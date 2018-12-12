package jpl.ch14.ex04;

public class ex04
{
	public static void main(String[] args)
	{
		System.out.println("ex04");
		NumberCount count = new NumberCount();
		for(int i=0;i<100;i++)
		{
			Thread thread = new Thread(count);
			thread.start();
		}
	}
}
