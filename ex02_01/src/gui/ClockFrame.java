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
        dialog.setSize(300,300);
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
    		Font f = new Font(this.fontName,this.fontStyle,this.fontSize);
    		FontMetrics fm = buffer.getFontMetrics(f);
    		
    		//描画処理
    		buffer.setColor(backgroundColor);
    		buffer.fillRect(0,0,size.width-1,size.height-1);
    		buffer.setFont(f);
    		buffer.setColor(fontColor);
    		String nowTime = clockDial.getClockTime();
    		int fontWidth = fm.stringWidth(nowTime);
    		int fontHeight = fm.getHeight();
    		buffer.drawString(clockDial.getClockTime(),10,70);
    		//リサイズ
    		this.setSize(10+fontWidth,70+fontHeight);
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
			
			this.fontName = this.dialog.getFontName();
			this.fontStyle = this.dialog.getFontStyle();
			this.fontSize = this.dialog.getFontSize();
			this.fontColor = this.dialog.getFontColor();
			this.backgroundColor = this.dialog.getBackgroundColor();
		}
		else
		{
			System.out.println(e.getActionCommand());
		}
	}

}
