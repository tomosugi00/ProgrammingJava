package gui.ex0202;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPanel extends JPanel implements ActionListener
{
	final static int TIME = 1000;
	
	/* 文字ステータス */
	private String pFontName;
	private int pFontStyle;
	private int pFontSize;
	private Color pFontColor;
	private Color pBackgroundColor;
	
	public ClockPanel(String pFontName,int pFontStyle,int pFontSize,Color pFontColor,Color pBackgroundColor)
    {
		//パラメータ初期化
        this.pFontName = pFontName;
        this.pFontStyle = pFontStyle;
        this.pFontSize = pFontSize;
        this.pFontColor = pFontColor;
        this.pBackgroundColor = pBackgroundColor;
        
		setBackground(this.pBackgroundColor);
		new Timer(TIME, this).start();
	}

	/** 定期的なアクション*/
    public void actionPerformed(ActionEvent actionEvent)
    {
    	try
    	{
    		repaint();
    	}
    	catch(NullPointerException e)
    	{
    		
    	}
    }

    /** 描画処理 */
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	// 描画内容
    	String dial = ClockDial.getClockTime();
    	
    	// ダブルバッファリング
    	Dimension size = getSize();
		Image back = createImage(size.width, size.height);
		Graphics buffer = back.getGraphics();
    	Font f = new Font(this.pFontName,this.pFontStyle,this.pFontSize);
    	FontMetrics fm = g.getFontMetrics(f);
    	
    	buffer.setColor(this.pBackgroundColor);
    	buffer.fillRect(0,0,size.width-1,size.height-1);
    	
    	buffer.setFont(f);
    	buffer.setColor(this.pFontColor);
    	
    	int pointX = (getWidth() - fm.stringWidth(dial)) / 2;
    	int pointY =  (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
    	buffer.drawString(dial, pointX,pointY);
    	
    	g.drawImage(back, 0,0,this);
    }
    
	public void setFontName(String fontName)
	{
		this.pFontName=fontName;
	}
	public void setFontStyle(int fontStyle)
	{
		this.pFontStyle = fontStyle;
	}
	public void setFontSize(int fontSize)
	{
		this.pFontSize = fontSize;
	}
	public void setFontColor(Color fontColor)
	{
		this.pFontColor = fontColor;
	}
	public void setBackgroundColor(Color backGroundColor)
	{
		this.pBackgroundColor = backGroundColor;
	}
}
