package blackjack.view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import blackjack.model.BlackJack;
import cardgame_gui.GameFrame;

public class MainView extends Frame implements ActionListener
{
	/* メニューバー */
	private MenuBar menuBar;
	private Menu menuFile;
	private MenuItem itemStart;
	private MenuItem itemExit;
	/* ボタン */
	private Button DrawButton;
	private Button FinishButton;
	private Button SplitButton;
	
	/* モデル層 */
	private BlackJack bj;
	
	public static void main(String[] args)
	{
		MainView view = new MainView();
	}
	
	public MainView()
	{
		this.createFrame();
		this.setEvent();
	}

	/** ゲームの進行状況を描写 */
	public void paint(Graphics g)
	{
		try
		{
			//描画準備(ダブルバッファリング)
			Dimension size = getSize();
			Image back = createImage(size.width, size.height);
			Graphics buffer = back.getGraphics();

			//描画処理

			//描画
			g.drawImage(back, 0, 0, this);
		}
		catch(NullPointerException e)
		{

		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == itemStart)
		{
			//ゲームスタート
		}
		else if(e.getSource() == itemExit)
		{
			//ゲーム終了
			System.exit(0);
		}
	}
	
	private void setEvent()
	{
		this.bj = new BlackJack();
	}
	
	private void createFrame()
	{
		this.setName("BlackJack");
		this.setSize(600,400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowCloseAdapter());
		this.setVisible(true);

		//メニュー
		this.menuBar = new MenuBar();
		setMenuBar(menuBar);
		//「File」
		this.menuFile = new Menu("File");
		menuBar.add(menuFile);
		//>>「Start」
		this.itemStart = new MenuItem("Start");
		itemStart.addActionListener(this);
		menuFile.add(itemStart);
		//>>「Exit」
		this.itemExit = new MenuItem("Exit");
		itemExit.addActionListener(this);
		menuFile.add(itemExit);

		// ボタン
		DrawButton = new Button("Draw");
		DrawButton.setBounds(100,300,50, 20);
		DrawButton.addActionListener(e ->
		{
			bj.draw();
		});
		this.add(DrawButton);
		DrawButton.setVisible(true);

		FinishButton = new Button("Finish");
		FinishButton.setBounds(200,300,50, 20);
		FinishButton.addActionListener(e ->
		{
			bj.finish();
		});
		this.add(FinishButton);
		FinishButton.setVisible(true);

		SplitButton = new Button("Split");
		SplitButton.setBounds(300,300,50, 20);
		SplitButton.addActionListener(e ->
		{
			bj.split();
		});
		this.add(SplitButton);
		SplitButton.setVisible(true);
	}
}