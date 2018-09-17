package jpl.ch04.ex02;

abstract class SortHarness
{
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();
	
	/** 全ソートをするために呼び出される */
	public final SortMetrics sort(Object[] data)
	{
		values = data;
		curMetrics.init();
		doSort();
		return getMetrics();
	}
	
	public final SortMetrics getMetrics()
	{
		return curMetrics.clone();
	}
	
	/** 拡張したクラスが要素の数を知るため */
	protected final int getDataLength()
	{
		return values.length;
	}
	
	/** 拡張したクラスが要素を調べるため */
	protected final Object probe(int i)
	{
		curMetrics.ProbeCntPlus();
		return values[i];
	}
	
	/** 拡張したクラスが要素を交換するため */
	protected final void swap(int i, int j)
	{
		curMetrics.SwapCntPlus();
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}
	
	/** 拡張したクラスが実装する -- ソートするのに使用される */
	protected abstract void doSort();
	
}
