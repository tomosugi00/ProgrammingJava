package jpl.ch05.ex02;

import java.util.ArrayList;
import java.util.List;	// java.awt.Listではエラーになる

/* History内のもつリストに直接アクセスできないようにHistoryはネストしたクラスにしない方がいいと思われる */

public class BankAccount
{
	private long number;	// 口座番号
	private long balance;	// 現在の残高(単位：セント)
	private Action lastAct;	// 最後の処理
	private History actHistory = new History();
	
	public class Action
	{
		private String act;
		private long amount;
		Action(String act, long amount)
		{
			this.act = act;
			this.amount = amount;
		}
		public String toString()
		{
			// identify our enclosing account
			return number + ": " + act + " " + amount;
		}
	}
	
	public class History
	{
		private List<Action> actionArray = new ArrayList<Action>();
		
		/** 
		 * actionArrayの最後のActionオブジェクトを1つ返す
		 * その後その要素をactionArrayから削除する
		 * Listが空の場合、Nullを返す
		 */
		public Action next()
		{
			// 次の処理が適切に行われることを明示するためisEmpty()は使わない
			if(this.actionArray.size()-1<0)
			{
				return null;
			}
			Action lastAct = this.actionArray.get(this.actionArray.size()-1);
			this.actionArray.remove(this.actionArray.size()-1);
			return lastAct;
		}
		
		/** Actionインスタンスをリストに格納する
		 * 　 要素数が10の場合、先頭の要素を削除した上でリストの最後に追加する */
		public void addAction(Action act)
		{
			while(this.actionArray.size()>=10)
			{
				this.actionArray.remove(0);
			}
			this.actionArray.add(act);
		}
	}
	
	public History history()
	{
		return this.actHistory;
	}
	
	public void deposit(long amount)
	{
		this.balance += amount;
		this.lastAct = new Action("deposit",amount);
		this.actHistory.addAction(lastAct);
	}
	public void withdraw(long amount)
	{
		this.balance -= amount;
		this.lastAct = new Action("withdraw",amount);
		this.actHistory.addAction(lastAct);
	}
}
