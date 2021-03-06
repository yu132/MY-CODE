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
 * 无标题栏、无框窗口，皮肤自定义
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
 
        // 初始化窗体
        setResizable(false);
        // 将窗体设置成无标题栏的语句，setUndecorated();注意此语句一定要放在setVisible之前，否则会报错
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(510, 290);
        setVisible(true);
        add(pic);
 
        // 设置窗体为屏幕的中央位置
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 510) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 290) / 2;
        this.setLocation(w, h);
 
        // 为窗体添加鼠标事件
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                isDragged = false;
                // 为指定的光标设置光标图像
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
 
            public void mousePressed(MouseEvent e) {
                tmp = new Point(e.getX(), e.getY());
                isDragged = true;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR ));
            }
        });
 
        pic.addMouseMotionListener(new MouseMotionAdapter() {
            // 鼠标按键在组件上按下并拖动时调用。
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {
                    loc = new Point(getLocation().x + e.getX() - tmp.x,
                            getLocation().y + e.getY() - tmp.y);
                    setLocation(loc);
                }
            }
        });
    }
 
    // 获取图片的方法
    public Icon getIcon(String path) {
        URL url = PicFrame.class.getClassLoader().getResource(path);
        return new ImageIcon(url);
    }
 
    public static void main(String[] args) {
 
        new PicFrame();
 
    }
  
 
}

