package jpl.ch13.ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ex04
{
	public static void main(String[] args)
	{

	}
	
	public static List<String> Constructer (String str)
	{
		List<String> list = new ArrayList();
		
		List<String> type = 
				Arrays.asList("Boolean","Character","Number","Byte","Short","Integer","Long","Float","Double","Void");
		
		return list;
	}

}
