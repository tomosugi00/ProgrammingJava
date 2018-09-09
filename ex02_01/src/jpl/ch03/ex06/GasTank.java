package jpl.ch03.ex06;

public class GasTank extends EnergySource
{
	GasTank()
	{
		this.energy = 100;
	}
	@Override
	boolean Empty()
	{
		return this.energy <= 0;
	}

}
