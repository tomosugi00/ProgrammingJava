package threadpool;

import java.awt.List;
import java.util.ArrayList;

public class ThreadPool
{
	private final ArrayList<Runnable> queue;
	private final int queueSize;
	private final int numberOfThreads;
	private boolean isActive;
	
    /**
     * Constructs ThreadPool.
     *
     * @param queueSize the max size of queue
     * (キューの最大収納数)
     * @param numberOfThreads the number of threads in this pool.
     * (本スレッドプールにおけるスレッド数)
     * @throws IllegalArgumentException if either queueSize or numberOfThreads is less than 1
     * (キューサイズorスレッド数が1より小さい時)
     * 
     */
    public ThreadPool(int queueSize, int numberOfThreads)
    {
    	if(queueSize<1||numberOfThreads<1)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	this.queueSize = queueSize;
    	this.numberOfThreads = numberOfThreads;
    	this.isActive = false;
    	this.queue = new ArrayList<Runnable>(this.queueSize);
    }
    
    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     * (既にスレッドが起動していた場合)
     */
    public void start()
    {
    	synchronized(this)
    	{
    		if(this.isActive)
    		{
    			throw new IllegalStateException();
    		}
    		
    		// スレッドプール起動
    		this.isActive = true;
    	
    		// スレッド生成
    		ArrayList<DispatchThread> threads = new ArrayList<DispatchThread>(this.numberOfThreads);
    		for(int i=0;i<this.numberOfThreads;i++)
    		{
    			threads.add(new DispatchThread(this.queue));
    			threads.get(i).run();
    		}
    	}
    }   
    
    /**
     * Stop all threads and wait for their terminations.
     * (全てのスレッドを停止し、それらの終了を待つ)
     * @throws IllegalStateException if threads has not been started.
     * (スレッドがまだ起動していなかった場合)
     */
    public void stop()
    {
    	synchronized(this)
    	{
    		if(!this.isActive)
    		{
    			throw new IllegalStateException();
    		}
    		
    		// スレッドプールの全てのタスクを終了させる
    		
    		// 1. queueの中のタスクを全部消費する
    		// 2. スレッドを全部終了する
    		// 3. 全部終わったことを確認してreturnすること
    		
    		// thread内でbreakをどう仕込むか
    		// threadのisActiveがfalseになっているか調べられてしまう
    		
    		// スレッドプール停止
    		this.isActive = false;
    	}
    }
    
    /**
     * Executes the specified Runnable object, using a thread in the pool.
     * run() method will be invoked in the thread. If the queue is full, then
     * this method invocation will be blocked until the queue is not full.
     * 
     * @param runnable Runnable object whose run() method will be invoked.
     * @throws NullPointerException if runnable is null.
     * (引数runnableがnullの場合)
     * @throws IllegalStateException if this pool has not been started yet.
     * (スレッドプールがまだ起動していなかった場合)
     */
    public synchronized void dispatch(Runnable runnable)
	{
    	if(runnable == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	synchronized(this)
    	{
    			if(!this.isActive)
    			{
    				throw new NullPointerException();
    			}
    			
    			// キューが最大まで利用されている場合
				if(!(this.queue.size()<this.queueSize))
				{
					try
					{
						// キューに空きができるまで待機
						wait();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				// キューに追加
				this.queue.add(runnable);
				
    	}
    }
    
    
    /**
     * 
     */
    private class DispatchThread extends Thread
    {
    	// 1. queueSizeを超えたらwait、終わったらnotifyAllを使う
    	// 2. 通知したら見に行く、キューサイズを超えたタスクが来たら待つ
    	// 3. 取り出したと通知、queueを見に行く、空いてたらつめる
    	
    	private ArrayList<Runnable> queue;
    	
    	// 引数には他スレッドと共有するキューを指定
    	public DispatchThread(ArrayList<Runnable> queue)
    	{	
    		this.queue = queue;
    	}
    	
    	public void run()
    	{
    		while(true)
    		{
    			synchronized(this.queue)
    			{
    				// キューにタスクが存在するならば
    				if(this.queue.size()>0)
    				{
    					Runnable runnable = this.queue.remove(0);
    					notifyAll(); 
    					// 通知(条件をwhileにしましょう)
    					// そうじゃなかったらwhileに入るようにすること
    					
    					// タスクを実行
    	    			if(runnable!=null)
    	    			{
    	    				runnable.run();
    	    			}
    	    			/* ここにsleep(...)を入れて無限ループを回避してもよい */
    				}
    				else
    				{
    					//やることなかったらここでwait(これで無限ループしなくなる)
    					try
    					{
    						if(!this.isActive())
    						{
    							break;
    						}
							wait();
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
    
}


