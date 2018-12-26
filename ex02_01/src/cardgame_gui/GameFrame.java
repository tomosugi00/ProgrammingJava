package cardgame_gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends Frame implements ActionListener
{
	/* メニューバー */
	private MenuBar menuBar;
	private Menu menuFile;
	private MenuItem itemStart;
	private MenuItem itemExit;
	
	public GameFrame()
	{
		super("Black Jack");
		this.setSize(600,400);
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
}
