package gui.ex0203;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JWindow;

public class DragWindowListener extends MouseAdapter
{
	private final Point startPt = new Point();
	private JWindow window;
	  
	@Override
	public void mousePressed(MouseEvent event)
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
	  
	@Override
	public void mouseDragged(MouseEvent event)
	{
		if (window != null)
		{
			Point eventLocationOnScreen = event.getLocationOnScreen();
			int pointX = eventLocationOnScreen.x - startPt.x;
			int pointY = eventLocationOnScreen.y - startPt.y;
			window.setLocation(pointX,pointY);
		}
	}
}