package jpl.ch02.ex06;

public class LinkedList {

	LinkedList preObject;
	LinkedList nextObject;
	Object value;
	
	/**@ ©•ª‚Ì‘O‚Ì—v‘f‚ğŠi”[*/
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
	
	/**@ Ÿ‚Ì—v‘f‚ğŠi”[ */
	void GetNextObject(Object value)
	{
		this.nextObject = new LinkedList(this,value);
	}
	
	public static void main(String[] args) {
		Vehicle RX8 = new Vehicle(10,10,"taro");
		LinkedList a = new LinkedList(null,RX8);
		Vehicle AX7 = new Vehicle(20,20,"ziro");
		a.GetNextObject(AX7);
		
		((Vehicle)a.value).PrintVehicleInfo();
		((Vehicle)a.nextObject.value).PrintVehicleInfo();
		
		
		

	}

}
