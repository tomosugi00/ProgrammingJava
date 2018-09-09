package jpl.ch04.ex03;

public interface LinkedList
{
	/** リストの値を取得*/
	public Object GetValue();

	/** リストに値を格納*/
	public void SetValue(Object value);
	
	/** リストの次の要素への参照を取得*/
	public LinkedList GetNextObject();
	
	/** リストの次の要素への参照を格納*/
	public void SetNextObject(Object list);

}
