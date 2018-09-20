package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockDialog extends Dialog
{
	public ClockDialog (Frame parent)
	{
        super(parent , true);
        this.setTitle("設定画面");
        this.setSize(100,100);
        // 閉じ方はdisposeでいいのか？visible(false)ではないのか？
        this.addWindowListener(new WindowDisposeAdapter());
    }
	
	/** 閉じるボタンを押した時、リソースの開放を行う */
	class WindowDisposeAdapter extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {
	        dispose();
	    }
	}
}
