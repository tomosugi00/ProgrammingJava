package gui.ex0202;

import javax.swing.JFrame;

public class ClockFrame extends JFrame
{
	private ClockPanel panel;
	
	public ClockFrame()
	{
		this.setName("デジタル時計");;
		this.setSize(300, 200);
		
		panel = new ClockPanel();
		add(panel);
		this.setVisible(true);
	}
}
