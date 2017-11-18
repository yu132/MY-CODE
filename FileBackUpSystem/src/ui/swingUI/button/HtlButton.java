package ui.swingUI.button;

import java.awt.Color;

import javax.swing.JLabel;

import ui.swingUI.size.ComponentSize;
import ui.swingUI.size.IntervalSize;

public class HtlButton extends JLabel{

	private static final long serialVersionUID = 1L;

	public HtlButton() {
		super();
		this.setOpaque(true);
		this.setText("htl");
		this.setBackground(Color.ORANGE);
		this.setBounds(IntervalSize.getInterval_1_Size_X()+ComponentSize.getNOW_STRING_LABEL_SIZE_X()+IntervalSize.getInterval_3_Size_X() - 10,
				IntervalSize.getInterval_2_Size_Y()+ComponentSize.getNOW_TO_PATH_SIZE_Y()+2*IntervalSize.getInterval_3_Size_Y()+ComponentSize.getNOW_LIST_PANEL_SIZE_Y(),
				ComponentSize.getNOW_3_BUTTON_SIZE_X(), ComponentSize.getNOW_3_BUTTON_SIZE_Y());
	}
}
