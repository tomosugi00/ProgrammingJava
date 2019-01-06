package blackjack.model;

import java.util.function.Consumer;

public class BlackJack
{
	private EventHandler onChanged;
	
	public void draw()
	{
		System.out.println("ドロー");
	}
	
	public void finish()
	{
		System.out.println("フィニッシュ");
	}
	
	public void split()
	{
		System.out.println("スプリット");
	}
	
	public void addOnChanged(Consumer<EventArgs> listener)
	{
		onChanged.add(listener);
	}
	public void removeOnChanged(Consumer<EventArgs> listener)
	{
		onChanged.remove(listener);
	}
	private void fireOnChanged(EventArgs args)
	{
		onChanged.broadcast(args);
	}
}
