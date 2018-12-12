package jpl.ch14.ex06;

public class TimeCount implements Runnable
{
	private int time = 0;	// 秒
	
	public TimeCount()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run()
	{
		for(;;)
		{
			addTime();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}

	public void showElapsedTime()
	{
		System.out.println(Thread.currentThread().getName()+" : " + time);
	}
	
	private void addTime()
	{
		this.time++;
	}
}
