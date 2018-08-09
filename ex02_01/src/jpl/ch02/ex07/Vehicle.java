package jpl.ch02.ex07;

public class Vehicle {
	/**�@ ���݂̃X�s�[�h */
	public double speed;
	/**�@ ���݂̊p�x*/
	public double angle;
	/**�@ ���L�҂̖��O*/
	public String ornerName;
	/**�@ ID�ԍ� */
	public long ID;
	/**�@ ���̏�蕨�̎��ʔԍ�  */
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
		System.out.printf("ID:%d, name:%s, (���x : �p�x) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
}
