package gui.ex0203;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JWindow;

public class ClockWindow extends JWindow
{
	private ClockPanel panel;
	
	public ClockWindow()
	{
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		this.setBackground(Color.BLACK);
		this.setSize(200, 100);
		// ドラッグ機能
		ClockMouseListener clockMouseListener = new ClockMouseListener();
		this.addMouseListener(clockMouseListener);
		this.addMouseMotionListener(clockMouseListener);
		
		panel = new ClockPanel();
		add(panel);
		this.setVisible(true);
	}
}
