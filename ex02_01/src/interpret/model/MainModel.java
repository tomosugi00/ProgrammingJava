package interpret.model;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import interpret.model.util.TypeDesc;

public class MainModel
{
	/* イベント */
	private EventHandler inputClassEventHandler = new EventHandler();
	private EventHandler instanceEventHandler = new EventHandler();
	private EventHandler arrayEventHandler = new EventHandler();
	private EventHandler arrayInfoEventHandler = new EventHandler();
	private EventHandler arrayChangeEventHandler = new EventHandler();
	private EventHandler instanceInfoEventHandler = new EventHandler();
	private EventHandler arrayItemInfoEventHandler = new EventHandler();
	private EventHandler instanceChangeFieldEventHandler = new EventHandler();
	private EventHandler instanceExecMethodEventHandler = new EventHandler();
	private EventHandler instanceGetMethodEventHandler = new EventHandler();

	/* インスタンス保有 */
	private ArrayList<String> instanceList;		// View用
	private ArrayList<Object>instanceArray; 	// Model用
	/* 修正対象配列保有 */
	private String arrayInfoItem;	//View用。対象配列のindexを文字列で表示
	private int arrayTargetIndex;	//Model用。実際の対象配列。
	private Object arrayTargetInfo;	//Model用。実際の対象配列。
	private ArrayList<String>arrayInfoList;		// View用。対象配列の要素のリスト。対象配列分だけあればよい。また何度も内容を変えるに注意

	/* 実行対象インスタンス保有 */
	private Object targetInstance;	//Model用。対象インスタンス
	private ArrayList<Field> targetInstanceFieldArray;	//Model用。フィールド
	private ArrayList<Method> targetInstanceMethodArray;	//Model用。メソッド
	private ArrayList<String> targetInstanceFieldList;		//View用
	private ArrayList<String> targetInstanceMethodList;	//View用
	private String targetInstanceItem;	// View用。インスタンス&配列兼用

	/*状態*/
	private ArrayList<String>  constractorList;	// View用 ※Listにするとエラー(コンパイラverのせい？)
	private ArrayList<Constructor<?>> constructorArray; 	// Model用

	private String constractorErrorCode ;	// エラーコード
	private String argumentErrorCode;
	private String arrayErrorCode;
	private String instanceErrorCode;
	private String arrayInfoErrorCode;
	private String arrayChangeErrorCode;
	private String instanceInfoErrorCode;
	private String arrayItemInfoErrorCode;
	private String instanceChangeFieldErrorCode;
	private String instanceExecMethodErrorCode;
	private String instanceGetMethodErrorCode;

	public MainModel()
	{
		instanceList = new ArrayList<String>();
		instanceArray = new ArrayList<Object>();

		constractorList = new ArrayList<String>();
		constructorArray = new ArrayList<Constructor<?>>();

		arrayInfoItem="";
		arrayTargetIndex=-1;
		arrayTargetInfo=null;
		arrayInfoList = new ArrayList<String>();

		targetInstance = null;

		constractorErrorCode = "";
		argumentErrorCode = "";
		arrayErrorCode = "";
		instanceErrorCode = "";
		arrayInfoErrorCode = "";
		arrayChangeErrorCode = "";
		instanceInfoErrorCode="";
		arrayItemInfoErrorCode = "";
		instanceChangeFieldErrorCode = "";
		instanceGetMethodErrorCode ="";
	}

	/**
	 * 左側：コンストラクタ一覧呼び出す
	 * @param inputClass
	 */
	public void getConstructorsFrom(String inputClass)
	{
		/* 初期化 */
		this.constractorList = new ArrayList<String>();
		this.constructorArray = new ArrayList<Constructor<?>>();

		/* コンストラクタリスト作成 */
		try
		{
			/* クラス取得 */
			Class<?> cls = Class.forName(inputClass);

			/* コンストラクタ取得 */
			this.constructorArray = new ArrayList<Constructor<?>>(Arrays.asList(cls.getDeclaredConstructors()));	

			/* MainView用に文字に変換 */
			//constructorArray = TypeDesc.GetConstuctorArray(cls);	// 継承元全てのコンストラクタを表示する場合
			for (Constructor<?> constructor : constructorArray)
			{
				constractorList.add(constructor.toString());
			}
			// 結果表示
			this.constractorErrorCode = "OK";
		}
		catch (ClassNotFoundException e)
		{
			this.constractorErrorCode = "クラスが存在しません";
		}
		/* イベント発火 */
		inputClassEventHandler.broadcast(null);
	}

