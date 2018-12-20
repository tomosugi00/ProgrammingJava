package cardgame2;

public class Flow
{
	private static BlackJack bj = new BlackJack();
	
	/* ゲームスタート */
	public static void main(String[] args)
	{
		bj.start();
		bj.playerTurn();
		//bj.dealerTurn();
		//bj.jadge();
	}
}
