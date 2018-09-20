package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** デジタル時計のフレーム部分担当 */
public class ClockFrame extends Frame implements Runnable, ActionListener
{
	/** 時計の文字盤 */
	private ClockDial clockDial;
	/* 文字ステータス */
	private String fontName = "Dialog";
	private int fontStyle = Font.PLAIN;
	private int fontSize = 14;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor = Color.WHITE;
	/* メニューバー */
	private MenuBar menuBar;
	private Menu menuFile;
	private MenuItem itemExit;
	private Menu menuEdit;
	private MenuItem itemEdit;
	/* ダイアログ */
	private ClockDialog dialog;
	
	
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
        
        //ダイアログ
        this.dialog = new ClockDialog(this,fontName,fontStyle,fontSize,fontColor,backgroundColor);
        dialog.setSize(200,200);
        dialog.setVisible(false);
	}
	
	public void startClock()
	{
		
	}
	
    public void run()
    {
        while(true){
        	  //this.resize();
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
    		//描画準備(ダブルバッファリング)
    		Dimension size = getSize();
    		Image back = createImage(size.width, size.height);
    		Graphics buffer = back.getGraphics();
    		//描画処理
    		buffer.setColor(backgroundColor);
    		buffer.fillRect(0,0,size.width-1,size.height-1);
    		buffer.setFont(new Font(this.fontName,this.fontStyle,this.fontSize));
    		buffer.setColor(fontColor);
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
			dialog.setVisible(true);
		}
		else
		{
			System.out.println(e.getActionCommand());
		}
	}

}