	/**
	 * 左側：選択中のコンストラクタでインスタンス作る
	 * @param index
	 * @param args
	 */
	public void getInstanceFrom(int index,String args)
	{
		if(index<0)
		{
			this.argumentErrorCode = "コンストラクタが選択されていません";
			this.instanceErrorCode = "";
			instanceEventHandler.broadcast(null);
			return;
		}

		/* コンストラクタ取得 */
		Constructor<?> ctor = this.constructorArray.get(index);	// 敢えて例外落ち

		/* 引数取得 */
		Object[] arguments = null;
		if(!args.isEmpty())	//空の場合は無視する
		{
			try
			{
				arguments = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),args).toArray();
			}
			catch (Exception e)
			{
				this.argumentErrorCode = "フィールドに不正な値があります";
				this.instanceErrorCode = "";
				instanceEventHandler.broadcast(null);
				return;
			}
		}

		/* インスタンス作成 */
		try
		{
			//System.out.printf("%s\n", arguments.getClass().isPrimitive());
			Object obj = ctor.newInstance(arguments);

			instanceArray.add(obj);
			instanceList.add(String.format("#%d：%s", instanceList.size(),obj.getClass().getSimpleName()));	//その時のサイズ = 今回のインデックス

			this.argumentErrorCode = "OK";
			//ここのサイズは上のaddと比べて+1されていることに注意
			this.instanceErrorCode = String.format("#%d：%s", instanceList.size()-1,obj.getClass().getSimpleName());
		}
		catch (IllegalArgumentException e)
		{
			this.argumentErrorCode = "コンストラクタに対して不正な引数です";
			this.instanceErrorCode = "";
		}
		catch (Exception e)
		{
			this.argumentErrorCode = "原因不明のエラーです";
			this.instanceErrorCode = "";
		}

		/* イベント発火 */
		instanceEventHandler.broadcast(null);
	}

	/**
	 * 左側：設定した配列作る
	 * @param input
	 * @param length
	 * @param arg
	 */
	public void getArrayFrom(String input,int length,String arg)
	{
		Class<?> cls = null;	// クラス
		ArrayList<Object> initials=new ArrayList<Object>();	//初期値

		try
		{
			// 配列生成
			cls = Class.forName(input);
			Object obj = Array.newInstance(cls, length);
			int objLen = Array.getLength(obj);	//TODO 一応例外処理

			// 初期値取得
			if(!arg.isEmpty())
			{

				initials = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),arg);
				if(initials==null)
				{
					this.arrayErrorCode = "フィールドに不正な値があります";
					arrayEventHandler.broadcast(null);
					return;
				}
			}
			if(initials.size()!=objLen)
			{
				this.arrayErrorCode = "配列の長さと初期値の数が等しくありません";
				arrayEventHandler.broadcast(null);
				return;
			}
			// 初期値格納
			for(int i=0;i<initials.size();i++)
			{
				if(initials.get(i).getClass()!=cls )
				{
					//TODO あまり片付けをしないまま出ていくのは関心しない
					this.arrayErrorCode = "配列と初期値の型が一致しません";
					arrayEventHandler.broadcast(null);
					return;
				}
				Array.set(obj, i, initials.get(i));	//arrayListのコピーでもok？
			}
			this.arrayErrorCode = "OK";
			instanceArray.add(obj);	//追加
			instanceList.add(String.format("#%d：%s", instanceList.size(),obj.getClass().getSimpleName()));	//その時のサイズ = 今回のインデックス

			this.argumentErrorCode = "OK";
			//ここのサイズは上のaddと比べて+1されていることに注意
			this.instanceErrorCode = String.format("#%d：%s", instanceList.size()-1,obj.getClass().getSimpleName());
		}
		catch (ClassNotFoundException e)
		{
			this.arrayErrorCode = "クラスが存在しません";
			arrayEventHandler.broadcast(null);
			return;
		}

		/* イベント発火 */
		arrayEventHandler.broadcast(null);
	}

	/**
	 * 左側：空配列作る
	 * @param input
	 * @param length
	 */
	public void getArrayFrom(String input,int length)
	{
		Class<?> cls = null;	// クラス
		try
		{
			// 配列生成
			cls = Class.forName(input);
			Object obj = Array.newInstance(cls, length);

			this.arrayErrorCode = "OK";
			instanceArray.add(obj);	//追加
			instanceList.add(String.format("#%d：%s", instanceList.size(),obj.getClass().getSimpleName()));	//その時のサイズ = 今回のインデックス

			this.argumentErrorCode = "OK";
			//ここのサイズは上のaddと比べて+1されていることに注意
			this.instanceErrorCode = String.format("#%d：%s", instanceList.size()-1,obj.getClass().getSimpleName());
		}
		catch (ClassNotFoundException e)
		{
			this.arrayErrorCode = "クラスが存在しません";
			arrayEventHandler.broadcast(null);
			return;
		}

		/* イベント発火 */
		arrayEventHandler.broadcast(null);
	}

	/**
	 * 右側：選択中の配列の要素一覧を取得する
	 * @param index
	 */
	public void getArrayInfoOf(int index)
	{
		//初期化
		this.arrayInfoItem = "";
		this.arrayInfoList = new ArrayList<String>();

		if(index<0)
		{
			this.arrayInfoErrorCode = "インスタンスが選択されていません";
			arrayInfoEventHandler.broadcast(null);
			return;
		}
		// TODO indexの範囲外指定エラー
		Object obj = instanceArray.get(index);
		if(!obj.getClass().isArray())
		{
			this.arrayInfoErrorCode = "選択インスタンスは配列ではありません";
			arrayInfoEventHandler.broadcast(null);
			return;
		}

		this.arrayTargetIndex = index;
		this.arrayTargetInfo = obj;	//対象配列を記録

		//TODO ここ全くエラー処理してなくて笑う
		this.arrayInfoItem = instanceList.get(index);
		int length = Array.getLength(obj);
		for (int i=0; i<length; i++) {
			arrayInfoList.add(String.format("#%d#%d：%s", index, i, Array.get(obj, i)));
		}

		this.arrayInfoErrorCode = "OK";
		arrayInfoEventHandler.broadcast(null);

	}

	/**
	 * 右側：選択中の配列要素の内容を変える
	 * @param index
	 * @param value
	 */
	public void changeValueInArray(int index,String value)
	{
		if(index<0)
		{
			this.arrayChangeErrorCode = "要素が選択されていません";
			arrayChangeEventHandler.broadcast(null);
			return;
		}
		if(value.isEmpty())
		{
			this.arrayChangeErrorCode = "値が入力されていません";
			arrayChangeEventHandler.broadcast(null);
			return;
		}

		Object obj = null;
		try
		{
			obj = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),value).toArray();
		}
		catch (Exception e)
		{
			this.arrayChangeErrorCode = "フィールドに不正な値があります";
			arrayChangeEventHandler.broadcast(null);
			return;
		}
		if(Array.getLength(obj)!=1)
		{
			//TODO 複数変える場合の対応
			this.arrayChangeErrorCode = "フィールドの値を1つにしてください";
			arrayChangeEventHandler.broadcast(null);
			return;
		}

		/* 値格納 */
		try
		{
			// 内容修正
			Object val = Array.get(obj, 0);	//1つの要素 = objの最初の要素 
			Array.set(this.arrayTargetInfo, index, val);

			// 要素表修正
			arrayInfoList.set(index, String.format("#%d#%d：%s", this.arrayTargetIndex, index, val));
			this.arrayChangeErrorCode = "OK";
			arrayChangeEventHandler.broadcast(null);
		}
		catch (IllegalArgumentException e)
		{
			this.arrayChangeErrorCode = "値の型が不正です";
			arrayChangeEventHandler.broadcast(null);
			return;
		}
		catch (Exception e)
		{
			this.arrayChangeErrorCode = "原因不明のエラーです";
			arrayChangeEventHandler.broadcast(null);
			return;
		}
	}

	/**
	 * 右側：選択中のインスタンスのフィールドとメソッドを取得
	 * @param index
	 * @param value
	 */
	public void getInstanceInfoFrom(int index)
	{

		//初期化
		this.targetInstanceItem = "";
		this.targetInstanceFieldArray = new ArrayList<Field>();
		this.targetInstanceMethodArray = new ArrayList<Method>();
		this.targetInstanceFieldList = new ArrayList<String>();
		this.targetInstanceMethodList = new ArrayList<String>();

		if(index<0)
		{
			this.instanceInfoErrorCode = "要素が選択されていません";
			instanceInfoEventHandler.broadcast(null);
			return;
		}

		//対象インスタンス取得
		Object obj = this.instanceArray.get(index);
		Class<?> cls = obj.getClass();

		//対象インスタンス表示
		this.targetInstance = obj;
		this.targetInstanceItem = instanceList.get(index);

		//フィールド取得
		this.targetInstanceFieldArray = new ArrayList<Field>(Arrays.asList(cls.getDeclaredFields()));	
		for (Field field: targetInstanceFieldArray)
		{
			this.targetInstanceFieldList.add(field.toGenericString());
		}

		//メソッド取得
		this.targetInstanceMethodArray = new ArrayList<Method>(Arrays.asList(cls.getDeclaredMethods()));	
		for (Method method: targetInstanceMethodArray)
		{
			this.targetInstanceMethodList.add(method.toGenericString());
		}

		this.instanceInfoErrorCode = "OK";
		instanceInfoEventHandler.broadcast(null);

	}


	/**
	 * 右側：選択中の配列要素のフィールドとメソッドを取得
	 * @param index
	 */
	public void getArrayItemInfoFrom(int index)
	{
		//初期化
		this.targetInstanceItem = "";
		this.targetInstanceFieldArray = new ArrayList<Field>();
		this.targetInstanceMethodArray = new ArrayList<Method>();
		this.targetInstanceFieldList = new ArrayList<String>();
		this.targetInstanceMethodList = new ArrayList<String>();

		if(index<0)
		{
			this.arrayItemInfoErrorCode = "配列要素が選択されていません";
			arrayItemInfoEventHandler.broadcast(null);
			return;
		}

		// 対象インスタンスは配列か？
		if(this.arrayTargetInfo==null)
		{
			this.arrayItemInfoErrorCode = "配列が選択されていません";
			arrayItemInfoEventHandler.broadcast(null);
			return;
		}
		if(!this.arrayTargetInfo.getClass().isArray())
		{
			this.arrayItemInfoErrorCode = "対象インスタンスが配列ではありません";
			arrayItemInfoEventHandler.broadcast(null);
			return;
		}
		// 配列の要素を取得
		Object target = Array.get(this.arrayTargetInfo, index);
		if(target==null)
		{
			this.arrayItemInfoErrorCode = "要素がnullです";
			arrayItemInfoEventHandler.broadcast(null);
			return;
		}

		Class<?> targetCls = target.getClass();

		//対象インスタンス表示
		this.targetInstance = target;
		this.targetInstanceItem = arrayInfoList.get(index);

		//フィールド取得
		this.targetInstanceFieldArray = new ArrayList<Field>(Arrays.asList(targetCls.getDeclaredFields()));	
		for (Field field: targetInstanceFieldArray)
		{
			this.targetInstanceFieldList.add(field.toGenericString());
		}

		//メソッド取得
		this.targetInstanceMethodArray = new ArrayList<Method>(Arrays.asList(targetCls.getDeclaredMethods()));	
		for (Method method: targetInstanceMethodArray)
		{
			this.targetInstanceMethodList.add(method.toGenericString());
		}

		this.arrayItemInfoErrorCode = "OK";
		arrayItemInfoEventHandler.broadcast(null);

	}

	/**
	 * 右側：対象インスタンスの
	 * @param index
	 * @param value
	 */
	public void setValueToField(int index,String value)
	{
		// 初期化
		this.instanceErrorCode = "";

		if(index<0)
		{
			this.instanceChangeFieldErrorCode = "フィールドが選択されていません";
			instanceChangeFieldEventHandler.broadcast(null);
			return;
		}

		Field field = this.targetInstanceFieldArray.get(index);
		field.setAccessible(true);

		Object obj = null;
		try
		{
			obj = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),value).toArray();
		}
		catch (Exception e)
		{
			this.instanceChangeFieldErrorCode = "フィールドに不正な値があります";
			instanceChangeFieldEventHandler.broadcast(null);
			return;
		}
		if(Array.getLength(obj)!=1)
		{
			//TODO 複数変える場合の対応
			this.instanceChangeFieldErrorCode = "フィールドの値を1つにしてください";
			instanceChangeFieldEventHandler.broadcast(null);
			return;
		}

		try
		{
			Object val = Array.get(obj, 0);	//最初の要素
			field.set(this.targetInstance, val);
			this.instanceChangeFieldErrorCode = "OK";
			this.instanceErrorCode = val.toString();
			instanceChangeFieldEventHandler.broadcast(null);
		}
		catch (IllegalArgumentException e)
		{
			this.instanceChangeFieldErrorCode = "値の型が不正です";
			instanceChangeFieldEventHandler.broadcast(null);
			return;
		}
		catch (IllegalAccessException e)
		{
			this.instanceChangeFieldErrorCode = "内部エラー：アクセスできません";
			instanceChangeFieldEventHandler.broadcast(null);
			return;
		}
	}

	public void execMethod(int index,String arg)
	{
		// 初期化
		this.instanceErrorCode = "";

		if(index<0)
		{
			this.instanceExecMethodErrorCode = "メソッドが選択されていません";
			instanceExecMethodEventHandler.broadcast(null);
			return;
		}

		Method method = this.targetInstanceMethodArray.get(index);
		method.setAccessible(true);

		Object[] obj = null;
		if(!arg.isEmpty())	//空の場合は無視する
		{
			try
			{
				obj = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),arg).toArray();
			}
			catch (Exception e)
			{
				this.instanceExecMethodErrorCode = "引数に不正な値があります";
				instanceExecMethodEventHandler.broadcast(null);
				return;
			}
		}

		try
		{
			Object result = method.invoke(this.targetInstance, obj);
			this.instanceExecMethodErrorCode = "OK";
			this.instanceErrorCode = result.toString();
			instanceExecMethodEventHandler.broadcast(null);
		}
		catch (IllegalArgumentException e)
		{
			this.instanceExecMethodErrorCode = "値の型が不正です";
			instanceExecMethodEventHandler.broadcast(null);
			return;
		}
		catch (IllegalAccessException e)
		{
			this.instanceExecMethodErrorCode = "内部エラー：アクセスできません";
			instanceExecMethodEventHandler.broadcast(null);
			return;
		}
		catch (InvocationTargetException e)
		{
			this.instanceExecMethodErrorCode = "選択メソッド内部でエラーが発生しました";
			instanceExecMethodEventHandler.broadcast(null);
			return;
		}
	}

	public void getInstanceFromMethod(int index,String arg)
	{
		// 初期化
		this.instanceErrorCode = "";

		if(index<0)
		{
			this.instanceGetMethodErrorCode = "メソッドが選択されていません";
			instanceGetMethodEventHandler.broadcast(null);
			return;
		}

		Method method = this.targetInstanceMethodArray.get(index);
		method.setAccessible(true);

		Object[] obj = null;
		if(!arg.isEmpty())	//空の場合は無視する
		{
			try
			{
				obj = TypeDesc.getArgumentFrom(new ArrayList<Object>(this.instanceArray),arg).toArray();
			}
			catch (Exception e)
			{
				this.instanceGetMethodErrorCode = "引数に不正な値があります";
				instanceGetMethodEventHandler.broadcast(null);
				return;
			}
		}

		try
		{
			Object result = method.invoke(this.targetInstance, obj);

			this.instanceArray.add(result);
			this.instanceList.add(String.format("#%d：%s", instanceList.size(),result.getClass().getSimpleName()));	//その時のサイズ = 今回のインデックス

			this.instanceGetMethodErrorCode = "OK";
			this.instanceErrorCode = result.toString();
			instanceGetMethodEventHandler.broadcast(null);
		}
		catch (IllegalArgumentException e)
		{
			this.instanceGetMethodErrorCode = "値の型が不正です";
			instanceGetMethodEventHandler.broadcast(null);
			return;
		}
		catch (IllegalAccessException e)
		{
			this.instanceGetMethodErrorCode = "内部エラー：アクセスできません";
			instanceGetMethodEventHandler.broadcast(null);
			return;
		}
		catch (InvocationTargetException e)
		{
			this.instanceGetMethodErrorCode = "選択メソッド内部でエラーが発生しました";
			instanceGetMethodEventHandler.broadcast(null);
			return;
		}
	}


	/* イベントセット */
	public void setInputClassEvent(Consumer<EventArgs> listener)
	{
		this.inputClassEventHandler.add(listener);
	}
	public void setInstanceEvent(Consumer<EventArgs> listener)
	{
		this.instanceEventHandler.add(listener);
	}
	public void setArrayEvent(Consumer<EventArgs> listener)
	{
		this.arrayEventHandler.add(listener);
	}
	public void setArrayInfoEvent(Consumer<EventArgs> listener)
	{
		this.arrayInfoEventHandler.add(listener);
	}
	public void setArrayChangeEvent(Consumer<EventArgs> listener)
	{
		this.arrayChangeEventHandler.add(listener);
	}
	public void setInstanceInfoEvent(Consumer<EventArgs> listener)
	{
		this.instanceInfoEventHandler.add(listener);
	}
	public void setArrayItemInfoEvent(Consumer<EventArgs> listener)
	{
		this.arrayItemInfoEventHandler.add(listener);
	}
	public void setInstanceChangeFieldEvent(Consumer<EventArgs> listener)
	{
		this.instanceChangeFieldEventHandler.add(listener);
	}
	public void setInstanceExecMethodEvent(Consumer<EventArgs> listener)
	{
		this.instanceExecMethodEventHandler.add(listener);
	}
	public void setInstanceGetMethodEvent(Consumer<EventArgs> listener)
	{
		this.instanceGetMethodEventHandler.add(listener);
	}

	/* プロパティ */
	public ArrayList<String> getConstractorList()
	{
		//TODO 読み取り専用にしておく方がよい
		return this.constractorList;
	}
	public ArrayList<String> getInstanceList()
	{
		//TODO 読み取り専用にしておく方がよい
		return this.instanceList;
	}
	public String getArrayInfoItem()
	{
		return this.arrayInfoItem;
	}
	public ArrayList<String> getArrayInfoList()
	{
		return this.arrayInfoList;
	}
	public String getTargetInstanceItem()
	{
		return this.targetInstanceItem;
	}
	public ArrayList<String> getTargetInstanceFieldList()
	{
		return this.targetInstanceFieldList;
	}
	public ArrayList<String> getTargetInstanceMethodList()
	{
		return this.targetInstanceMethodList;
	}
	// エラーコード取得
	public String getConstractorError()
	{
		return this.constractorErrorCode;
	}
	public String getArgumentError()
	{
		return this.argumentErrorCode;
	}
	public String getArrayError()
	{
		return this.arrayErrorCode;
	}
	public String getInstanceError()
	{
		return this.instanceErrorCode;
	}
	public String getArrayInfoError()
	{
		return this.arrayInfoErrorCode;
	}
	public String getArrayChangeError()
	{
		return this.arrayChangeErrorCode;
	}
	public String getInstanceInfoError()
	{
		return this.instanceInfoErrorCode;
	}
	public String getArrayItemInfoError()
	{
		return this.arrayItemInfoErrorCode;
	}
	public String getInstanceChangeFieldError()
	{
		return this.instanceChangeFieldErrorCode;
	}
	public String getInstanceExecMethodError()
	{
		return this.instanceExecMethodErrorCode;
	}
	public String getInstanceGetMethodError()
	{
		return this.instanceGetMethodErrorCode;
	}
}
