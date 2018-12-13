package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Trump
{
	/** スーツ */
	private Suit suit;
	/** 数字 */
	private int number;
	/** 絵札か */
	private boolean face;
	
	public Trump(Suit suit,int number)
	{
		this.suit = suit;
		this.number = number;
	}
	
	/** トランプ用のデッキを取得 */
	public static List<Trump> GetDeck()
	{
		List<Trump> deck = new ArrayList<Trump>();
		deck.addAll(GetSuitGroup(Suit.HEARTS));
		deck.addAll(GetSuitGroup(Suit.DIAMONDS));
		deck.addAll(GetSuitGroup(Suit.CLUBS));
		deck.addAll(GetSuitGroup(Suit.SPADES));
		return deck;
	}
	
	public String GetSuit()
	{
		return this.suit.getInitial();
	}
	
	public int GetNumber()
	{
		return this.number;
	}
	
	/** スーツ1つ分のカードを取得 */
	private static List<Trump> GetSuitGroup(Suit suit)
	{
		List<Trump> deck = new ArrayList<Trump>();
		for(int i=1;i<=13;i++)
		{
			deck.add(new Trump(suit,i));
		}
		return deck;
	}
	
	private boolean IsFaceCard()
	{
		return this.number>=11;
	}

}
