package ui.swingUI.outlookui;  
  
  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.GradientPaint;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.Insets;  
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JComponent;  
import javax.swing.JScrollBar;  
import javax.swing.plaf.basic.BasicArrowButton;  
import javax.swing.plaf.basic.BasicScrollBarUI;

import ui.swingUI.util.DrawBar;
import ui.swingUI.util.DrawTriangle;  
  
public class CBScrollBarUI extends BasicScrollBarUI  
{  
    private Color frameColor = new Color(145, 105, 55);  
  
    public Dimension getPreferredSize(JComponent c)  
    {  
        return new Dimension(16, 16);  
    }  
  
    // �ػ�������Ļ���  
    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)  
    {  
        super.paintThumb(g, c, thumbBounds);  
        int tw = thumbBounds.width;  
        int th = thumbBounds.height;  
        // �ض�ͼ�������ĵ�ԭ�㣬���һ��Ҫд����Ȼ������϶�����ʱ���鲻��������  
        g.translate(thumbBounds.x, thumbBounds.y);  
  
        Graphics2D g2 = (Graphics2D) g;  
        GradientPaint gp = null;  
     //   Image arrowImg = new ImageIcon("img/block.png").getImage();
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)  
        {  
            gp = new GradientPaint(0, 0, new Color(242, 222, 198), tw, 0, new Color(207, 190, 164));  
        }  
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)  
        {  
            gp = new GradientPaint(0, 0, new Color(242, 222, 198), 0, th, new Color(207, 190, 164));  
        }  
        //g2.setPaint(gp);  
       // g2.drawImage(arrowImg,0,0,thumbBounds.width-1,thumbBounds.height-1,Color.BLACK,null);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(-1, -1, tw+1 , th+1 , 5, 5);  
        g2.setColor(Color.black);
        DrawBar.drawBar(0, 0, thumbBounds.width-1, thumbBounds.height-1, g2);
       // g2.fillRoundRect(0, 0, tw - 1, th - 1, 5, 5);  
      //  g2.setColor(frameColor);  
       // g2.drawRoundRect(0, 0, tw - 1, th - 1, 5, 5);  
    }  
  
    // �ػ滬��Ļ������򱳾�  
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)  
    {  
        Graphics2D g2 = (Graphics2D) g;  
        GradientPaint gp = null;  
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)  
        {  
            gp = new GradientPaint(0, 0, new Color(198, 178, 151), trackBounds.width, 0, new Color(234, 214, 190));  
        }  
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)  
        {  
            gp = new GradientPaint(0, 0, new Color(198, 178, 151), 0, trackBounds.height, new Color(234, 214, 190));  
        }  
      //  g2.setPaint(gp);  
      //  g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);  
      //  g2.setColor(new Color(175, 155, 95));  
      //  g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1);  
      //  Image arrowImg = new ImageIcon("img/track.png").getImage();
      //  g2.drawImage(arrowImg,trackBounds.x,trackBounds.y,trackBounds.width,trackBounds.height,Color.BLACK,null);
        g2.setColor(Color.WHITE);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);  
        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT)  
            this.paintDecreaseHighlight(g);  
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT)  
            this.paintIncreaseHighlight(g);  
    }  
  
    // �ػ浱��������������ϻ�����ť֮�������  
    protected void paintDecreaseHighlight(Graphics g)  
    {  
        g.setColor(Color.green);  
        int x = this.getTrackBounds().x;  
        int y = this.getTrackBounds().y;  
        int w = 0, h = 0;  
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)  
        {  
            w = this.getThumbBounds().width;  
            h = this.getThumbBounds().y - y;  
  
        }  
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)  
        {  
            w = this.getThumbBounds().x - x;  
            h = this.getThumbBounds().height;  
        }  
        //g.fillRect(x, y, w, h);  
    }  
  
    // �ػ浱��������������»����Ұ�ť֮�������  
    protected void paintIncreaseHighlight(Graphics g)  
    {  
        Insets insets = scrollbar.getInsets();  
        g.setColor(Color.blue);  
        int x = this.getThumbBounds().x;  
        int y = this.getThumbBounds().y;  
        int w = this.getTrackBounds().width;  
        int h = this.getTrackBounds().height;  
      //  g.fillRect(x, y, w, h);  
    }  
  
    protected JButton createIncreaseButton(int orientation)  
    {  
        return new BasicArrowButton(orientation)  
        {  
            // �ػ水ť�����Ǳ��  
            public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled)  
            {  
                Graphics2D g2 = (Graphics2D) g;  
                GradientPaint gp = null;  
                Image arrowImg = null;  
             /*   switch (this.getDirection())  
                {  
                case BasicArrowButton.SOUTH:  
                    gp = new GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new Color(207, 190, 164));  
                    arrowImg = Toolkit.getDefaultToolkit().getImage("img/arrowdown.png");//ImageLoader.get("south.gif");  
                    break;  
                case BasicArrowButton.EAST:  
                    gp = new GradientPaint(0, 0, new Color(242, 222, 198), 0, getHeight(), new Color(207, 190, 164));  
                    arrowImg = Toolkit.getDefaultToolkit().getImage("img/arrowright.png");//ImageLoader.get("east.gif");  
                    break;  
                }  */
                gp = new GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new Color(207, 190, 164));  
              //  g2.setPaint(gp);  
              //  g2.fillRect(0, 0, getWidth(), getHeight());  
              //  g2.setColor(frameColor);  
              //  g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);  
               // g2.drawImage(arrowImg, (getWidth() - 2) / 2 -7, (getHeight() - 2) / 2 -7, 16, 16, null);  
             //   arrowImg = new ImageIcon("img/arrowdown.png").getImage();
              //  g2.drawImage(arrowImg,0,0,getWidth(),getHeight(),Color.BLACK,null);
                DrawTriangle.drawDoubleIsoscelesTriangle(0, 4, getWidth(), getHeight()-4, 0.5, g2, Color.black, Color.white);
            }  
        };  
    }  
  
    protected JButton createDecreaseButton(int orientation)  
    {  
        return new BasicArrowButton(orientation)  
        {  
            public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled)  
            {  
                Graphics2D g2 = (Graphics2D) g;  
                GradientPaint gp = null;  
                Image arrowImg = null;  
               /* switch (this.getDirection())  
                {  
                case BasicArrowButton.NORTH:  
                    gp = new GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new Color(207, 190, 164));  
                    arrowImg = Toolkit.getDefaultToolkit().getImage("img/arrowup.png");//ImageLoader.get("north.gif");  
                    break;  
                case BasicArrowButton.WEST:  
                    gp = new GradientPaint(0, 0, new Color(242, 222, 198), 0, getHeight(), new Color(207, 190, 164));  
                    arrowImg = Toolkit.getDefaultToolkit().getImage("img/arrowleft.png");//ImageLoader.get("west.gif");  
                    break;  
                } */ 
                gp = new GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new Color(207, 190, 164));
                arrowImg = new ImageIcon("img/arrowup.png").getImage();
                g2.setPaint(gp);  
               // g2.fillRect(0, 0, getWidth(), getHeight());  
               // g2.setColor(frameColor);  
               // g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);  
                //g2.drawImage(arrowImg, (getWidth() - 2) / 2 -7, (getHeight() - 2) / 2 -7, 16, 16, null);  
              //  g2.drawImage(arrowImg,0,0,getWidth(),getHeight(),Color.BLACK,null);
                DrawTriangle.drawDoubleIsoscelesTriangle(0, getHeight()-4, getWidth(), -getHeight()+4, 0.5, g2, Color.black, Color.white);
            }  
        };  
    }  
}  