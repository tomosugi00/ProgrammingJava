package blackjack.model;

import java.util.function.Consumer;

public class BlackJack
{
	/* イベントハンドラー */
	private EventHandler onChanged;
	/* ゲームの進行状況管理 */
	private GameStatus status;
	private String table;
	/* ディーラー&プレイヤー */
	private Player dealer;
	private Player player;
	
	public BlackJack()
	{
		onChanged = new EventHandler();
		status = GameStatus.READY;
		table = new String();
		this.dealer = new Player();
		this.player = new Player();
	}
	
	public String getStatus()
	{
		return status.toString();
	}
	public String getTable()
	{
		return table.toString();
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
		
		dealer.draw(0);
		dealer.draw(0);
		// player 2枚引く
		player.draw(0);
		player.draw(0);
		
		this.updateGameTable(false);
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
	
	private void updateGameTable(boolean game)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(dealer.showHand(game,0)+"\n");
		builder.append(player.showHands(false)+"\n");
		table = builder.toString();
	}
	
	/*---以下、後日実装-------------------*/
	
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
}
