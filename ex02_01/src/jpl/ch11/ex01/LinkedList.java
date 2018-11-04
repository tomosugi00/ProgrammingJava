package jpl.ch11.ex01;

public class LinkedList<E>
{
	LinkedList<E> preObject;
	LinkedList<E> nextObject;
	E value;
	
	LinkedList(LinkedList<E> preObject)
	{
		this.preObject = preObject;
	}
	
	void GetNextObject()
	{
		this.nextObject = new LinkedList<E>(this);
	}
}
