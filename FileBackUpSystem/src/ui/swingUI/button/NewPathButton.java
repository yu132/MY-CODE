package ui.swingUI.button;

import java.awt.Color;
import java.awt.Font;

import ui.swingUI.size.ComponentSize;
import ui.swingUI.size.IntervalSize;

public class NewPathButton extends SpecialButton{

	private static final long serialVersionUID = 1L;
	
	static int[] a={27,52};

	public NewPathButton() {
		
		super(Color.GREEN,Color.WHITE,Color.black,0,0,ComponentSize.getNOW_R_3_BUTTON_SIZE_X(),ComponentSize.getNOW_R_3_BUTTON_SIZE_Y(),10,new Font("Microsoft YaHei UI", Font.BOLD,24),"NewPath",a);
		//this.setOpaque(true);
		//this.setBackground(Color.ORANGE);
		this.setBounds(IntervalSize.getInterval_1_Size_X()+ComponentSize.getNOW_STRING_LABEL_SIZE_X()+2*IntervalSize.getInterval_3_Size_X()+ComponentSize.getNOW_LIST_PANEL_SIZE_X() - 10,
				IntervalSize.getInterval_2_Size_Y()+ComponentSize.getNOW_TO_PATH_SIZE_Y()+IntervalSize.getInterval_3_Size_Y()+IntervalSize.getInterval_4_Size_Y(),
				ComponentSize.getNOW_R_3_BUTTON_SIZE_X(),ComponentSize.getNOW_R_3_BUTTON_SIZE_Y());
	}
	
}
