package jpl.ch14.ex03;

public class ex03 
{
	public static void main(String[] args)
	{
		System.out.println("ex03");
		NumberCount count = new NumberCount();
		for(int i=0;i<100;i++)
		{
			Thread thread = new Thread(count);
			thread.start();
		}
	}
}
