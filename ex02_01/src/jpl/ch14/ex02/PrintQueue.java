package jpl.ch14.ex02;

public class PrintQueue
{
	private SingleLinkQueue<PrintJob> queue = new SingleLinkQueue<PrintJob>();
	
	public synchronized void add(PrintJob j)
	{
		queue.add(j);
		notifyAll(); //待ってるスレッドに知らせる:プリントジョブが追加された
	}
	
	public synchronized PrintJob remove() throws InterruptedException
	{
		while(queue.size==0)
		{
			wait();
		}
		return queue.remove();
	}
	
}
