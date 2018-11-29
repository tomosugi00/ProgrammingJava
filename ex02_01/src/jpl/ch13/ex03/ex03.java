package jpl.ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class ex03
{
	public static void main(String[] args)
	{
		String str = "aaa";
		List<String> list = delimitedString(str,'a','b');
		for(String word:list)
		{
			System.out.println(word);
		}
	}

	public static List<String> delimitedString(String from, char start, char end)
	{
		List<String> list = new ArrayList<String>();
		
		for(int p=0;p<from.length();p++)
		{
			p=from.indexOf(start, p);
			if(p==-1)
			{
				break;
			}
			for(int q=p;q<from.length();q++)
			{
				q=from.indexOf(end, q);
				if(q==-1)
				{
					list.add(from.substring(p));
					break;
				}
				list.add(from.substring(p, q+1));
			}
		}
		return list;
	}
}
