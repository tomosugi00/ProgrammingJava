package interpret.model.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;



public class TypeDesc
{
	/**
	 * (現状利用しない？)継承元も含めたすべてのコンストラクタを取得する
	 * @param inputClass
	 * @return
	 */
	public static ArrayList<Constructor<?>> getConstuctorArray(Class<?> inputClass)
	{
		if(inputClass ==null)
		{
			return null;
		}

		ArrayList<Type> typeList = getTypes(inputClass);
		ArrayList<Constructor<?>> constructorList = new ArrayList<Constructor<?>>();

		for(Type type : typeList)
		{
			if(type instanceof Class<?>)
			{
				Class<?> cls = (Class<?>) type;
				constructorList.addAll(Arrays.asList(cls.getDeclaredConstructors()));	//TODO ここ例外処理いる？
			}
		}
		return constructorList;
	}

	/***
	 * 引数typeが継承するクラスをリストで返す
	 * リストはスーパークラスから順に格納し、最後に引数typeのクラスが入る
	 * @param type
	 * @return
	 */
	public static ArrayList<Type> getTypes(Type type)
	{
		// Classか、ParameterizedTypeか判定
		Class<?> cls = null;
		if(type instanceof Class<?>)		//通常(普通のクラス)用
		{
			cls = (Class<?>) type;
		}
		else if(type instanceof ParameterizedType)	//総称型(List<T>など)用
		{
			cls = (Class<?>)((ParameterizedType)type).getRawType();
		}
		else	//未対応のクラス
		{
			throw new Error(String.format("Unexpected Type: %s",type.getTypeName())) ;
		}

		//
		ArrayList<Type> typeList = new ArrayList<Type>();

		// スーパークラスへ再起
		if(cls.getGenericSuperclass()!=null)
		{
			typeList.addAll(getTypes(cls.getGenericSuperclass()));
		}
		// インターフェースへ再起
		Type[] interfaces = cls.getGenericInterfaces();
		for(Type iface : interfaces)
		{
			typeList.addAll(getTypes(iface));
		}
		//最後に自分を追加
		typeList.add(cls);

		return typeList;
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public static ArrayList<Object> getArgumentFrom(String args)
	{		
		String[] arguments = args.split(",");
		ArrayList<Object> resultArgs = new ArrayList<Object>();

		//TODO char型は後回し
		for(String arg:arguments)
		{		
			if(arg.matches("\".*\""))	// "文字列"ならば
			{
				String[] argument = arg.split("\"");
				//System.out.println("文字列："+argument[1]);
				resultArgs.add(argument[1]);	// 結果は [ , (引数)] となるのでargument[1]
			}
			else if(arg.matches("true")||arg.matches("false"))	// booleanならば(大文字対応しない)
			{
				resultArgs.add(Boolean.valueOf(arg));
			}
			else if(arg.matches("#[0-9]+"))	//既存インスタンスならば
			{
				//仮で文字列
				//System.out.println("インスタンス："+arg);
				resultArgs.add("インスタンス");

			}
			else if(arg.matches("^-?(0|[1-9]\\d*)(\\.\\d+|)$"))	//数字(対応できない表記あり)
			{
				if(arg.contains("."))	//浮動小数点
				{
					double val = Double.valueOf(arg);
					if(val>Float.MIN_NORMAL&&val<Float.MAX_VALUE)
					{
						resultArgs.add(Float.valueOf(arg));
					}
					else
					{
						resultArgs.add(val);	
					}
				}
				else	//整数
				{
					long val = Long.valueOf(arg);
					if(val>Byte.MIN_VALUE&&val<Byte.MAX_VALUE)	//Byte
					{
						resultArgs.add(Byte.valueOf(arg));
					}
					else if(val>Short.MIN_VALUE&&val<Short.MAX_VALUE)	//short
					{
						resultArgs.add(Short.valueOf(arg));
					}
					else if(val>Integer.MIN_VALUE&&val<Integer.MAX_VALUE)
					{
						resultArgs.add(Integer.valueOf(arg));
					}
					else
					{
						resultArgs.add(val);
					}
				}
			}
			else
			{
				return null;	//TODO 良い感じの返しを考える
			}
			//System.out.println("---");
		}
		return resultArgs;

	}

}
