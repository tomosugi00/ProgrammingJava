package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockDialog extends Dialog
{
	/* 各パラメータ */
	private String fontName;
	private int fontStyle;
	private int fontSize;
	private Color fontColor;
	private Color backgroundColor;
	
	/* コンポーネント */
	
	
	public ClockDialog (Frame parent,String pFontName,int pFontStyle,int pFontSize,Color pFontColor,Color pBackgroundColor)
	{
        super(parent , true);
        this.setTitle("設定画面");
        this.setSize(100,100);
        // 閉じ方はdisposeでいいのか？visible(false)ではないのか？
        this.addWindowListener(new WindowDisposeAdapter());
        
        //パラメータ初期化
        this.fontName = pFontName;
        this.fontStyle = pFontStyle;
        this.fontSize = pFontSize;
        this.fontColor = pFontColor;
        this.backgroundColor = pBackgroundColor;
    }
	
	/** 閉じるボタンを押した時、リソースの開放を行う */
	class WindowDisposeAdapter extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {
	        dispose();
	    }
	}
	
	public String getFontName()
	{
		return this.fontName;
	}
	public int getFontStyle()
	{
		return this.fontStyle;
	}
	public int getFontSize()
	{
		return this.fontSize;
	}
	public Color getFontColor()
	{
		return this.fontColor;
	}
	public Color getBackgroundColor()
	{
		return this.backgroundColor;
	}
}
