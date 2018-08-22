package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.time.LocalDateTime;

public class MyCanvas extends Canvas {
	public void paint(Graphics g)
	{
		LocalDateTime time = LocalDateTime.now();	//@ Œ»İæ“¾
        int h = time.getHour();
        int m = time.getMinute();
        int s = time.getSecond();
		g.drawString(h+":"+m+":"+s, 100, 100);
		//g.drawRect(100, 100, 150, 100);	
	}
}
