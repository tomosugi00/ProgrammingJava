package jpl.ch09.ex02;

public class ex02
{
	public static void main(String[] args)
	{
		int x = 127;
		int count = CountBitOf(x);
		System.out.printf("%dの1bitの数は%d個である\n",x,count);
	}
	
	private static int CountBitOf(int num)
	{
		int count = 0;
		int tmp = num;
		int mask = 0x0001;
		for(int i=0;i<32;i++) {
			int bit= tmp & mask;
			if(bit>0)
			{
				count++;
			}
			tmp>>=1;
		}
		return count;
	}
}
