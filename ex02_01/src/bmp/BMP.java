package bmp;

import java.io.*;

public class BMP
{
	// 定数
	private static final int OFFSET = 10;
	private static final int WIDTH = 18;
	private static final int HEIGHT = 22;
	private static final int DIGIT = 256;
	private static final int UNSIGN = 0xFF;
	
	public static void readBMP(String intputFilePath,String outputFilePath) throws IOException
	{
		int fileSize=0;

		byte[] dat = null;
		FileInputStream fi = null;
		BufferedInputStream bi = null;
		try
		{
			// ファイルサイズ取得
			File file = new File(intputFilePath);
			fileSize = (int)(file.length());
			dat = new byte[fileSize];
			// 読み取り
			fi = new FileInputStream(intputFilePath);
			bi = new BufferedInputStream(fi);
			bi.read(dat);
		}
		catch (FileNotFoundException e)
		{
			return;
		}
		finally
		{
			if(fi!=null)
			{
				fi.close();
			}
			if(bi!=null)
			{
				bi.close();
			}
		}

		// 24bitBMP判定
		boolean is24Bit = ((dat[0]=='B') && (dat[1]=='M') && (dat[28]==24));
		if (!is24Bit)
		{
			return;
		}

		int width=0;
		int height=0;
		int rowLength=0;
		// 幅取得
		if (dat[WIDTH]<0)
		{
			width = (DIGIT+dat[WIDTH]) + (int)dat[WIDTH+1]*DIGIT;
		}
		else
		{
			width = dat[WIDTH] + (int)dat[WIDTH+1]*DIGIT;
		}
		//高さ取得
		if (dat[HEIGHT]<0)
		{
			height = (DIGIT+dat[HEIGHT]) + (int)dat[HEIGHT+1]*DIGIT;
		}
		else
		{
			height = dat[HEIGHT] + (int)dat[HEIGHT+1]*DIGIT;
		}
		// 1行の長さ
		int pad = (width*3) % 4;
		if (pad==0)
		{
			rowLength = width*3;
		}
		else
		{
			rowLength = width * 3 + (4-pad);
		}

		// ピクセル列配列取得(幅*3色*高さ)
		int[] pixel = new int[ width * 3 * height];
		int offset = dat[OFFSET];

		// ピクセル列設定
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				int address = offset + (j*3) + (i*rowLength);
				int index = j*3 + ((height-1)-i) * (width*3);

				pixel[index] = dat[address+2] &UNSIGN;		//R
				pixel[index+1] = dat[address+1]&UNSIGN;	//G
				pixel[index+2] = dat[address]&UNSIGN;		//B
			}
		}

		// 画像処理
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				int index = j * 3 + i * (width*3);
				
				
				pixel[index] = 255 - pixel[index];		//R
				pixel[index+1] = 255- pixel[index+1];	//G
				pixel[index+2] = 255 - pixel[index+2];	//B
			}
		}
		
		//pixel -> dat 
		// ファイル書き出し
		byte[] output = new byte[fileSize];
		for(int i=0;i<offset;i++)
		{
			output[i] = dat[i];
		}
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				int address = offset + (j*3) + (i*rowLength);
				int index = j*3 + ((height-1)-i) * (width*3);
				
				output[address] = (byte) pixel[index+2];		//B
				output[address+1] = (byte) pixel[index+1];	//G
				output[address+2] = (byte) pixel[index];		//R
			}
		}
		
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		try
		{
			fo = new FileOutputStream(outputFilePath);
			bo = new BufferedOutputStream(fo);
			bo.write(output);
			bo.flush();
		}
		catch (FileNotFoundException e)
		{
			return;
		}
		finally
		{
			if(fo!=null)
			{
				fo.close();
			}
			if(bo!=null)
			{
				bo.close();
			}
		}
	}
}
