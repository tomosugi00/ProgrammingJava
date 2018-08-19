package jpl.ch02.ex11;

public class LinkedList {
	LinkedList preObject;
	LinkedList nextObject;
	Object value;
	
	/**@ Ž©•ª‚Ì‘O‚Ì—v‘f‚ðŠi”[*/
	LinkedList(LinkedList preObject)
	{
		this.preObject = preObject;
	}
	LinkedList(LinkedList preObject,Object value)
	{
		this.preObject = preObject;
		SetValue(value);
	}
	void SetValue(Object value)
	{
		this.value = value;
	}
	
	/**@ ŽŸ‚Ì—v‘f‚ðŠi”[ */
	void GetNextObject(Object value)
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
