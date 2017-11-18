package ui.swingUI.label;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import entity.BeBackUpDirectory;

public class BBDLabel extends JLabel implements Comparable<BBDLabel>{

	private static final long serialVersionUID = 1L;
	
	private BeBackUpDirectory bbd;

	public BBDLabel() {
		super();
		init();
	}

	public BBDLabel(BeBackUpDirectory bbd) {
		this.setText(bbd.getName());
		init();
		this.bbd = bbd;
	}
	private void init(){
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setPreferredSize(new Dimension(300, 100));
	}

	public BeBackUpDirectory getBbd() {
		return bbd;
	}

	public void setBbd(BeBackUpDirectory bbd) {
		this.setText(bbd.getName());
		this.bbd = bbd;
	}

	@Override
	public int compareTo(BBDLabel o) {
		return bbd.getName().compareTo(o.getBbd().getName());
	}

}
