package jpl.ch14.ex06;

public class ex06
{
	public static void main(String[] args)
	{
		TimeCount count = new TimeCount();
		Thread time1  = new Thread(new TimeThread(count,1));
		Thread time7  = new Thread(new TimeThread(count,7));
		Thread time15 = new Thread(new TimeThread(count,15));
		time1.start();
		time7.start();
		time15.start();
	}
}
