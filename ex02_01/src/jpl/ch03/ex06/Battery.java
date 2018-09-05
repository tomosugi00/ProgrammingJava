package jpl.ch03.ex06;

public class Battery extends EnergySource
{
	Battery()
	{
		this.energy = 100;
	}
	@Override
	boolean Empty()
	{
		return energy <= 0;
	}

}
