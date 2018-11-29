package gui.ex0203;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPanel extends JPanel implements ActionListener
{
	// 描画更新時間(ミリ秒)
	final static int TIME = 1000;
	
	/* 文字ステータス */
	private String pFontName;
	private int pFontStyle;
	private int pFontSize;
	private Color pFontColor;
	private Color pBackgroundColor;
	
	public ClockPanel(String pFontName,int pFontStyle,int pFontSize,Color pFontColor,Color pBackgroundColor)
    {
		// パネル生成
		setBackground(Color.WHITE);
		
		// 文字ステータス初期化
		this.pFontName = pFontName;
        this.pFontStyle = pFontStyle;
        this.pFontSize = pFontSize;
        this.pFontColor = pFontColor;
        this.pBackgroundColor = pBackgroundColor;
        
		// 時刻表示開始
		new Timer(TIME, this).start();
	}

    public void actionPerformed(ActionEvent actionEvent)
    {
    	repaint();
    }

    /** 描画処理 */
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	// 描画内容
    	String dial = ClockDial.getClockTime();
    	// 描画位置
    	FontMetrics fm = g.getFontMetrics();
    	int pointX = (getWidth() - fm.stringWidth(dial)) / 2;
    	int pointY =  (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
    	g.drawString(dial, pointX,pointY);
    }
    
    //　各プロパティ
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
