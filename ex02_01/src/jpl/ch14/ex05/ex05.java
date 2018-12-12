package jpl.ch14.ex05;

public class ex05 implements  Runnable
{
	public static void main(String[] args)
	{
		System.out.println("ex05");
		NumberCount count = new NumberCount();
		ex05 e5 = new ex05();
		for(int i=0;i<100;i++)
		{
			Thread thread;
			if(i%2==0)
			{
				thread = new Thread(count);
			}
			else
			{
				thread = new Thread(e5);
			}
			thread.start();
		}
	}

	@Override
	public void run()
	{
		subNumber();
	}
	
	private synchronized void subNumber()
	{
		NumberCount.subNumber();
	}

}
