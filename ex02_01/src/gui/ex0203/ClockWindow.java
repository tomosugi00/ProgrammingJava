package gui.ex0203;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JWindow;

public class ClockWindow extends JWindow
{
	private ClockPanel panel;
	public ClockPopMenu menu;
	
	/* 文字ステータス */
	private String fontName = "Dialog";
	private int fontStyle = Font.PLAIN;
	private int fontSize = 14;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor = Color.WHITE;
	
	public ClockWindow()
	{
		// ウィンドウ生成
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		this.setBackground(Color.BLACK);
		this.setSize(200, 100);
		// ドラッグ機能
		ClockMouseListener clockMouseListener = new ClockMouseListener(this);
		this.addMouseListener(clockMouseListener);
		this.addMouseMotionListener(clockMouseListener);
		//ポップアップメニュー生成
		this.menu = new ClockPopMenu();
		// 文字盤生成
		panel = new ClockPanel(fontName,fontStyle,fontSize,fontColor,backgroundColor);
		add(panel);
		this.setVisible(true);
	}
	
	public void SetConfig(int x,int y)
	{
		//System.out.println("aa");
		this.menu.setVisible(true);
	}
	public ClockPopMenu GetMenu()
	{
		return this.menu;
	}
}


