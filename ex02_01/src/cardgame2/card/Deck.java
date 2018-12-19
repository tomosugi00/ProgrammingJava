package cardgame2.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck
{
	/* 山札 */
	private static List<Card> deck;
	/* 一番上のカードの位置 */
	private static int index;

	private Deck()
	{
		deck = new ArrayList<Card>();
		deck.addAll(getSuitCardGroup(Suit.HEARTS));
		deck.addAll(getSuitCardGroup(Suit.DIAMONDS));
		deck.addAll(getSuitCardGroup(Suit.CLUBS));
		deck.addAll(getSuitCardGroup(Suit.SPADES));
		deck.addAll(getSuitCardGroup(Suit.JOKER));
		index = 0;
	}

	/** シャッフル */
	public void Shuffle()
	{
		Collections.shuffle(deck);
	}

	/** 一番上のカードを取得 */
	public Card draw()
	{
		if(isEmpty())
		{
			return null;
		}
		Card card = deck.get(index);
		index++;
		return card;
	}

	/* この仕様だとカード複製し放題な気がする。プレイヤーの手元にはカードが残ったままだし */
	/** デッキを元に戻してシャッフルする */
	public void reset()
	{
		index=0;
		this.Shuffle();
	}

	/** カードの残り枚数 */
	public int getRestOfTheNumber()
	{
		return deck.size() - index;
	}

	/** デッキのカードが切れたか判定 */
	public boolean isEmpty()
	{
		return getRestOfTheNumber()==0;
	}

	private List<Card> getSuitCardGroup(Suit suit)
	{
		List<Card> cardGroup = new ArrayList<Card>();
		if(suit==Suit.JOKER)
		{
			return getJokers(2);
		}
		for(int number=1;number<=13;number++)
		{
			try
			{
				cardGroup.add(Card.getCard(suit, number));
			}
			catch(Exception e)
			{
				return null;
			}
		}
		return cardGroup;
	}

	private List<Card> getJokers(int number)
	{
		List<Card> cardGroup = new ArrayList<Card>();
		for(int i=0;i<number;i++)
		{
			try
			{
				/* ジョーカーの数字をちゃんと決める */
				cardGroup.add(Card.getCard(Suit.JOKER, 1));
			}
			catch(Exception e)
			{
				return null;
			}
		}
		return cardGroup;
	}
}
