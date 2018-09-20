package jpl.ch06.ex03;

public enum Verbose
{
	SILENT,
	TERSE,
	NORMAL,
	VERBODSE;
	
	private int verbosity=2;
	
	void setVerbosity(Verbose level)
	{
		if(level==Verbose.SILENT)
		{
			this.verbosity = 0;
		}
		else if(level==Verbose.TERSE)
		{
			this.verbosity = 1;
		}
		else if(level==Verbose.NORMAL)
		{
			this.verbosity = 2;
		}
		else if(level==Verbose.VERBODSE)
		{
			this.verbosity = 3;
		}
	}
	int getVerbosity()
	{
		return this.verbosity;
	}
}
