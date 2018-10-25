package jpl.ch10.ex02;

public class ex02
{
	public static void main(String[] args)
	{
		String str = "aaanaaataaabaaaraaafaaa";	
		System.out.println("最終結果"+ParseStringToPlainText(str));
	}
	
	private static String ParseStringToPlainText(String str)
	{
		for(int i = 0;i<str.length();i++)
		{
			char c = str.charAt(i);
			switch(c)
			{
				case 'n': 
					str = str.replace('n', '\n');
					System.out.println(str);
					break;
				case 't': 
					str = str.replace('t', '\t');
					System.out.println(str);
					break;
				case 'b': 
					str = str.replace('b', '\b');
					System.out.println(str);
					break;
				case 'r': 
					str = str.replace('r', '\r');
					System.out.println(str);
					break;
				case 'f': 
					str = str.replace('f', '\f');
					System.out.println(str);
					break;
				case '\\': 
					str = str.replace('\\', '\\');
					System.out.println(str);
					break;
				case '\'': 
					str = str.replace('\'', '\'');
					System.out.println(str);
					break;
				case '\"': 
					str = str.replace('\"', '\"');
					System.out.println(str);
					break;
				default: 
					break;
			}
		}
		return str;
	}
}
