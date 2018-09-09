package jpl.ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable
{

	/** �ő���Ȑ� */
	private int sheet;
	/** ��Ԑl�� */
	private int passenger;
	
	public PassengerVehicle() {
		super();
		this.sheet=4;
		this.passenger=0;
	}
	
	public PassengerVehicle(int sheet,int passenger) {
		super();
		this.sheet = sheet;
		this.passenger = passenger;
		
	}
	
	/** ���݂̗��q�Ґ���\�����܂� */
	public void PrintCurrentPassenger()
	{
		System.out.printf("���݂̗��q�҂́A���Ȑ�%d�ɑ΂���%d�l�ł�\n",this.sheet,this.passenger);
	}
	
	public static void main(String[] args)
	{	
		PassengerVehicle pv1 = new PassengerVehicle(4,3);
		PassengerVehicle pv2 = new PassengerVehicle(8,7);
		PassengerVehicle pv3 = new PassengerVehicle(200,200);
		pv1.PrintCurrentPassenger();
		pv2.PrintCurrentPassenger();
		pv3.PrintCurrentPassenger();
	}

}
