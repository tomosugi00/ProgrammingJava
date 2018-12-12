package jpl.ch14.ex07;

/* yieldがtrueの場合、結果は常に同じではない */

public class Babble extends Thread
{
	static boolean doYield;	//他のスレッドに実行を譲るか
	static int howOften;		//表示する回数
	private String word;		//このスレッドの単語
	
	Babble(String whatToSay)
	{
		this.word = whatToSay;
	}
	
	public void run()
	{
		for (int i=0; i<howOften;i++)
		{
			System.out.println(this.word);
			if(doYield)
			{
				Thread.yield();//他のスレッドを走らせる
			}
		}
	}
	
	public static void main(String[] args)
	{
		doYield = new Boolean(args[0]).booleanValue();
		howOften = Integer.parseInt(args[1]);
		
		//各スレッドに対してスレッドを生成
		for(int i=2;i<args.length;i++)
		{
			new Babble(args[i]).start();
		}
	}

}
