package cardgame;

public enum Suit
{
	HEARTS("HEARTS","H"),
	DIAMONDS("DIAMONDS","D"),
	CLUBS("CLUBS","C"),
	SPADES("SPADES","S");

	private String name;
	private String initial;
	private Suit(String name, String initial)
	{
		this.name = name;
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
