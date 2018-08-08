package jpl.ch02.ex06;

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
	
	public void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (速度 : 角度) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
}
