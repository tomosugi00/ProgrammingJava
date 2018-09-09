package jpl.ch04.ex01;

public class Vehicle {
	/**  */
	private double speed;
	/**�@ ���݂̊p�x*/
	private double angle;
	/**�@ ���L�҂̖��O*/
	private String ornerName;
	/**�@ ID�ԍ� */
	private long ID;
	/**�@ ���̏�蕨�̎��ʔԍ�  */
	private static long nextID = 0;
	/** �R�� */
	private EnergySource energy;
	
	public final double TURN_LEFT  = -45.0;
	public final double TURN_RIGHT = 45.0;
	
	/**�@ �R���X�g���N�^ */
	Vehicle()
	{
		this.speed = 0;
		this.angle = 0;
		this.ornerName = "no name";
		this.ID = nextID;
		this.energy = new GasTank();
		nextID++;
	}
	Vehicle(String name)
	{
		this();
		this.ornerName = name;
	}
	
	Vehicle(EnergySource e)
	{
		this();
		this.energy = e;
	}
	
	/** ����邩���肵�܂� */
	public boolean start()
	{
		return !this.energy.Empty();
	}
	
	public double getSpeed() {
		return this.speed;
	}
	/** �ʏ��setter�̖��O�ύX�������� */
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
		Vehicle v = new Vehicle(args[0]);
		v.PrintVehicleInfo();
	}
	
	public void turn(double angle)
	{
		this.angle = angle;
	}
	/**�@ ����N���X�̒萔�݂̂������ɂ�����@���킩��Ȃ� */
	public void turn()
	{
		this.angle = new Vehicle().TURN_LEFT;
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
	
	/** toString���I�[�o�[���C�h */
	public String toString() {
		String desc = this.ID + " ( " + this.ornerName + " )";	//������Object�^��toString()
		return desc;
	}
}
