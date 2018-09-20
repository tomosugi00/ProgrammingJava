package gui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockDialog extends Dialog implements ItemListener
{
	/* 各パラメータ */
	private String fontName;
	private int fontStyle;
	private int fontSize;
	private Color fontColor;
	private Color backgroundColor;
	/* コンポーネント */
	Choice choiceFontName;
	Choice choiceFontStyle;
	Choice choiceFontSize;
	Choice choiceFontColor;
	Choice choiceBackgroundColor;
	
	public ClockDialog (Frame parent,String pFontName,int pFontStyle,int pFontSize,Color pFontColor,Color pBackgroundColor)
	{
        super(parent , true);
        this.setTitle("設定画面");
        this.setSize(300,300);
        setLayout(null);
        // 閉じ方はdisposeでいいのか？visible(false)ではないのか？
        this.addWindowListener(new WindowDisposeAdapter());
        
        //パラメータ初期化
        this.fontName = pFontName;
        this.fontStyle = pFontStyle;
        this.fontSize = pFontSize;
        this.fontColor = pFontColor;
        this.backgroundColor = pBackgroundColor;
        
        //コンポーネント初期化(初期値設定(select)は後日)
        this.choiceFontName = new Choice();
        this.choiceFontName.setBounds(20, 40, 100, 20);
        this.choiceFontName.add("Dialog");
        this.choiceFontName.add("DialogInput");
        this.choiceFontName.addItemListener(this);
        this.add(this.choiceFontName);
        
        this.choiceFontStyle = new Choice();
        this.choiceFontStyle.setBounds(20, 70, 100, 20);
        this.choiceFontStyle.add("普通");
        this.choiceFontStyle.add("太字");
        this.choiceFontStyle.addItemListener(this);
        this.add(this.choiceFontStyle);
        
        
        this.choiceFontSize = new Choice();
        this.choiceFontSize.setBounds(20, 100, 100, 20);
        this.choiceFontSize.add("14");
        this.choiceFontSize.add("24");
        this.choiceFontSize.addItemListener(this);
        this.add(this.choiceFontSize);
        
        this.choiceFontColor = new Choice();
        this.choiceFontColor.setBounds(20, 130, 100, 20);
        this.choiceFontColor.add("Black");
        this.choiceFontColor.add("Red");
        this.choiceFontColor.addItemListener(this);
        this.add(this.choiceFontColor);
        
        this.choiceBackgroundColor = new Choice();
        this.choiceBackgroundColor.setBounds(20, 160, 100, 20);
        this.choiceBackgroundColor.add("White");
        this.choiceBackgroundColor.add("Yellow");
        this.choiceBackgroundColor.addItemListener(this);
        this.add(this.choiceBackgroundColor);
        
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
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == choiceFontName)
		{
			
		}
		else if(e.getSource() == choiceFontStyle)
		{
			
		}
		else if(e.getSource() == choiceFontSize)
		{
			
		}
		else if(e.getSource() == choiceFontColor)
		{
			
		}
		else if(e.getSource() == choiceBackgroundColor)
		{
			
		}
	}
}
