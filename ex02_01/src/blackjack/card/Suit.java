package blackjack.card;

public enum Suit
{
	HEARTS("H"),
	DIAMONDS("D"),
	CLUBS("C"),
	SPADES("S"),
	JOKER("J");

	private String initial;
	private Suit(String initial)
	{
		this.initial = initial;
	}
	public String getName()
	{
		return this.name();
	}
	public String getInitial()
	{
		return this.initial;
	}
}
