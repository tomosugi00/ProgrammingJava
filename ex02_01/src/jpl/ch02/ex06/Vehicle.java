package jpl.ch02.ex06;

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
		System.out.printf("ID:%d, name:%s, (���x : �p�x) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
}
