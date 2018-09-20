package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** デジタル時計のフレーム部分担当 */
public class ClockFrame extends Frame implements Runnable, ActionListener
{
	/** 時計の文字盤 */
	private ClockDial clockDial;
	
	private MenuBar menuBar;
	private Menu menuFile;
	private MenuItem itemExit;
	private Menu menuEdit;
	private MenuItem itemEdit;
	
	public ClockFrame(int width, int height)
	{
		super("デジタル時計");
		this.setSize(width,height);
		this.setVisible(true);
        this.addWindowListener(new WindowCloseAdapter());
        this.clockDial = new ClockDial();
        
        //メニュー
        this.menuBar = new MenuBar();
        setMenuBar(menuBar);
        //「File」
        this.menuFile = new Menu("File");
        menuBar.add(menuFile);
        this.itemExit = new MenuItem("Exit");
        itemExit.addActionListener(this);
        menuFile.add(itemExit);
        //「Edit」
        this.menuEdit = new Menu("Edit");
        menuBar.add(menuEdit);
        this.itemEdit = new MenuItem("Edit");
        itemEdit.addActionListener(this);
        menuEdit.add(itemEdit);
        
	}
	
	public void startClock()
	{
		
	}
	
    public void run()
    {
        while(true){
              repaint();
              try
              {
                  Thread.sleep(1000);
              }
              catch(InterruptedException e)
              {
            	  /* エラー処理 */
              }               
        }
    }
	
	//文字盤のフォント・サイズなどはこちらで行う
    //ダブルバッファリングを行う
    public void paint(Graphics g)
    {
    	/* なぜか最初2回ほどNullPointerExceptionが出る */
    	/* 起動時すぐは時間を取得できない？ */
    	try
    	{
    		//描画処理
    		Dimension size = getSize();
    		Image back = createImage(size.width, size.height);
    		Graphics buffer = back.getGraphics();
    		buffer.drawString(clockDial.getClockTime(),100,100);
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
		if (e.getSource() == itemExit)
		{
			System.exit(0);
		}
		else if(e.getSource() == itemEdit)
		{
			System.out.println("editがクリックされました");
		}
		else
		{
			System.out.println(e.getActionCommand());
		}
	}

}
