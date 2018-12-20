package cardgame2;

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
		showGameTable();
		
		//プレイヤー側：スプリット
		player.split();
		
		//確認用。後で消す
		showGameTable();
		
	}

	/** プレイヤーサイドの処理 */
	public void playerTurn()
	{
		/*
		// スプリットした数だけ
		for(int i=0;i<player.hands.size();i++)
		{
			//既にブラックジャックならスルー
			if(player.hand[i].isBJ)
			{
				continue;
			}

			// ゲームの状況を表示
			showGameTable();

			// 引くのをやめる?
			if(isFinished())
			{
				continue;
			}
			// カードを引く
			player.hands[i].draw();
			// バースト？
			if(player.hands[i].isBurst)
			{
				continue;
			}
		}
		*/
	}

	/** */
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
		scan.close(); //いらないかも
		return input.equals("n");
	}

	/** */
	private void showGameTable()
	{
		System.out.println("------------");
		dealer.showHand(true,0);
		player.showHands(false);
		System.out.println("------------");
	}



	/** ディーラーサイドの処理 */
	public void dealerTurn()
	{
		/*
		while(dealer.getSumForDealer()<17)
		{
			// 1枚引く
			dealer.draw();

			// バーストした？
			if(dealer.isBurstForDealer())
			{
				break;
			}
		}
		*/
	}

	/** 勝敗判定 */
	public void jadge()
	{

	}
}
