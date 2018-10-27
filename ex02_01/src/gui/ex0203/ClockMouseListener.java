package gui.ex0203;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JWindow;

public class ClockMouseListener extends MouseAdapter
{
	private final Point startPt = new Point();
	private JWindow window;
	  
	@Override
	public void mousePressed(MouseEvent event)
	{
		System.out.println(event.getButton());
		switch (event.getButton())
		{
			case MouseEvent.BUTTON1:
				GetLocation(event);
				break;
			case MouseEvent.BUTTON3:
				ShowConfigMenu(event);
				break;
			default:
				break;
		}
	}
	  
	@Override
	public void mouseDragged(MouseEvent event)
	{
		switch (event.getButton())
		{
			case MouseEvent.NOBUTTON:
				MoveWindow(event);
				break;
			default:
				break;
		}
	}
	
	// 左クリック
	private void GetLocation(MouseEvent event)
	{
		if (window == null) {
			Object o = event.getSource();
			if (o instanceof JWindow)
			{
				window = (JWindow) o;
			}
		}
		startPt.setLocation(event.getPoint());
	}
	private void MoveWindow(MouseEvent event)
	{
		if (window != null)
		{
			Point eventLocationOnScreen = event.getLocationOnScreen();
			int pointX = eventLocationOnScreen.x - startPt.x;
			int pointY = eventLocationOnScreen.y - startPt.y;
			window.setLocation(pointX,pointY);
		}
	}
	
	//右クリック
	private void ShowConfigMenu(MouseEvent event)
	{
		
	}
}