package jpl.ch03.ex11;

public final class SortMetrics implements Cloneable
{
	private long probeCnt,     // 単純なデータの値の調査
	             compareCnt,  // 2つの要素の比較
	             swapCnt;     // 2つの要素の交換

	public void init()
	{
		probeCnt = compareCnt = swapCnt = 0;
	}
	
	public String toString()
	{
		return probeCnt + "probs " +
	           compareCnt + " compares " +
			   swapCnt + " swaps";
	}
	
	/** このクラスは、cloneをサポートしている */
	public SortMetrics clone()
	{
		try
		{
			//デフォルトの仕組みで十分
			return (SortMetrics) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			//起こりえない。このクラスとObjectは複製できる
			throw new InternalError(e.toString());
		}
	}
	
	/** 調査回数に1加算 */
	public void ProbeCntPlus()
	{
		this.probeCnt++;
	}
	
	/** 比較回数に1加算 */
	public void CompareCntPlus()
	{
		this.compareCnt++;
	}
	
	/** 交換回数に1加算 */
	public void SwapCntPlus()
	{
		this.swapCnt++;
	}

}
