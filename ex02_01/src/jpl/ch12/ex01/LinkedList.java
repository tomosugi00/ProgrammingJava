package jpl.ch12.ex01;

public interface LinkedList
{
	/** リストの値を取得*/
	public Object GetValue();

	/** リストに値を格納*/
	public void SetValue(Object obj);
	
	/** リストの次の要素への参照を取得*/
	public LinkedList GetNextObject();
	
	/** リストの次の要素への参照を格納*/
	public void SetNextObject(Object list);

	/** リスト中のオブジェクトを探す。リスト中に見つからなければ例外を投げる */
	public LinkedList Find(Object obj) throws ObjectNotFoundException;
}
