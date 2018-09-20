package gui;

public class Main
{
	public static void main(String[] args)
	{
		
		ClockFrame clock = new ClockFrame(300,200);
		Thread thread = new Thread(clock);
		thread.start();
	}
}