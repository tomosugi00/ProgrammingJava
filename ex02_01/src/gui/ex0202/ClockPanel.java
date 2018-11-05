package gui.ex0202;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPanel extends JPanel implements ActionListener
{
	final static int TIME = 1000;
	
	public ClockPanel()
    {
		setBackground(Color.WHITE);
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
}
