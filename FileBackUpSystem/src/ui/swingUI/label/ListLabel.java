package ui.swingUI.label;

import java.awt.Color;

import javax.swing.JLabel;

import ui.swingUI.size.ComponentSize;
import ui.swingUI.size.IntervalSize;

public class ListLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public ListLabel() {
		super();
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.setBounds(IntervalSize.getInterval_1_Size_X() - 10,
				IntervalSize.getInterval_2_Size_Y() + ComponentSize.getNOW_STRING_LABEL_SIZE_Y()
						+ IntervalSize.getInterval_3_Size_Y(),
				ComponentSize.getNOW_STRING_LABEL_SIZE_X(), ComponentSize.getNOW_LIST_PANEL_SIZE_Y());
		this.setText("list");
	}
}
