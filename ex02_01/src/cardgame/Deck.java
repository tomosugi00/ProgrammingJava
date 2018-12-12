package cardgame;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck<E>
{
	/* デッキ本体 */
	private List<E> deck;
	/* デッキの一番上のカードの位置 */
	private int index;
	/* デッキのカード枚数 */
	private int maxNumber;
	
	public Deck(List<E> cardList)
	{
		this.deck = new ArrayList<E>(cardList);
		index = 0;
		maxNumber = deck.size();
	}
	
	/** シャッフル */
	public void Shuffle()
	{
		Collections.shuffle(deck);
	}
	
	/** 一番上のカードを取得 */
	public E Draw()
	{
		if(IsEmpty())
		{
			return null;
		}
		E card = this.deck.get(this.index);
		index++;
		return card;
	}
	
	public void Reset()
	{
		index=0;
		this.Shuffle();
	}
	
	/** デッキのカードが切れたか判定 */
	private boolean IsEmpty()
	{
		return index>=this.maxNumber;
	}
	
}
