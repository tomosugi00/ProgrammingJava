package jpl.ch03.ex09;

public class Garage implements Cloneable
{
	public Vehicle[] myGarage;
	
	public Garage() {
		this.myGarage = new Vehicle[10];
	}
	
	public Garage clone()
	{
		try {
			Garage grg = (Garage)super.clone();
			grg.myGarage = this.myGarage.clone();
			return grg;
		}
		catch(CloneNotSupportedException e)
		{
			throw new InternalError(e.toString());
		}
	}
	
	public static void main(String[] args)
	{
		Garage grg1 = new Garage();
		grg1.myGarage[0] = new Vehicle("noguchi");
		grg1.myGarage[1] = new Vehicle("higuchi");
		grg1.myGarage[2] = new Vehicle("fukuzawa");
		System.out.println(grg1.myGarage[0].toString());
		System.out.println(grg1.myGarage[1].toString());
		System.out.println(grg1.myGarage[2].toString());
		
		Garage grg2 = grg1.clone();
		
		System.out.println("---");
		System.out.println(grg2.myGarage[0].toString());
		System.out.println(grg2.myGarage[1].toString());
		System.out.println(grg2.myGarage[2].toString());
		
		grg1.myGarage[0].setOrnerName("nitobe");
		
		System.out.println("---");
		System.out.println(grg1.myGarage[0].toString());
		System.out.println(grg2.myGarage[0].toString());
		
	}

}
