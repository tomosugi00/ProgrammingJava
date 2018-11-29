package jpl.ch13.ex02;

public class ex02
{
	public static void main(String[] args)
	{
		System.out.println(CountOf("gugegogagagoga","gag"));
	}

	public static int CountOf(String source, String str)
	{
		int start=0;
		int count=0;
		while(start<source.length())
		{
			start = source.indexOf(str,start);
			if(start==-1)
			{
				break;
			}
			count++;
			start++;
		}
		return count;
	}
}
