package jpl.ch03.ex05;

public class MethodBenchmark extends Benchmark {

	@Override
	void benchmark()
	{ 
	}
	
	public static void main(String[] arg)
	{
		int count = Integer.parseInt(arg[0]);
		long time = new MethodBenchmark().repeat(count);
		System.out.printf("It takes %d nano seconds to count %d\n",time,count);
	}

}
