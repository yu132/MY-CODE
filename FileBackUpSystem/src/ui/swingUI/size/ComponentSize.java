package ui.swingUI.size;

public class ComponentSize {
	
	public final static int DEFULT_JFRAME_SIZE_X=800;
	public final static int DEFULT_JFRAME_SIZE_Y=600;
	
	public final static double JFRAME_SIZE_X=1.0;
	public final static double JFRAME_SIZE_Y=1.0;
	
	public final static double HEADLINE_SIZE_X=1.0;
	public final static double HEADLINE_SIZE_Y=0.1;
	
	public final static double MAIN_PANEL_SIZE_X=1.0;
	public final static double MAIN_PANEL_SIZE_Y=0.9;
	
	public final static double LIST_PANEL_SIZE_X=0.4;
	public final static double LIST_PANEL_SIZE_Y=0.25;
	
	public final static double HANG_ON_LIST_PANEL_SIZE_X=0.4;
	public final static double HANG_ON_LIST_PANEL_SIZE_Y=0.15;
	
	public final static double LIST_ELEMENTS_PANEL_SIZE_X=0.4;
	public final static double LIST_ELEMENTS_PANEL_SIZE_Y=0.1;
	
	public final static double TO_PATH_SIZE_X=0.65;
	public final static double TO_PATH_SIZE_Y=0.05;
	
	public final static double STRING_LABEL_SIZE_X=0.1;
	public final static double STRING_LABEL_SIZE_Y=0.05;
	
	public final static double _3_BUTTON_SIZE_X=0.1;
	public final static double _3_BUTTON_SIZE_Y=0.1;
	
	public final static double R_3_BUTTON_SIZE_X=0.2;
	public final static double R_3_BUTTON_SIZE_Y=0.15;
	
	public static int NOW_JFRAME_SIZE_X;
	public static int NOW_JFRAME_SIZE_Y;
	
	static{
		NOW_JFRAME_SIZE_X=DEFULT_JFRAME_SIZE_X;
		NOW_JFRAME_SIZE_Y=DEFULT_JFRAME_SIZE_Y;
	}
	
	public static int getNOW_HEADLINE_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*HEADLINE_SIZE_X);
	}
	
	public static int getNOW_HEADLINE_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*HEADLINE_SIZE_Y);
	}
	
	public static int getNOW_MAIN_PANEL_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*MAIN_PANEL_SIZE_X)-20;
	}
	
	public static int getNOW_MAIN_PANEL_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*MAIN_PANEL_SIZE_Y);
	}
	
	public static int getNOW_LIST_PANEL_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*LIST_PANEL_SIZE_X);
	}
	
	public static int getNOW_LIST_PANEL_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*LIST_PANEL_SIZE_Y);
	}
	
	public static int getNOW_HANG_ON_LIST_PANEL_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*HANG_ON_LIST_PANEL_SIZE_X);
	}
	
	public static int getNOW_HANG_ON_LIST_PANEL_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*HANG_ON_LIST_PANEL_SIZE_Y);
	}
	
	public static int getNOW_LIST_ELEMENTS_PANEL_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*LIST_ELEMENTS_PANEL_SIZE_X);
	}
	
	public static int getNOW_LIST_ELEMENTS_PANEL_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*LIST_ELEMENTS_PANEL_SIZE_Y);
	}
	
	public static int getNOW_TO_PATH_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*TO_PATH_SIZE_X);
	}
	
	public static int getNOW_TO_PATH_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*TO_PATH_SIZE_Y);
	}
	
	public static int getNOW_STRING_LABEL_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*STRING_LABEL_SIZE_X);
	}
	
	public static int getNOW_STRING_LABEL_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*STRING_LABEL_SIZE_Y);
	}
	
	
	public static int getNOW_3_BUTTON_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*_3_BUTTON_SIZE_X);
	}
	
	public static int getNOW_3_BUTTON_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*_3_BUTTON_SIZE_Y);
	}
	
	public static int getNOW_R_3_BUTTON_SIZE_X(){
		return (int)(NOW_JFRAME_SIZE_X*R_3_BUTTON_SIZE_X);
	}
	
	public static int getNOW_R_3_BUTTON_SIZE_Y(){
		return (int)(NOW_JFRAME_SIZE_Y*R_3_BUTTON_SIZE_Y);
	}
}
