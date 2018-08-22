package gui;

import java.awt.*;
import java.time.*;

public class ex11 extends Frame implements Runnable
{
        static ex11 e = new ex11();
        static Thread clock = new Thread(e);
        LocalDateTime time ;
        int h;
        int m;
        int s;
        
    public static void main(String args[])
    {
        e.setSize(200, 100);
        e.setVisible(true);
        e.addWindowListener(new WinAdapter());
        clock.start();
    }
    public void run()
    {
        while(true){
        	  time = LocalDateTime.now();
              h = time.getHour();
              m = time.getMinute();
              s = time.getSecond();
              repaint();
              try
              {
                  clock.sleep(1000);
              }
              catch(InterruptedException e)
              {
            	  /*Å@ ïKê{ */
              }               
        }
    }
    public void paint(Graphics g)
    {
        g.drawString(h+":"+m+":"+s,50,50);
    }
}