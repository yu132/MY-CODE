package ui.swingUI.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ui.swingUI.other.SpecialBorder;
import ui.swingUI.size.ComponentSize;

public class LeftExtensionPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public LeftExtensionPanel(){
		this.setBounds(0, ComponentSize.getNOW_HEADLINE_SIZE_Y(), 10 , ComponentSize.getNOW_MAIN_PANEL_SIZE_Y());
		//this.setBackground(Color.blue);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		this.setBorder(new SpecialBorder(Color.blue,"left"));
	}

}
