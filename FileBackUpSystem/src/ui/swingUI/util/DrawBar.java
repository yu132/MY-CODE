package ui.swingUI.util;

import java.awt.Graphics;

public class DrawBar {

	public static void drawBar(int x,int y,int width,int height,Graphics g){
		if(width>height){
			int r=height>>1;
			int l=width-(r<<1);
	
			int xpr=x+r;
			int xpl=x+l;
			
			g.fillArc(x, y, height, height, 90, 180);
			g.fillRect(xpr, y, l, height);
			g.fillArc(xpl, y, height, height, -90, 180);
		}else{
			int r=width>>1;
			int l=height-(r<<1);
	
			int ypr=y+r;
			int ypl=y+l;
			
			g.fillArc(x, y, width, width, 0, 180);
			g.fillRect(x, ypr, width, l);
			g.fillArc(x, ypl-1, width, width, -180, 180);
		}
	}
}
