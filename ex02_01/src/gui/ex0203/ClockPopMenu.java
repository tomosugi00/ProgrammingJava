package gui.ex0203;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ClockPopMenu extends JPopupMenu
{
	// コンポーネント
	private JMenuItem fontConfigItem = new JMenuItem("フォント");
    private JMenuItem sizeConfigItem = new JMenuItem("サイズ");
    private JMenuItem boldConfigItem = new JMenuItem("太字");
    private JMenuItem colorConfigItem = new JMenuItem("カラー");
    private JMenuItem backgroundPanelConfigItem = new JMenuItem("背景色");
    
    public ClockPopMenu()
    {
    	
    	this.add(fontConfigItem);
    	this.add(sizeConfigItem);
    	this.add(boldConfigItem);
    	this.add(colorConfigItem);
    	this.add(backgroundPanelConfigItem);
    }
    
    
}
