package jpl.ch13.ex05;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ex05
{
	public static void main(String[] args)
	{
		String str = "1000期生代表の1000000円を持つ10代の少年が1番強いと思う100000000の男の中の男は12345678900円の貯金がある";
		System.out.println(SetComma(str));
	}

	public static String SetComma(String str)
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
			String subStr2 = SetComma2(subStr);
			matcher.appendReplacement(buffer, subStr2);
		}
		matcher.appendTail(buffer);
		
		return buffer.toString();
		
	}
	
	private static String SetComma2(String subStr)
	{
		String numStr="#,##0";
		DecimalFormat df = new DecimalFormat(numStr);
		subStr = df.format(Long.valueOf(subStr, 10));
		return subStr;
	}
}
