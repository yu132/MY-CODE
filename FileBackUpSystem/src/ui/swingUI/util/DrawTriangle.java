package ui.swingUI.util;

import java.awt.Color;
import java.awt.Graphics;

public class DrawTriangle {

	private static void drawTriangle(int x1, int y1, int x2, int y2,boolean positive,Graphics g) {//ֱ�߻����㷨
		if(y2-y1<x2-x1){
			int x, y, d0, d1, d2, a, b;
			y = y1;
			a = y1 - y2; // ֱ�߷����е�a���㷨
			b = x2 - x1; // ֱ�߷����е�b���㷨
			d0 = 2 * a + b; // ������ʼֵ
			d1 = 2 * a; // ��>=0ʱ������
			d2 = 2 * (a + b); // ��<0ʱ������
			for (x = x1; x <= x2; x++) {
				// putpixel(x,y,GREEN); //����
				System.out.println(x+" "+y);
				if(positive)
					g.drawLine(2*x1-x, y, x, y);
				else
					g.drawLine(x, y, 2*x2-x, y);
				if (d0 < 0) {
					y++;
					d0 += d2;
				} else 
					d0 += d1;
			}
		}else{
			int y, x, d0, d1, d2, a, b;
			x = x1;
			a = x1 - x2; // ֱ�߷����е�a���㷨
			b = y2 - y1; // ֱ�߷����е�b���㷨
			d0 = 2 * a + b; // ������ʼֵ
			d1 = 2 * a; // ��>=0ʱ������
			d2 = 2 * (a + b); // ��<0ʱ������
			//System.out.println(d1+" "+d2);
			for (y = y1; y <= y2; y++) {
				if(positive)
					g.drawLine(2*x1-x, y, x, y);
				else
					g.drawLine(x, y, 2*x2-x, y);
				if (d0 < 0) {
					x++;
					d0 += d2;
				} else {
					d0 += d1;
				}

			}
		}
	}
	
	public static void drawIsoscelesTriangle(int x, int y,int l, int h,Graphics g){//h����Ϊ��������Ϊ��
		if(h<0){
			drawTriangle(x+l/2,y+h,x+l,y,true,g);
		}else if(h>0){
			drawTriangle(x,y,x+l/2,y+h,false,g);
		}else{
			g.drawLine(x, y, x+l, y);
		}
	}
	
	public static void drawDoubleIsoscelesTriangle(int x, int y,int l, int h,double ratio,Graphics g,Color outColor,Color inColor){//h����Ϊ��������Ϊ��
		if(ratio<=0||ratio>=1)
			return;
		if(h<0){
			g.setColor(outColor);
			drawIsoscelesTriangle(x,y,l,h,g);
			g.setColor(inColor);
			drawIsoscelesTriangle((int)(x+(l/2*(1-ratio))),y,(int)(l*ratio),(int)(h*ratio),g);
		}else if(h>0){
			g.setColor(outColor);
			drawIsoscelesTriangle(x,y,l,h,g);
			g.setColor(inColor);
			drawIsoscelesTriangle((int)(x+(l/2*(1-ratio))),y,(int)(l*ratio),(int)(h*ratio),g);
		}else{
			g.drawLine(x, y, x+l, y);
		}
	}
	
}
