package gui;

import java.awt.event.*;

public class WinAdapter extends WindowAdapter {
	/**　 ウィンドウを閉じようとしたときに呼び出される */
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}
