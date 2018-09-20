package gui;

public class Main
{
	public static void main(String[] args)
	{
		
		ClockFrame clock = new ClockFrame(400,300);
		Thread thread = new Thread(clock);
		thread.start();
	}
}