package jpl.ch02.ex07;

public class Vehicle {
	/**　 現在のスピード */
	public double speed;
	/**　 現在の角度*/
	public double angle;
	/**　 所有者の名前*/
	public String ornerName;
	/**　 ID番号 */
	public long ID;
	/**　 次の乗り物の識別番号  */
	public static long nextID = 0;
	
	Vehicle()
	{
		this.speed = 0;
		this.angle = 0;
		this.ornerName = "no name";
		this.ID = nextID;
		nextID++;
	}
	
	Vehicle(String name)
	{
		this();
		this.ornerName = name;
	}
	
	public static void main(String[] args)
	{	
		Vehicle RX8 = new Vehicle("taroh");
		Vehicle AX7 = new Vehicle("ziroh");
		
		RX8.PrintVehicleInfo();
		AX7.PrintVehicleInfo();
		
	}
	
	public void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (速度 : 角度) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
}
