package myUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowTimeString {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH£ºmm£ºss");
	
	public static String getNowTimeString() {
		return formatter.format(new Date(System.currentTimeMillis()));
	}
	
	public static void main(String[] args) {
		System.out.println(getNowTimeString());
	}
}
