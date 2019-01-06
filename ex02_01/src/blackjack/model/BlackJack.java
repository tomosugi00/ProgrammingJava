package blackjack.model;

import java.util.function.Consumer;

public class BlackJack
{
	private EventHandler onChanged;
	private GameStatus status;
	
	public BlackJack()
	{
		onChanged = new EventHandler();
		status = GameStatus.READY;
	}
	
	public String getStatus()
	{
		return status.toString();
	}
	
	/** ゲームスタート */
	public void start()
	{
		if(status!=GameStatus.READY)
		{
			return;
		}
		System.out.println("スタート");
		
		status = GameStatus.PL_DRAW;
		onChanged.broadcast(null);
	}
	/** スプリット処理 */
	public void split()
	{
		if(status!=GameStatus.PL_SPLIT)
		{
			return;
		}
		System.out.println("スプリット");
		status = GameStatus.PL_DRAW;
		onChanged.broadcast(null);
	}
	/** ドロー処理 */
	public void draw()
	{
		if((status!=GameStatus.PL_SPLIT)&&(status!=GameStatus.PL_DRAW))
		{
			return;
		}
		System.out.println("ドロー");
		onChanged.broadcast(null);
	}
	/** フィニッシュ処理 */
	public void finish()
	{
		if(status!=GameStatus.PL_DRAW)
		{
			return;
		}
		System.out.println("フィニッシュ");
		status = GameStatus.DE_DRAW;
		onChanged.broadcast(null);
	}
	
	/** イベント1追加 */
	public void addOnChanged(Consumer<EventArgs> listener)
	{
		onChanged.add(listener);
	}
	/** イベント1削除 */
	public void removeOnChanged(Consumer<EventArgs> listener)
	{
		onChanged.remove(listener);
	}
}
