package jpl.ch04.ex02;

/** SortHarnessを拡張したクラス
 *  ISorterHarnessを実装している */
public class SimpleSorterHarness extends SortHarness implements ISorterHarness
{
	protected void doSort()
	{
		for(int i=0; i< getDataLength(); i++)
		{
			for(int j=i+1;j<getDataLength();j++)
			{
				if(compare(i,j)>0)
				{
					swap(i,j);
				}
			}
		}
	}
	
	public int compare(int i, int j)
	{
		/* ... 比較方法を定義する ... */
		return 0;
	}

}
