package jpl.ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable
{

	/** 最大座席数 */
	private int sheet;
	/** 乗車人数 */
	private int passenger;
	
	public PassengerVehicle() {
		super();
		this.sheet=4;
		this.passenger=0;
	}
	
	public PassengerVehicle(int sheet,int passenger) {
		super();
		this.sheet = sheet;
		this.passenger = passenger;
		
	}
	
	/** 現在の旅客者数を表示します */
	public void PrintCurrentPassenger()
	{
		System.out.printf("現在の旅客者は、座席数%dに対して%d人です\n",this.sheet,this.passenger);
	}
	
	public static void main(String[] args)
	{	
		PassengerVehicle pv1 = new PassengerVehicle(4,3);
		PassengerVehicle pv2 = new PassengerVehicle(8,7);
		PassengerVehicle pv3 = new PassengerVehicle(200,200);
		pv1.PrintCurrentPassenger();
		pv2.PrintCurrentPassenger();
		pv3.PrintCurrentPassenger();
	}

}
