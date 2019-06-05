package interpret.model;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
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

	/* インスタンス保有 */
	private ArrayList<String> instanceList;		// View用
	private ArrayList<Object>instanceArray; 	// Model用
	/* 修正対象配列保有 */
	private String arrayInfoItem;	//View用。対象配列のindexを文字列で表示
	private int arrayTargetIndex;	//Model用。実際の対象配列。
	private Object arrayTargetInfo;	//Model用。実際の対象配列。
	private ArrayList<String>arrayInfoList;		// View用。対象配列の要素のリスト。対象配列分だけあればよい。また何度も内容を変えるに注意


	/*状態*/
	private ArrayList<String>  constractorList;	// View用 ※Listにするとエラー(コンパイラverのせい？)
	private ArrayList<Constructor<?>> constructorArray; 	// Model用

	private String constractorErrorCode ;	// エラーコード
	private String argumentErrorCode;
	private String arrayErrorCode;
	private String instanceErrorCode;
	private String arrayInfoErrorCode;
	private String arrayChangeErrorCode;

	public MainModel()
	{
		instanceList = new ArrayList<String>();
		instanceArray = new ArrayList<Object>();

		constractorList = new ArrayList<String>();
		constructorArray = new ArrayList<Constructor<?>>();

		arrayInfoItem="";
		arrayTargetIndex=-1;
		arrayTargetInfo=null;
		arrayInfoList = 	new ArrayList<String>();

		constractorErrorCode = "";
		argumentErrorCode = "";
		arrayErrorCode = "";
		instanceErrorCode = "";
		arrayInfoErrorCode = "";
		arrayChangeErrorCode = "";
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
				arguments = TypeDesc.getArgumentFrom(args).toArray();
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

				initials = TypeDesc.getArgumentFrom(arg);
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
			obj = TypeDesc.getArgumentFrom(value).toArray();
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
}
