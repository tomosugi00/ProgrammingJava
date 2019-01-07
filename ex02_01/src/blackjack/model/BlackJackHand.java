package blackjack.model;

import blackjack.card.Hand;

public class BlackJackHand extends Hand
{
	public BlackJackHand()
	{
		super();
	}
	
	/** 手札がバーストしているか判定 */
	public boolean isBurst(boolean dealer)
	{
		return getSum(dealer)>21;
	}
	
	/** 手札がブラックジャックか判定 */
	public boolean isBlackJack()
	{
		// 1 と 11
		return  (myHand.get(0).getNumber()==1 && myHand.get(1).getNumber()==11)
				||  (myHand.get(1).getNumber()==1 && myHand.get(0).getNumber()==11);
	}
	
	/** スプリットか判定 */
	public boolean isSplit()
	{
		if(myHand.size()>2)
		{
			return false;
		}
		
		int card0 = this.myHand.get(0).getNumber();
		if(card0>10)
		{
			card0=10;
		}
		int card1 = this.myHand.get(1).getNumber();
		if(card1>10)
		{
			card0=10;
		}
		
		return card0==card1;
	}
	
	//CUI用になっているのでGUIにも対応するようなものに変える
	/** 手札表示 */
	public String showHand(boolean dealer)
	{
		StringBuilder builder = new StringBuilder();
		
		if(dealer)
		{
			builder.append(" [? ??]");
		}
		else
		{
			builder.append(" [" + myHand.get(0).getSuit()+" "+myHand.get(0).getNumber()+"]");
		}
		for(int i=1;i<myHand.size();i++)
		{
			builder.append(", [" + myHand.get(i).getSuit()+" "+myHand.get(i).getNumber()+"]");
		}
		if(this.isBlackJack())
		{
			builder.append(" Black Jack!! ");
		}
		if(this.isBurst(false))
		{
			builder.append(" Burst!! ");
		}
		builder.append("  合計: "+getSum(dealer)+"\n");
		return builder.toString();
	}
	
	//デフォルト値使いたい
	/** 手札の合計 */
	public int getSum(boolean dealer)
	{
		int start=0;
		if(dealer)
		{
			start=1;
		}
		int sum=0;
		for(int i=start;i<myHand.size();i++)
		{
			int num =  myHand.get(i).getNumber();
			if(num>10)
			{
				num=10;
			}
			sum += num;
		}
		return sum;
	}
	
}
