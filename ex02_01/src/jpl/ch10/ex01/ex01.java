package jpl.ch10.ex01;

public class ex01
{
	public static void main(String[] args)
	{
		String str = "aaanaaataaabaaaraaafaaa";	
		System.out.println("最終結果"+ParseStringToPlainText(str));
	}
	
	private static String ParseStringToPlainText(String str)
	{
		System.out.println(str);
		if(str.contains("n"))
		{
			str = str.replace('n', '\n');
			System.out.println(str);
		}
		if(str.contains("t"))
		{
			str =str.replace('t', '\t');
			System.out.println(str);
		}
		if(str.contains("b"))
		{
			str =str.replace('b', '\b');
			System.out.println(str);
		}
		if(str.contains("r"))
		{
			str =str.replace('r', '\r');
			System.out.println(str);
		}
		if(str.contains("f"))
		{
			str =str.replace('f', '\f');
			System.out.println(str);
		}
		if(str.contains("\\"))	//謎
		{
			str =str.replace('\\', '\\');
			System.out.println(str);
		}
		if(str.contains("'"))	//謎
		{
			str =str.replace('\'', '\'');
			System.out.println(str);
		}
		if(str.contains("\""))	//謎
		{
			str =str.replace('\"', '\"');
			System.out.println(str);
		}
		return str;
	}

}
