package jpl.ch04.ex03;


/** 
 * LinkedListを提供する場合、リストの要素と次への参照も義務付ける必要がある
 * そのため、メソッドだけでなくフィールドも必要となるのでインターフェースより抽象クラスがよいと考える
 * */
public class SimpleLinkedList implements LinkedList
{
	private SimpleLinkedList nextObject;
	private Object value;
	
	SimpleLinkedList(SimpleLinkedList nextObject, Object value)
	{
		this.nextObject = nextObject;
		SetValue(value);
	}
	
	public Object GetValue()
	{
		return this.value;
	}
	public void SetValue(Object value)
	{
		this.value = value;
	}	
	public SimpleLinkedList GetNextObject()
	{
		return this.nextObject;
	}
	public void SetNextObject(Object list)
	{
		if(list instanceof SimpleLinkedList )
		{
			this.nextObject = (SimpleLinkedList) list;
		}
	}
}
