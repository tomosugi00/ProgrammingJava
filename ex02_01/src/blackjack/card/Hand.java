package blackjack.card;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
	/** 自分の山札 */
	protected Deck deck;
	protected List<Card> myHand;
	
	public Hand()
	{
		this.deck = Deck.getDeck();
		this.myHand = new ArrayList<Card>();
	}
	
	/* ここら辺呼び出し側中身弄り放題だと思う */
	/** 自分のカードを取得 */
	public List<Card> getMyHand()
	{
		return myHand;
	}
	
	/** カードを一枚引く */
	public Card draw()
	{
		Card card = this.deck.draw();
		if(card!=null)
		{
			myHand.add(card);
		}
		return card;
	}
	
	/** カードを一枚引く */
	public void add(Card card)
	{
		if(card!=null)
		{
			myHand.add(card);
		}
	}
	
	//これ無駄なメソッド。インデックスで指定できる方がいい
	/** 最後の要素のカードを渡す */
	public Card passLastCard()
	{
		int lastIndex = myHand.size()-1;
		Card card = myHand.get(lastIndex);
		myHand.remove(lastIndex);
		return card;
	}
}
