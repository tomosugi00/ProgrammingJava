package blackjack.view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import blackjack.model.BlackJack;

public class MainView extends Frame implements ActionListener
{
	/* メニューバー */
	private MenuBar menuBar;
	private Menu menuFile;
	private MenuItem itemStart;
	private MenuItem itemExit;
	/* ボタン */
	private Button drawButton;
	private Button finishButton;
	private Button splitButton;
	/* ラベル */
	private Label statusLabel;
	/**/
	private Label gameTable;
	
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
			bj.start();
		}
		else if(e.getSource() == itemExit)
		{
			//ゲーム終了
			System.exit(0);
		}
	}
	
	/** イベントを追加 */
	private void setEvent()
	{
		this.bj = new BlackJack();
		bj.addOnChanged(e -> 
		{
			this.statusLabel.setText(bj.getStatus());
			this.gameTable.setText(bj.getTable());
			repaint();
		});
	}
	
	/** 画面生成 */
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
		
		// ラベル
		statusLabel = new Label("aaa");
		statusLabel.setBounds(20, 50, 100, 20);
		this.add(statusLabel);
		statusLabel.setVisible(true);

		gameTable = new Label("aaa");
		gameTable.setBounds(20, 0, 400, 200);
		this.add(gameTable);
		gameTable.setVisible(true);
		
		// ボタン
		drawButton = new Button("Draw");
		drawButton.setBounds(100,300,50, 20);
		drawButton.addActionListener(e ->
		{
			bj.draw();
		});
		this.add(drawButton);
		drawButton.setVisible(true);

		finishButton = new Button("Finish");
		finishButton.setBounds(200,300,50, 20);
		finishButton.addActionListener(e ->
		{
			bj.finish();
		});
		this.add(finishButton);
		finishButton.setVisible(true);

		splitButton = new Button("Split");
		splitButton.setBounds(300,300,50, 20);
		splitButton.addActionListener(e ->
		{
			bj.split();
		});
		this.add(splitButton);
		splitButton.setVisible(true);
	}
}