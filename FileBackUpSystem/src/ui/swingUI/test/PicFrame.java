package ui.swingUI.test;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
/**
 * �ޱ��������޿򴰿ڣ�Ƥ���Զ���
 */
public class PicFrame extends JFrame {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    private JPanel pic;
 
    public PicFrame() {
        pic = new JPanel();
       // pic.setIcon(getIcon("theme.jpg"));
        pic.setBounds(0, 0, 510, 50);
        pic.setBackground(Color.black);
 
        // ��ʼ������
        setResizable(false);
        // ���������ó��ޱ���������䣬setUndecorated();ע������һ��Ҫ����setVisible֮ǰ������ᱨ��
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(510, 290);
        setVisible(true);
        add(pic);
 
        // ���ô���Ϊ��Ļ������λ��
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 510) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 290) / 2;
        this.setLocation(w, h);
 
        // Ϊ�����������¼�
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                isDragged = false;
                // Ϊָ���Ĺ�����ù��ͼ��
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
 
            public void mousePressed(MouseEvent e) {
                tmp = new Point(e.getX(), e.getY());
                isDragged = true;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR ));
            }
        });
 
        pic.addMouseMotionListener(new MouseMotionAdapter() {
            // ��갴��������ϰ��²��϶�ʱ���á�
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {
                    loc = new Point(getLocation().x + e.getX() - tmp.x,
                            getLocation().y + e.getY() - tmp.y);
                    setLocation(loc);
                }
            }
        });
    }
 
    // ��ȡͼƬ�ķ���
    public Icon getIcon(String path) {
        URL url = PicFrame.class.getClassLoader().getResource(path);
        return new ImageIcon(url);
    }
 
    public static void main(String[] args) {
 
        new PicFrame();
 
    }
  
 
}

