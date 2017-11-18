package ui.swingUI.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.BeBackUpDirectory;
import ui.swingUI.abandoned.MyListScrollPane;
import ui.swingUI.label.BBDLabel;
import ui.swingUI.panel.MyListScrollPane2;
import ui.swingUI.util.*;

public class Test2 {
	
	public static void main(String[] args) {
		JFrame jf=new JFrame();
		jf.setBounds(575, 250, 800, 580); // 设置总界面的位置
		jf.setLayout(new FlowLayout());
		
		jf.add(new JLabel(){

			JLabel init(){
				this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				this.setPreferredSize(new Dimension(600, 600));
				this.repaint();
				return this;
			}
			
		/*	@Override
			public void print(Graphics g) {
				System.out.println(1);
				g.setColor(Color.BLACK);
				g.drawArc(50, 50, 100, 100, 90, 180);
				super.print(g);
			}*/

			@Override
			protected void paintComponent(Graphics g) {
				/*for(int i=0;i<10;i++)
					g.drawArc(50+i, 50+i, 100-i-i, 100-i-i, 90, 180);*/
				/*int x=50;
				int y=50;
				int width=200;
				int height=100;
				int thickness=5;
				
				if(width>height){
					int r=height>>1;
					int l=width-(r<<1);

					int xpr=x+r;
					int xpl=x+l;
					int hm2t=height-(thickness<<1);
					int ypt=y+thickness;
					
					g.setColor(Color.green);
					
					g.fillArc(x, y, height, height, 90, 180);
					g.fillRect(xpr, y, l, height);
					g.fillArc(xpl, y, height, height, -90, 180);
					
					g.setColor(Color.white);
					
					g.fillArc(x+thickness, ypt, hm2t, hm2t, 90, 180);
					g.fillRect(xpr, ypt, l, hm2t);
					g.fillArc(xpl+thickness, ypt, hm2t, hm2t, -90, 180);
					
					g.drawString("hi", 50, 50);
				
				}*/
				
				//DrawTriangle.drawDoubleIsoscelesTriangle(100,100,100,100,0.3,  g,Color.black,Color.green);
				DrawBar.drawBar(100, 100, 80, 200, g);
				//DrawTriangle.drawIsoscelesTriangle(100,100,100,400,  g);
				//DrawTriangle.line1(100,100,300,150,g);
				//DrawTriangle.line2(100,100,300,450,g);
				//	g.drawLine(100,100,200,100);
				//DrawTriangle.DrawBresenhamline(100,100,200,50);
				/*g.setColor(Color.green);
				g.fillArc(50, 50, 100, 100, 90, 180);
				g.fillRect(100, 50, 100, 100);
				g.fillArc(150, 50, 100, 100, -90, 180);
				g.setColor(Color.white);
				g.fillArc(60, 60, 80, 80, 90, 180);
				g.fillRect(100, 60, 100, 80);
				g.fillArc(160, 60, 80, 80, -90, 180);*/
				
				super.paintComponent(g);
			}
			
			
			
			
		}.init());
		
		jf.setResizable(false); // 让窗口大小不可改变
		jf.setVisible(true); //让窗口可见
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	static class MyCellRenderer extends JLabel implements ListCellRenderer {
	     public MyCellRenderer() {
	    	addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if(e.getClickCount()==2&&e.getButton()==MouseEvent.BUTTON1)
						System.out.println(1);
					super.mousePressed(e);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
				}
	    		 
			});
	    	 
	         setOpaque(true);
	     }
	     
	     

	     public Component getListCellRendererComponent(JList list,
	                                                   Object value,
	                                                   int index,
	                                                   boolean isSelected,
	                                                   boolean cellHasFocus) {

	         setText(value.toString());

	         Color background;
	         Color foreground;

	         // check if this cell represents the current DnD drop location
	         JList.DropLocation dropLocation = list.getDropLocation();
	         if (dropLocation != null
	                 && !dropLocation.isInsert()
	                 && dropLocation.getIndex() == index) {

	             background = Color.BLUE;
	             foreground = Color.WHITE;

	         // check if this cell is selected
	         } else if (isSelected) {
	             background = Color.RED;
	             foreground = Color.WHITE;

	         // unselected, and not the DnD drop location
	         } else {
	             background = Color.WHITE;
	             foreground = Color.BLACK;
	         };

	         setBackground(background);
	         setForeground(foreground);

	         return this;
	     }
	 }
	
}
