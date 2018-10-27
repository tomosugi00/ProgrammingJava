package gui.ex0203;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class DragWindowListener extends MouseAdapter
{
	private final Point startPt = new Point();
	  private JWindow window;
	  
	  @Override public void mousePressed(MouseEvent me)
	  {
	    if (window == null) {
	      Object o = me.getSource();
	      if (o instanceof JWindow) {
	        window = (JWindow) o;
	      }
	    }
	    startPt.setLocation(me.getPoint());
	  }
	  
	  @Override public void mouseDragged(MouseEvent me)
	  {
	    if (window != null) {
	      Point eventLocationOnScreen = me.getLocationOnScreen();
	      window.setLocation(eventLocationOnScreen.x - startPt.x,
	                         eventLocationOnScreen.y - startPt.y);
	    }
	  }
}

/*
private final Point startPt = new Point();
private Window window;
@Override public void mousePressed(MouseEvent me) {
  if (window == null) {
    Object o = me.getSource();
    if (o instanceof Window) {
      window = (Window) o;
    } else if (o instanceof JComponent) {
      window = SwingUtilities.windowForComponent(me.getComponent());
    }
  }
  startPt.setLocation(me.getPoint());
}
@Override public void mouseDragged(MouseEvent me) {
  if (window != null) {
    Point eventLocationOnScreen = me.getLocationOnScreen();
    window.setLocation(eventLocationOnScreen.x - startPt.x,
                       eventLocationOnScreen.y - startPt.y);
  }
}
*/