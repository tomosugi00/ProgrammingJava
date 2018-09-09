package jpl.ch04.ex01;

public class Battery implements EnergySource
{
	private int energy;
	
	Battery()
	{
		this.energy = 100;
	}
	
	public int getEnergy()
	{
		return this.getEnergy();
	}
	
	public void setEnergy(int addEnergy)
	{
		if(addEnergy<=0)
		{
			return;
		}
		this.energy += addEnergy;
	}
	
	public boolean Empty()
	{
		return energy <= 0;
	}

}
