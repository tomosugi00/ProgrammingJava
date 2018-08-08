package jpl.ch02.ex02;

public class LinkedList {
	
	LinkedList preObject;
	LinkedList nextObject;
	Object value;
	
	/**@ ©•ª‚Ì‘O‚Ì—v‘f‚ğŠi”[*/
	LinkedList(LinkedList preObject)
	{
		this.preObject = preObject;
	}
	
	/**@ Ÿ‚Ì—v‘f‚ğŠi”[ */
	void GetNextObject()
	{
		this.nextObject = new LinkedList(this);
	}
}
