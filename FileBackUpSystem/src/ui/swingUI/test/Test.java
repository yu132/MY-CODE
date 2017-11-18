package ui.swingUI.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

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

public class Test {
	
	public static void main(String[] args) {
		JFrame jf=new JFrame();
		jf.setBounds(575, 250, 800, 580); // 设置总界面的位置
		jf.setLayout(new FlowLayout());
		
		/*DefaultListModel<Point> dm=new DefaultListModel<>();
		dm.addElement(new Point(1, 1));
		dm.addElement(new Point(1, 1));
		
		JList<Point> jlist=new JList<>(dm);
		jlist.setCellRenderer(new MyCellRenderer());
		jlist.setPreferredSize(new Dimension(300, 300));
		jlist.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	            	JOptionPane.showMessageDialog(jf,  jlist.getSelectedValue(), null, JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
		
		
		*/
		
		
		
		/*jf.add(jlist);*/
		
	/*	MyListScrollPane mlp=new MyListScrollPane(jf);
		BBDLabel a;
		mlp.addBBDLabel(a=new BBDLabel(new BeBackUpDirectory("testpath", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.addBBDLabel(new BBDLabel(new BeBackUpDirectory("testpath2", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.addBBDLabel(new BBDLabel(new BeBackUpDirectory("testpath3", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.addBBDLabel(new BBDLabel(new BeBackUpDirectory("testpath4", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.addBBDLabel(new BBDLabel(new BeBackUpDirectory("testpath5", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.addBBDLabel(new BBDLabel(new BeBackUpDirectory("testpath6", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));*/
		
		MyListScrollPane2<BBDLabel> mlp=new MyListScrollPane2<>(jf);
		BBDLabel a;
		mlp.add(a=new BBDLabel(new BeBackUpDirectory("testpath", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		/*mlp.add(new BBDLabel(new BeBackUpDirectory("testpath2", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.add(new BBDLabel(new BeBackUpDirectory("testpath3", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.add(new BBDLabel(new BeBackUpDirectory("testpath4", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.add(new BBDLabel(new BeBackUpDirectory("testpath5", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		mlp.add(new BBDLabel(new BeBackUpDirectory("testpath6", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));*/
		
		JButton jb=new JButton("jb1");
		jb.addActionListener((e)->{
			mlp.remove(a);
		});
		
		jb.setPreferredSize(new Dimension(100, 100));
		
		JButton jb2=new JButton("jb2");
		jb2.addActionListener((e)->{
			mlp.add(new BBDLabel(new BeBackUpDirectory("testpath6", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		});
		
		jb2.setPreferredSize(new Dimension(100, 100));
		
		jf.add(mlp.getJsp());
		jf.add(jb);
		jf.add(jb2);
		
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
