package jpl.ch02.ex15;

public class Vehicle {
	/**　 現在のスピード */
	private double speed;
	/**　 現在の角度*/
	private double angle;
	/**　 所有者の名前*/
	private String ornerName;
	/**　 ID番号 */
	private long ID;
	/**　 次の乗り物の識別番号  */
	private static long nextID = 0;
	
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
	
	public double getSpeed() {
		return this.speed;
	}
	/** 通常のsetterの名前変更しただけ */
	public void changeSpeed(double spd) {
		this.speed = spd;
	}
	public void stop() {
		this.speed = 0;
	}
	public double getAngle() {
		return this.angle;
	}
	public void setAngle(double agl) {
		this.angle = agl;
	}
	public String getOrnerName() {
		return this.ornerName;
	}
	public void setOrnerName(String name) {
		this.ornerName = name;
	}
	public long getID() {
		return this.ID;
	}
	
	
	public static void main(String[] args)
	{	
		Vehicle RX8 = new Vehicle("Fujiya");
		System.out.println(RX8);
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
	
	/** toStringをオーバーライド */
	public String toString() {
		String desc = this.ID + " ( " + this.ornerName + " )";	//ここはObject型のtoString()
		return desc;
	}
}
