package jpl.ch14.ex09;

public class ex09 implements Runnable
{
	private static ThreadGroup group = new ThreadGroup("top");
	
	public static void main(String[] args)
	{
		/* 封印 */
		
		/*
		ThreadGroup g1 = new ThreadGroup(group,"one");
		Thread th11 = new Thread(g1,new Runnable(){public void run() {for(;;) {}}},"1-1");
		Thread th12 = new Thread(g1,new Runnable(){public void run() {for(;;) {}}},"1-2");
		th11.start();
		th12.start();
		ThreadGroup g2 = new ThreadGroup(group,"two");
		Thread th21 = new Thread(g1,new Runnable(){public void run() {for(;;) {}}},"2-1");
		Thread th22 = new Thread(g1,new Runnable(){public void run() {for(;;) {}}},"2-2");
		Thread th23 = new Thread(g1,new Runnable(){public void run() {for(;;) {}}},"2-3");
		th21.start();
		th22.start();
		th23.start();
		ThreadGroup g3 = new ThreadGroup(group,"three");
		*/
		
		ex09 e9 = new ex09();
		Thread mainThread = new Thread(e9);
		mainThread.start();
		
	}

	@Override
	public void run()
	{
		printThreadGroup(group);
		for(;;)
		{
			try
			{
				Thread.sleep(3*1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				return;
			}
			
		}
	}
	
	private void printThreadGroup(ThreadGroup g)
	{
		ThreadGroup[] subG = new ThreadGroup[g.activeGroupCount()];
		int count = (g.enumerate(subG));
		if(count==0)
		{
			return;
		}
		
		
		/* グループが1つ以上存在している */
		for(int i=0;i<count;i++)
		{
			/* さらにスレッドグループがあるか確認 */
			ThreadGroup[] groups = new ThreadGroup[subG[i].activeGroupCount()];
			int groupCount = subG[i]!=null?subG[i].enumerate(groups):0;
			if(groupCount==0)
			{
				/* 以下にスレッドグループは存在しない */
				/* スレッドがあるか確認 */
				Thread[] threads = new Thread[subG[i].activeCount()];
				int threadCount = subG[i].enumerate(threads);
				if(threadCount==0)
				{
					continue;
				}
				for(int j=0;j<threadCount;j++)
				{
					System.out.println("    "+threads[j].getName());
				}
			}
			else
			{
				/* スレッドグループがさらに下の層まである */
				System.out.println("    "+subG[i].getName());
				printThreadGroup(subG[i]);
			}
		}
	}
}
