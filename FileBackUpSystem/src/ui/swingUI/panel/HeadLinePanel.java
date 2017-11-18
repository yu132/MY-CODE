package ui.swingUI.panel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.swingUI.other.SpecialBorder;
import ui.swingUI.size.ComponentSize;

public class HeadLinePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
	
	public HeadLinePanel(JFrame jf) {
		// this.setIcon(getIcon("theme.jpg"));
    	this.setBounds(0, 0, ComponentSize.getNOW_HEADLINE_SIZE_X(), ComponentSize.getNOW_HEADLINE_SIZE_Y());
    	this.setBackground(new Color(1,188,0));
    	this.setBorder(new SpecialBorder(Color.BLUE,"up"));
    	 this.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseReleased(MouseEvent e) {
                 isDragged = false;
                // setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             }
  
             public void mousePressed(MouseEvent e) {
                 tmp = new Point(e.getX(), e.getY());
                 isDragged = true;
               //  setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR ));
             }
         });
  
         this.addMouseMotionListener(new MouseMotionAdapter() {
             // 鼠标按键在组件上按下并拖动时调用。
             public void mouseDragged(MouseEvent e) {
                 if (isDragged) {
                     loc = new Point(jf.getLocation().x + e.getX() - tmp.x,
                             jf.getLocation().y + e.getY() - tmp.y);
                     jf.setLocation(loc);
                 }
             }
         });
	}
	
}
