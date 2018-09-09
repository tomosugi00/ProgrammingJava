package jpl.ch03.ex10;

public class LinkedList implements Cloneable
{
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
	
	public int GetListNum() {
		int pre = this.getPreNum();
		int next = this.getNextNum();
		System.out.println("�O�̗v�f�� : "+pre+", ��̗v�f�� : "+next);
		return 1 + pre + next;
	}
	public int getPreNum() {
		if(this.preObject==null) {
			return 0;
		}
		int count = 1;
		count += preObject.getPreNum();
		return count;
	}
	public int getNextNum() {	
		if(this.nextObject==null) {
			return 0;
		}
		int count = 1;
		count += nextObject.getPreNum();
		return count;
	}
	
	/**�@ ���̗v�f���i�[ */
	public void GetNextObject(Object value)
	{
		this.nextObject = new LinkedList(this,value);
	}
	
	public static void main(String[] args) {
		LinkedList one = new LinkedList(null,"1");
		LinkedList two = new LinkedList(one,"2");
		LinkedList three = new LinkedList(two,"3");
		LinkedList four = new LinkedList(three,"4");
		LinkedList five = new LinkedList(four,"5");
		
		one.nextObject = two;
		two.nextObject = three;
		three.nextObject = four;
		four.nextObject = five;
		
		LinkedList one_copy = (LinkedList)one.clone();
		
		System.out.println(one);
		System.out.println(one_copy);
		System.out.println("---");
		one.SetValue("10");
		System.out.println(one);
		System.out.println(one_copy);
		
		//int count = two.GetListNum();
		//System.out.println("���v�� = " + count );
		//count = five.GetListNum();
		//System.out.println("���v�� = " + count );
		
	}
	
	public LinkedList clone ()
	{
		try {
			LinkedList lst = (LinkedList)super.clone();
			return lst;
		}
		catch(CloneNotSupportedException e)
		{
			throw new InternalError(e.toString());
		}
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
