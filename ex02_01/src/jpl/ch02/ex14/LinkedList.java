package jpl.ch02.ex14;

public class LinkedList {
	
	/**
	 * value�͕ύX�̉\�������邽�߁Agetter,setter
	 * preObject,nextObject�͊O������A�N�Z�X����ƃG���[�̊댯�����邽��getter�̂�
	 */
	
	private LinkedList preObject;
	private LinkedList nextObject;
	private Object value;
	
	/**�@ �����̑O�̗v�f���i�[*/
	LinkedList(LinkedList preObject)
	{
		this.preObject = preObject;
	}
	LinkedList(LinkedList preObject,Object value)
	{
		this.preObject = preObject;
		SetValue(value);
	}
	
	
	public void SetValue(Object value)
	{
		this.value = value;
	}
	public Object GetValue() {
		return this.value;
	}
	public LinkedList GetPreObject() {
		return this.preObject;
	}
	public LinkedList GetNextObject() {
		return this.nextObject;
	}
	
	
	/**�@ ���̗v�f���i�[ */
	public void GetNextObject(Object value)
	{
		this.nextObject = new LinkedList(this,value);
	}
	
	public static void main(String[] args) {
		LinkedList one = new LinkedList(null,"1");
		LinkedList two = new LinkedList(one,"2");
		LinkedList thr = new LinkedList(one,"3");
		one.nextObject = two;
		two.nextObject = thr;
		
		System.out.println(one);
	}
	
	public String toString()
	{
		String desc = this.value +  " > ";
		if(this.nextObject!=null)
		{
			desc += this.nextObject;
		}
		else
		{
			desc += "<end>";
		}
		return desc;
	}
}
