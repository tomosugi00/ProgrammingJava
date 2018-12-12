package jpl.ch14.ex06;

public class TimeThread implements Runnable
{
	private final TimeCount count;
	private final int printTime;
	
	public TimeThread(TimeCount count, int printTime)
	{
		this.count = count;
		this.printTime = printTime;
	}

	@Override
	public void run()
	{
		for(;;)
		{
			try
			{
				Thread.sleep(printTime*1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				return;
			}
			printTime();
		}
	}
	
	public  void printTime()
	{
		this.count.showElapsedTime();
	}
}
