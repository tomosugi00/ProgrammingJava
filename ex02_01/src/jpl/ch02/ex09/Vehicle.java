package jpl.ch02.ex09;

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
		PrintVehicleID();		
	}
	
	public void PrintVehicleInfo()
	{
		System.out.printf("ID:%d, name:%s, (���x : �p�x) = (%.2f, %.2f)%n", this.ID,this.ornerName,this.speed,this.angle);
	}
	
	/** �Ăяo�����܂łɎg��ꂽ���ʔԍ��̍ő�l��Ԃ� */
	public static void PrintVehicleID()
	{
		long MAX_ID = nextID;
		if(nextID>0) {
			MAX_ID--;
		}
		System.out.printf("���ݔ��s���Ă��鎯�ʎq�̍ő�l = %d",MAX_ID );
	}
}
