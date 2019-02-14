package blackjack.view;

import java.awt.event.*;

public class WindowCloseAdapter extends WindowAdapter
{
	/** ウィンドウを閉じた時の処理 */
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}
