package jpl.ch10.ex04;

/*
 * do-whileにはしない。
 * 引数によっては一度も処理しないことも考えられるため。
 */

abstract class Benchmark
{
	abstract void benchmark();
	
	public final long repeat(int count)
	{
		long start = System.nanoTime();
		//for(int i=0;i<count;i++)
		//{
		//	benchmark();
		//}
		while(count>0)
		{
			benchmark();
			count--;
		}
		return (System.nanoTime() - start);
	}
}
