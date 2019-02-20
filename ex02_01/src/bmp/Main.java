package bmp;

import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			BMP.readBMP("C:\\Users\\Ficti\\Desktop\\lena.bmp", "C:\\Users\\Ficti\\Desktop\\lena2.bmp");
		}
		catch (IOException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
