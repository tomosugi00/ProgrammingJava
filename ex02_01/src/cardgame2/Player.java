package cardgame2;

import java.util.ArrayList;
import java.util.List;

public class Player
{
	private List<BlackJackHand> myHands;

	public Player()
	{
		this.myHands = new ArrayList<BlackJackHand>();
		this.myHands.add(new BlackJackHand());
	}

	public void draw(int index)
	{
		this.myHands.get(index).draw();
	}
	
	public BlackJackHand getHand(int index)
	{
		return this.myHands.get(index);
	}
	
	public int getNumberOfHands()
	{
		return this.myHands.size();
	}

	public void split()
	{
		//自分の全ての手札で判定を行う。処理中に手札が増えることもある
		for(int i=0;i<myHands.size();i++)
		{
			// スプリットの条件を満たさなくなるまでスプリットする
			while(myHands.get(i).isSplit())
			{
				// myHandsの最後の要素として追加
				myHands.add(new BlackJackHand());
				// 出来たばかりの最後の要素に周回対象の手札の最後の1枚を渡す
				myHands.get(myHands.size()-1).add(myHands.get(i).passLastCard());
				// それぞれ1枚ずつ引く
				myHands.get(i).draw();
				myHands.get(myHands.size()-1).draw();
			}
		}
	}

	public void showHand(boolean dealer,int index)
	{
		if(dealer)
		{
			System.out.printf("dealer  : ");
		}
		else
		{
			System.out.printf("player%d : ",index);
		}
		this.myHands.get(index).showHand(dealer);
	}

	public void showHands(boolean dealer)
	{
		//全ての手札
		for(int i=0;i<myHands.size();i++)
		{
			showHand(dealer,i);
		}
	}
}
