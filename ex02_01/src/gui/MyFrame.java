package gui;

import java.awt.Frame;
import java.awt.Graphics;
import java.time.LocalDateTime;

public class MyFrame extends Frame {
	static LocalDateTime time = LocalDateTime.now();	//　 現在時刻取得
	int h,m,s;
	MyFrame(String s){
		super(s);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		/*
		LocalDateTime time = LocalDateTime.now();	//　 現在時刻取得
        int h = time.getHour();
        int m = time.getMinute();
        int s = time.getSecond();
		g.drawString(h+":"+m+":"+s, 100, 100);
		*/
	}
}
