package jpl.ch14.ex05;

public class NumberCount implements Runnable
{
	private static int number = 0;
	public static synchronized void addNumber()
	{
		number++;
		System.out.println(Thread.currentThread().getName()+" : " + number);
	}
	public  static void subNumber()
	{
		number--;
		System.out.println(Thread.currentThread().getName()+" : " + number);
	}
	@Override
	public void run()
	{
		addNumber();
	}
}
