package jpl.ch14.ex03;

public class NumberCount implements Runnable
{
	private int number = 0;
	public synchronized void addNumber()
	{
		this.number++;
		System.out.println(Thread.currentThread().getName()+" : " + this.number);
	}
	@Override
	public void run()
	{
		addNumber();
	}
}
