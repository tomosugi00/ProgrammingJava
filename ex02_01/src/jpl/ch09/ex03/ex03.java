package jpl.ch09.ex03;

/* 9章の内容で使えそうな要素は使っている */

public class ex03
{
	private final int depth = 13;
	private int[][] tri = new int[depth][];
	
	public static void main(String[] args)
	{
		ex03 e = new ex03();
		e.CreatePascalTriangle();
		e.PrintPascalTriangle();
	}
	
	private void CreatePascalTriangle()
	{
		
		for (int d = 1; d <=depth; d++)
		{
			/* 各行の要素の算出 */
			int width = d;
			int[] row = new int[width];
			for (int w = 1; w <=width; w++)
			{
				if (w == 1)
				{
					row[w-1] = 1;
				}
				else if (w == d)
				{
					row[w-1] = 1;
				}
				else
				{
					row[w-1] = tri[(d-1)-1][(w-1)-1] + tri[(d-1)-1][w-1];
				}
			}
			tri[d-1] = row; 
		}
	}
		
	private  void PrintPascalTriangle()
	{
		for(int d=0;d<depth;d++)
		{
			int width = d;
			for(int w=0;w<=width;w++)
			{
				System.out.printf("%4d", tri[d][w]);
				if(w!=width)
				{
					System.out.printf(" ");
				}
			}
			System.out.printf("\n");
		}
	}
}
