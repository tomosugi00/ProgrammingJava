package jpl.ch06.ex02;

/* 列挙型TURNを用いることで他クラスでもTURNを使えることがあげられる */

public class Vehicle
{	
	private double speed;
	private double angle;
	private String ornerName;
	private long ID;
	private static long nextID = 0;	
	//public final double TURN_LEFT  = -45.0;
	//public final double TURN_RIGHT = 45.0;
	
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
	
	public double getSpeed()
	{
		return this.speed;
	}
	public void changeSpeed(double spd)
	{
		this.speed = spd;
	}
	public void stop()
	{
		this.speed = 0;
	}
	public double getAngle()
	{
		return this.angle;
	}
	public void setAngle(double agl)
	{
		this.angle = agl;
	}
	public String getOrnerName()
	{
		return this.ornerName;
	}
	public void setOrnerName(String name)
	{
		this.ornerName = name;
	}
	public long getID()
	{
		return this.ID;
	}
	
	
	public static void main(String[] args)
	{	
		Vehicle RX8 = new Vehicle("Fujiya");
		System.out.println(RX8);
	}
	
	public void turn(double angle)
	{
		this.angle = angle;
	}

	public void turn(TURN t)
	{
		if(t==TURN.TURN_LEFT)
		{
			this.angle = -45.0;
		}
		else if(t==TURN.TURN_RIGHT)
		{
			this.angle = 45.0;
		}
	}
	
	public void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (���x : �p�x) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
	
	public static void PrintVehicleID()
	{
		long MAX_ID = nextID;
		if(nextID>0) {
			MAX_ID--;
		}
		System.out.printf("���ݔ��s���Ă��鎯�ʎq�̍ő�l = %d",MAX_ID );
	}
	
	public String toString() {
		String desc = this.ID + " ( " + this.ornerName + " )";	//������Object�^��toString()
		return desc;
	}
}
