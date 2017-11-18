package ui.swingUI.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.BeBackUpDirectory;
import ui.swingUI.label.BBDLabel;
import ui.swingUI.outlookui.CBScrollBarUI;
import ui.swingUI.size.ComponentSize;
import ui.swingUI.util.BinaryInsetList;

public class MyListScrollPane2<T extends Comparable<? super T>> {

	private List<T> list=new ArrayList<>();
	private JPanel panel=new JPanel();
	private JScrollPane jsp=new JScrollPane(panel);
	JFrame jf;
	
	public boolean add(T a){
		int index=BinaryInsetList.binaryInsert(list, a);
		((JLabel)a).setBorder(BorderFactory.createLineBorder(Color.black, 2));
		int listX=ComponentSize.getNOW_LIST_ELEMENTS_PANEL_SIZE_X();
		int listY=ComponentSize.getNOW_LIST_ELEMENTS_PANEL_SIZE_Y();
		((JLabel)a).setSize(new Dimension(listX,listY));
		panel.add((JLabel)a);
		for(int i=index-1;i<list.size();i++){
			((JLabel)a).setLocation(0, i*listY);
		}
		panel.repaint();
		System.out.println(list.size());
		return true;
	}
	
	public void addList(List<T> addList){
		for(T e:addList)
			panel.add((JLabel)e);
		list.addAll(addList);
		Collections.sort(list);
		int listX=ComponentSize.getNOW_LIST_ELEMENTS_PANEL_SIZE_X();
		int listY=ComponentSize.getNOW_LIST_ELEMENTS_PANEL_SIZE_Y();
		for(int i=0;i<list.size();i++){
			((JLabel)list.get(i)).setLocation(0, i*listY);
			((JLabel)list.get(i)).setSize(new Dimension(listX,listY));
		}
		jf.setVisible(true);
	}
	
	public boolean remove(T a){
		int index=Collections.binarySearch(list, a);
		list.remove(a);
		panel.remove((JLabel)a);
		int listY=ComponentSize.getNOW_LIST_ELEMENTS_PANEL_SIZE_Y();
		for(int i=index;i<list.size();i++){
			((JLabel)list.get(i)).setLocation(0, i*listY);
		}
		panel.repaint();
		return true;
	}
	
	public MyListScrollPane2(JFrame jf){
		this.jf=jf;
		list=new ArrayList<>();
		init();
	}
	
	private void init(){
		int listPanelX=ComponentSize.getNOW_LIST_PANEL_SIZE_X();
		int listPanelY=ComponentSize.getNOW_LIST_PANEL_SIZE_Y();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(listPanelX,2*listPanelY));
		//panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		//jsp.setPreferredSize(new Dimension(listPanelX,listPanelY));
		jsp.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUI(new CBScrollBarUI()); 
		jsp.repaint();
	}
	
	public void setBounds(int x,int y,int width,int height){
		panel.setPreferredSize(new Dimension(width,2*height));
		jsp.setBounds(x, y, width, height);
	}

	public JScrollPane getJsp() {
		return jsp;
	}
	
}
