package jpl.ch02.ex05;

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
	
	Vehicle(double speed,double angle,String name)
	{
		this.speed = speed;
		this.angle = angle;
		this.ornerName = name;
		this.ID = nextID;
		nextID++;
	}
	
	public static void main(String[] args)
	{	
		Vehicle RX8 = new Vehicle(10,45,"taroh");
		Vehicle AX7 = new Vehicle(20,30,"ziroh");
		
		RX8.PrintVehicleInfo();
		AX7.PrintVehicleInfo();
		
	}

	private void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (速度 : 角度) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
}
