package jpl.ch13.ex01;

public class ex01
{
	public static void main(String[] args)
	{
		System.out.println(CountOf("sagyouhasashisuseso",'s'));

	}
	
	public static int CountOf(String str, char ch)
	{
		int count=0;
		for(int i=0;i<str.length();i++) {
			if(ch==str.charAt(i))
			{
				count++;
			}
		}
		return count;
	}

}
