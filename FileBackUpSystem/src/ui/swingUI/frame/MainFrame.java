package ui.swingUI.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.swingUI.panel.HeadLinePanel;
import ui.swingUI.panel.LeftExtensionPanel;
import ui.swingUI.panel.MainPanel;
import ui.swingUI.panel.RightExtensionPanel;
import ui.swingUI.size.ComponentSize;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel headLine;
    private JPanel mainPanel;
    private JPanel leftExtensionPanel;
    private JPanel rightExtensionPanel;
    
    //左扩展
    //右扩展
    
    public MainFrame() {
    	this.setLayout(null);
    	headLine=new HeadLinePanel(this);
    	mainPanel=new MainPanel(this);
    	leftExtensionPanel=new LeftExtensionPanel();
    	rightExtensionPanel=new RightExtensionPanel();
        // 初始化窗体
        setResizable(false);
        // 将窗体设置成无标题栏的语句，setUndecorated();注意此语句一定要放在setVisible之前，否则会报错
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int frameX=ComponentSize.DEFULT_JFRAME_SIZE_X;
        int frameY=ComponentSize.DEFULT_JFRAME_SIZE_Y;
        setSize(frameX,frameY);
        add(headLine);
        add(mainPanel);
        add(leftExtensionPanel);
        add(rightExtensionPanel);
        // 设置窗体为屏幕的中央位置
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - frameX) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - frameY) / 2;
        this.setLocation(w, h);
        setVisible(true);
 
        // 为窗体添加鼠标事件
       
    }
    
    public static void main(String[] args) {
		new MainFrame();
	}
	
}
