package jpl.ch10.ex03;

/* Switchの方が楽 */

public class ex03
{	
	private static boolean IsWorkDayIf(Week week)
	{
		if(week == Week.SUNDAY)
		{
			return false;
		}
		if(week == Week.MONDAY)
		{
			return true;
		}
		if(week == Week.TUESDAY)
		{
			return true;
		}
		if(week == Week.WEDNESDAY)
		{
			return true;
		}
		if(week == Week.THURSDAY)
		{
			return true;
		}
		if(week == Week.FRIDAY)
		{
			return true;
		}
		if(week == Week.SATURDAY)
		{
			return false;
		}
		return false;
	}
	private static boolean IsWorkDaySwitch(Week week)
	{
		switch(week)
		{
			case SUNDAY :
				return false;
			case MONDAY:
				return true;
			case TUESDAY:
				return true;
			case WEDNESDAY:
				return true;
			case THURSDAY:
				return true;
			case FRIDAY:
				return true;
			case SATURDAY:
				return false;
		}
		return false;
	}
	
}
