package cardgame_gui;

import java.util.Scanner;

import javax.net.ssl.HandshakeCompletedEvent;

public class BlackJack
{
	private Player dealer;
	private Player player;

	public BlackJack()
	{
		this.dealer = new Player();
		this.player = new Player();
	}

	/** ゲームのスタート処理 */
	public void start()
	{
		// dealer 2枚引く
		dealer.draw(0);
		dealer.draw(0);
		// player 2枚引く
		player.draw(0);
		player.draw(0);

		//確認用。後で消す
		//showGameTable();

		//プレイヤー側：スプリット
		player.split();

		//現在のテーブルを表示
		showGameTable(true);	
	}

	/** プレイヤーサイドの処理 */
	public void playerTurn()
	{
		System.out.println("===<プレイヤーターン>===\n");
		
		// スプリットした数だけ
		for(int i=0;i<player.getNumberOfHands();i++)
		{
			//既にブラックジャックならスルー
			if(player.getHand(i).isBlackJack())
			{
				continue;
			}

			while(true)
			{
				// ゲームの状況を表示
				showGameTable(true);

				// 引くのをやめる?
				if(isFinished())
				{
					break;
				}
				// カードを引く
				player.draw(i);
				// バースト？
				if(player.getHand(i).isBurst(false))
				{
					break;
				}
			}
		}
	}

	/** カードを引くか */
	private boolean isFinished()
	{
		System.out.println("カードを引きますか？ (y/n)");
		Scanner scan = new Scanner(System.in);
		String input = null;
		while (true)
		{
			input = scan.next();
			if(input.equals("y")||input.equals("n"))
			{
				break;
			}
		}
		return input.equals("n");
	}

	/** */
	private void showGameTable(boolean game)
	{
		System.out.println("------------");
		dealer.showHand(game,0);
		player.showHands(false);
		System.out.println("------------");
	}

	/** ディーラーサイドの処理 */
	public void dealerTurn()
	{
		System.out.println("===<ディーラーターン>===\n");
		while(dealer.getHand(0).getSum(true)<17)
		{
			// 1枚引く
			dealer.draw(0);
			// バーストした？
			if(dealer.getHand(0).isBurst(true))
			{
				break;
			}
		}
	}

	/** 勝敗判定 */
	public void jadge()
	{
		System.out.println("===<勝敗>===\n");
		showGameTable(false);
		
		
	}
}
