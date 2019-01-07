package blackjack.card;

public class Card
{
	/** スーツ */
	private Suit suit;
	/** 数字 */
	private int number;
	/** ジョーカーか */
	private boolean joker;

	private Card(Suit suit, int number)
	{
		this.suit = suit;
		if(number<1||number>13)
		{
			throw new IllegalArgumentException();
		}
		this.number = number;
		this.joker = this.suit==Suit.JOKER;
	}
	
	/** カードを取得する。<br>
	 *  numberに0以下または14以上の値を指定するとIllegalArgumentExceptionをスローする。 */
	static Card getCard(Suit suit, int number)
	{
		return new Card(suit,number);
	}

	/** スーツを取得 */
	public String getSuit()
	{
		return this.suit.getInitial();
	}
	/** 数字を取得 */
	public int getNumber()
	{
		return this.number;
	}
	/** 絵柄か判定 */
	public boolean isFace()
	{
		return this.number>=11;
	}
	/** ジョーカーか判定 */
	public boolean isJoker()
	{
		return this.joker;
	}
}
