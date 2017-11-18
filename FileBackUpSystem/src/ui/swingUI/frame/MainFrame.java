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
    
    //����չ
    //����չ
    
    public MainFrame() {
    	this.setLayout(null);
    	headLine=new HeadLinePanel(this);
    	mainPanel=new MainPanel(this);
    	leftExtensionPanel=new LeftExtensionPanel();
    	rightExtensionPanel=new RightExtensionPanel();
        // ��ʼ������
        setResizable(false);
        // ���������ó��ޱ���������䣬setUndecorated();ע������һ��Ҫ����setVisible֮ǰ������ᱨ��
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int frameX=ComponentSize.DEFULT_JFRAME_SIZE_X;
        int frameY=ComponentSize.DEFULT_JFRAME_SIZE_Y;
        setSize(frameX,frameY);
        add(headLine);
        add(mainPanel);
        add(leftExtensionPanel);
        add(rightExtensionPanel);
        // ���ô���Ϊ��Ļ������λ��
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - frameX) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - frameY) / 2;
        this.setLocation(w, h);
        setVisible(true);
 
        // Ϊ�����������¼�
       
    }
    
    public static void main(String[] args) {
		new MainFrame();
	}
	
}
