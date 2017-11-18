package ui.swingUI.abandoned;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.swingUI.label.BBDLabel;

public class MyListScrollPane {

	private static final long serialVersionUID = 1L;
	
	private List<BBDLabel> list;
	private JPanel panel=new JPanel();
	private JScrollPane jsp=new JScrollPane(panel);
	JFrame jf;
	GridBagConstraints c ;
	GridBagLayout gridbag;
	
	public void addBBDLabel(BBDLabel jLabel){
		gridbag.setConstraints(jLabel, c);
		list.add(jLabel);
		panel.add(jLabel);
		panel.setPreferredSize(new Dimension(300, 100*list.size()));
		//panel.setLayout(new FlowLayout());
		System.out.println(list.size());
		//panel.repaint();
		jf.setVisible(true);
	}
	
	public void remove(BBDLabel jLabel){
		list.remove(jLabel);
		panel.remove(jLabel);
		panel.setPreferredSize(new Dimension(300, 100*list.size()));
		//panel.setLayout(new GridLayout(list.size(),1));
		//panel.repaint();
		jf.setVisible(true);
	}
	
	public MyListScrollPane(JFrame jf) {
		this.jf=jf;
		list=new ArrayList<>();
		init();
	}

	private void init(){
		//panel.setLayout(new GridLayout(6, 1));
		gridbag = new GridBagLayout();
        c= new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
		panel.setLayout(gridbag);
		panel.setPreferredSize(new Dimension(300, 1000));
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		jsp.setBounds(0, 0, 362, 463);
		jsp.setPreferredSize(new Dimension(360,460));
		jsp.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	public MyListScrollPane(List<BBDLabel> list) {
		super();
		this.list = new ArrayList<BBDLabel>(list);
		init();
	}

	public JScrollPane getJsp() {
		return jsp;
	}	

}
