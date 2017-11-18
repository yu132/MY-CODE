package ui.swingUI.other;

import javax.swing.JTextField;

import ui.swingUI.size.ComponentSize;
import ui.swingUI.size.IntervalSize;

public class ToPathTextField extends JTextField{

	private static final long serialVersionUID = 1L;

	public ToPathTextField() {
		super();
		this.setBounds(IntervalSize.getInterval_1_Size_X()+ComponentSize.getNOW_STRING_LABEL_SIZE_X()+IntervalSize.getInterval_3_Size_X() - 10,
				IntervalSize.getInterval_2_Size_Y(),
				ComponentSize.getNOW_TO_PATH_SIZE_X(), ComponentSize.getNOW_TO_PATH_SIZE_Y());
	}
	
}
