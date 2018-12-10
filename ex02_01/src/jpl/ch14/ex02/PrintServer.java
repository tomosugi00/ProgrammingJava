package jpl.ch14.ex02;

public class PrintServer implements Runnable
{
	private final PrintQueue requests = new PrintQueue();
	private String myThread;
	public PrintServer()
	{
		Thread thread = new Thread(this);
		myThread = thread.getName();
		thread.start();
	}
	public void print(PrintJob job)
	{
		requests.add(job);
	}
	@Override
	public void run()
	{
		if(Thread.currentThread().getName()!=myThread)
		{
			return;
		}
		for(;;)
		{
			try
			{
				realPrint(requests.remove());
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	private void realPrint(PrintJob job)
	{
		// 印刷の実際の処理
	}
}