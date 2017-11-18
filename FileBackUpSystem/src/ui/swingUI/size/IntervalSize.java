package ui.swingUI.size;

public class IntervalSize {
	
	public final static double interva1=0.1;
	public final static double interva2=0.1;
	public final static double interva3=0.05;
	public final static double interva4=0.025;
	
	public static int getInterval_1_Size_X(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_X*interva1);
	}
	
	public static int getInterval_1_Size_Y(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_Y*interva1);
	}
	
	public static int getInterval_2_Size_X(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_X*interva2);
	}
	
	public static int getInterval_2_Size_Y(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_Y*interva2);
	}
	
	public static int getInterval_3_Size_X(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_X*interva3);
	}
	
	public static int getInterval_3_Size_Y(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_Y*interva3);
	}
	
	public static int getInterval_4_Size_X(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_X*interva4);
	}
	
	public static int getInterval_4_Size_Y(){
		return (int)(ComponentSize.NOW_JFRAME_SIZE_Y*interva4);
	}
	
}

