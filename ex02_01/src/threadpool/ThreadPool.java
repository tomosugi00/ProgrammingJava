package threadpool;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ThreadPool
{
	private final ArrayList<Runnable> queue;
	private final int queueSize;
	private final ArrayList<DispatchThread> threads;
	private final int numberOfThreads;
	private boolean isActive;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize       the max size of queue (キューの最大収納数)
	 * @param numberOfThreads the number of threads in this pool.
	 *                        (本スレッドプールにおけるスレッド数)
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads is
	 *                                  less than 1 (キューサイズorスレッド数が1より小さい時)
	 * 
	 */
	public ThreadPool(int queueSize, int numberOfThreads)
	{
		if (queueSize < 1 || numberOfThreads < 1)
		{
			throw new IllegalArgumentException();
		}

		this.queueSize = queueSize;
		this.numberOfThreads = numberOfThreads;
		this.isActive = false;
		this.queue = new ArrayList<Runnable>(this.queueSize);
		this.threads = new ArrayList<DispatchThread>(this.numberOfThreads);
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 *                               (既にスレッドが起動していた場合)
	 */
	public void start()
	{
		synchronized (this)
		{
			if (this.isActive)
			{
				throw new IllegalStateException();
			}

			this.isActive = true;

			//ArrayList<DispatchThread> threads = new ArrayList<DispatchThread>(this.numberOfThreads);
			threads.clear();
			for (int i = 0; i < this.numberOfThreads; i++)
			{
				threads.add(new DispatchThread(this.queue,() -> {
					return this.isActive;
				}));
				threads.get(i).start();
			}
		}
	}

	/**
	 * Stop all threads and wait for their terminations. (全てのスレッドを停止し、それらの終了を待つ)
	 * 
	 * @throws IllegalStateException if threads has not been started.
	 *                               (スレッドがまだ起動していなかった場合)
	 */
	public void stop()
	{
		synchronized (this)
		{
			if (!this.isActive)
			{
				throw new IllegalStateException();
			}

			// スレッドプールの全てのタスクを終了させる

			// 1. queueの中のタスクを全部消費する
			// 2. スレッドを全部終了する
			// 3. 全部終わったことを確認してreturnすること


			
			while (true)
			{
				synchronized (this.queue)
				{
					if(this.queue.size() > 0)
					{
						try
						{
							this.queue.wait();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						this.isActive = false;
						this.queue.notifyAll();
						break;
					}
				}
			}
			
			
			while(true)
			{
				int sum = 0;
				for(int i=0;i<threads.size();i++)
				{
					if(threads.get(i).isAlive())
					{
						sum++;
					}
				}
				if(sum<1)
				{
					break;
				}
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this method
	 * invocation will be blocked until the queue is not full.
	 * 
	 * @param runnable Runnable object whose run() method will be invoked.
	 * @throws NullPointerException  if runnable is null. (引数runnableがnullの場合)
	 * @throws IllegalStateException if this pool has not been started yet.
	 *                               (スレッドプールがまだ起動していなかった場合)
	 */
	public synchronized void dispatch(Runnable runnable)
	{
		if (runnable == null)
		{
			throw new NullPointerException();
		}

		synchronized (this)
		{
			if (!this.isActive)
			{
				throw new IllegalStateException();
			}

			while (true)
			{
				synchronized (this.queue)
				{
					if (this.queue.size() < this.queueSize)
					{
						this.queue.add(runnable);
						this.queue.notifyAll();
						break;
					}
					else
					{
						try
						{
							this.queue.wait();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	private class DispatchThread extends Thread
	{

		private final ArrayList<Runnable> queue;
		private final Supplier<Boolean> poolActive;

		public DispatchThread(ArrayList<Runnable> queue, Supplier<Boolean> liper)
		{
			this.queue = queue;
			this.poolActive = liper;
		}

		public void run()
		{
			while (true)
			{
				Runnable runnable = null;
				synchronized (this.queue)
				{
					if (this.queue.size() > 0)
					{
						runnable = this.queue.remove(0);
						this.queue.notifyAll();
					}
					else
					{
						try
						{
							if (!poolActive.get())
							{
								break;
							}
							this.queue.wait();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
				if (runnable != null)
				{
					runnable.run();
				}
			}
		}
	}
}
