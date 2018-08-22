package jpl.ch02.ex14;

public class LinkedList {
	
	/**
	 * valueは変更の可能性があるため、getter,setter
	 * preObject,nextObjectは外部からアクセスするとエラーの危険があるためgetterのみ
	 */
	
	private LinkedList preObject;
	private LinkedList nextObject;
	private Object value;
	
	/**　 自分の前の要素を格納*/
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
	
	
	/**　 次の要素を格納 */
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
