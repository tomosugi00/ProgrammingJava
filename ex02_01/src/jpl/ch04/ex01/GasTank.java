package jpl.ch04.ex01;

public class GasTank implements EnergySource
{
	private int energy;
	
	GasTank()
	{
		this.energy = 100;
	}
	
	public int getEnergy()
	{
		return this.energy;
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
		return this.energy <= 0;
	}

}
