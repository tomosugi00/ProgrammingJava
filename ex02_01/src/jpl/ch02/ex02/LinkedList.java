package jpl.ch02.ex02;

public class LinkedList {
	
	LinkedList preObject;
	LinkedList nextObject;
	Object value;
	
	/**�@ �����̑O�̗v�f���i�[*/
	LinkedList(LinkedList preObject)
	{
		this.preObject = preObject;
	}
	
	/**�@ ���̗v�f���i�[ */
	void GetNextObject()
	{
		this.nextObject = new LinkedList(this);
	}
}
