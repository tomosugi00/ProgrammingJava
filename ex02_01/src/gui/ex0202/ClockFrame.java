package gui.ex0202;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.ClockDialog;

public class ClockFrame extends JFrame implements ActionListener
{
	private ClockPanel panel;
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
	
	public ClockFrame()
	{
		this.setSize(200, 100);
		// なぜかなくても消せる
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// パネル
		panel = new ClockPanel();
		add(panel);
		this.setVisible(true);
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
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == itemExit)
		{
			System.exit(0);
		}
		else if(e.getSource() == itemEdit)
		{
			dialog.setVisible(true);
			
			// ダイアログの編集状態を取得
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
