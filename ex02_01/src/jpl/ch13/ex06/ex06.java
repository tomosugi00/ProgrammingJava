package jpl.ch13.ex06;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ex06
{
	public static void main(String[] args)
	{
		String str = "1000期生代表の1000000円を持つ10代の少年が1番強いと思う100000000の男の中の男は12345678900円の貯金がある";
		System.out.println(SetComma(str,4));
	}

	public static String SetComma(String str,int num)
	{
		//文字列を最初から読み込んで10進数と一致する場所があれば、その部分をsetcomma2へ
		String check = "[0-9]+";
		Pattern pat = Pattern.compile(check);
		Matcher matcher = pat.matcher(str);
		StringBuffer buffer = new StringBuffer();
		
		while(matcher.find())
		{
			// 一致したパターン
			String subStr = matcher.group();
			String subStr2 = SetComma2(subStr,num);
			matcher.appendReplacement(buffer, subStr2);
		}
		matcher.appendTail(buffer);
		
		return buffer.toString();
		
	}
	
	private static String SetComma2(String subStr,int num)
	{
		String numStr="#,";
		for(int i=0;i<num-1;i++)
		{
			numStr += "#";
		}
		numStr +="0";
		DecimalFormat df = new DecimalFormat(numStr);
		subStr = df.format(Long.valueOf(subStr, 10));
		return subStr;
	}
}
