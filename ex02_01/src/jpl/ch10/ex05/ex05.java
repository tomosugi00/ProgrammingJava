package jpl.ch10.ex05;

public class ex05 {

	public static void main(String[] args)
	{
		PrintCharacterBetween('a','A');
	}

	private static void PrintCharacterBetween(char c1,char c2)
	{
		if(c1==c2)
		{
			System.out.println(c1);
			return;
		}
		if(c1>c2)
		{
			char tmp = c1;
			c1 = c2;
			c2 = tmp;
		}
		//c1<c2である
		for(;c1<=c2;c1++)
		{
			System.out.println(c1);
		}
		
	}
	
}
