package cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static boolean  win = false;
	
	public static void main(String[] args)
	{
		/* カード用意 */
		List<Trump> cardList = Trump.GetDeck();
		Deck<Trump> deck = new Deck<Trump>(cardList);
		deck.Shuffle();
		
		System.out.println("Game Start!");
		
		/* ディーラとユーザに2枚ずつ配る */
		List<Trump> dealer = new ArrayList<Trump>();
		List<Trump> player = new ArrayList<Trump>();
		
		
		dealer.add(deck.Draw());	//TODO 1枚目は見えないようにする
		dealer.add(deck.Draw());
		
		player.add(deck.Draw());
		player.add(deck.Draw());
		
		/* 画面に表示 */
		System.out.println("------------------");
		System.out.printf("dealer : [ ? ], [%s %d]\n", dealer.get(1).GetSuit(), dealer.get(1).GetNumber());
		System.out.printf("player : [%s %d], [%s %d]\n", player.get(0).GetSuit(), player.get(0).GetNumber(), player.get(1).GetSuit(), player.get(1).GetNumber());
		System.out.println("------------------");
		
		/* 続行する？ */
		 while(true)
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
        	//scan.close();
        
        	/* ユーザの意思(y/n)に合わせて処理を分岐 */
       		if(input.equals("y"))
       		{
       			/* カードを引く */
       			player.add(deck.Draw());
        		if(IsBurst(player))
        		{
       				break;
       			}
       			dealer.add(deck.Draw());
       			if(IsBurst(dealer))
       			{
       				win = true;
       				break;
       			}
        	}
        	else
       		{
       			/* スタンド */
       			int dealerSum = CardSum(dealer);
       			int playerSum = CardSum(player);
       			win = dealerSum<playerSum;
        		break;
        	}
        	System.out.println("------------------");
        	System.out.printf("dealer : [ ? ]");
       		for(int i = 1;i<dealer.size();i++)
       		{
       			System.out.printf(", [%s %d]", dealer.get(i).GetSuit(), dealer.get(i).GetNumber());
       		}
       		System.out.printf("\n");
       		System.out.printf("player : [%s %d]",player.get(0).GetSuit(), player.get(0).GetNumber());
       		for(int i = 1;i<dealer.size();i++)
       		{
        			System.out.printf(", [%s %d]", player.get(i).GetSuit(), player.get(i).GetNumber());
        	}
        	System.out.printf("\n");
       		System.out.println("------------------");
		 }
		
		 System.out.println("------------------");
		 System.out.printf("dealer : [%s %d]",dealer.get(0).GetSuit(), dealer.get(0).GetNumber());
    		for(int i = 1;i<dealer.size();i++)
    		{
    			System.out.printf(", [%s %d]", dealer.get(i).GetSuit(), dealer.get(i).GetNumber());
    		}
    		System.out.printf("\n");
    		System.out.printf("player : [%s %d]",player.get(0).GetSuit(), player.get(0).GetNumber());
    		for(int i = 1;i<player.size();i++)
    		{
     			System.out.printf(", [%s %d]", player.get(i).GetSuit(), player.get(i).GetNumber());
     	}
     	System.out.printf("\n");
    		System.out.println("------------------");
		 
		 if(win)
		 {
			 System.out.println("player win");
		 }
		 else
		 {
			 System.out.println("dealer win");
		 }
	}
	
	private static boolean IsBurst(List<Trump> list)
	{
		int sum=0;
		for(int i=0;i<list.size();i++)
		{
			sum += list.get(i).GetNumber();
		}
		return sum > 21;
	}
	
	private static int CardSum(List<Trump> list)
	{
		int sum=0;
		for(int i=0;i<list.size();i++)
		{
			sum += list.get(i).GetNumber();
		}
		return sum;
	}

}
