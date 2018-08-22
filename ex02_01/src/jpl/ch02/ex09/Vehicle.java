package jpl.ch02.ex09;

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
		PrintVehicleID();		
	}
	
	public void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (速度 : 角度) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
	
	/** 呼び出し時までに使われた識別番号の最大値を返す */
	public static void PrintVehicleID()
	{
		long MAX_ID = nextID;
		if(nextID>0) {
			MAX_ID--;
		}
		System.out.printf("現在発行している識別子の最大値 = %d",MAX_ID );
	}
}
