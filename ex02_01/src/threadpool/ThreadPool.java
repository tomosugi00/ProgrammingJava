package threadpool;

public class ThreadPool
{
    /**
     * Constructs ThreadPool.
     *
     * @param queueSize the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     *
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     *         is less than 1
     */
    public ThreadPool(int queueSize, int numberOfThreads)
    {
    	throw new AssertionError("Not Implemented Yet");
    }
    
    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public void start()
    {
       	throw new AssertionError("Not Implemented Yet");
    }   
    
    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop()
    {
       	throw new AssertionError("Not Implemented Yet");
    }
    
    /**
     * Executes the specified Runnable object, using a thread in the pool.
     * run() method will be invoked in the thread. If the queue is full, then
     * this method invocation will be blocked until the queue is not full.
     * 
     * @param runnable Runnable object whose run() method will be invoked.
     *
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable)
	{
       	throw new AssertionError("Not Implemented Yet");
    }
}
