package ui.swingUI.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class SpecialButton extends JLabel{

	private static final long serialVersionUID = 1L;
	
	private Color outColor;
	private Color inColor;
	private Color strColor;
	private int x;
	private int y;
	private int width;
	private int height;
	private int thickness;
	private Font font;
	private String str;
	private int[] strLocation;

	public SpecialButton(Color outColor, Color inColor, Color strColor, int x, int y, int width, int height,
			int thickness, Font font, String str, int[] strLocation) {
		super();
		this.outColor = outColor;
		this.inColor = inColor;
		this.strColor = strColor;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.thickness = thickness;
		this.font = font;
		this.str = str;
		this.strLocation = strLocation;
	}

	@Override
	protected void paintComponent(Graphics g) {
		//System.out.println("aa");
		Color old=g.getColor();
		Font oldFont=g.getFont();
		if(width>height){
			int r=height>>1;
			int l=width-(r<<1);

			int xpr=x+r;
			int xpl=x+l;
			int hm2t=height-(thickness<<1);
			int ypt=y+thickness;
			
			g.setColor(outColor);
			
			g.fillArc(x, y, height, height, 90, 180);
			g.fillRect(xpr, y, l, height);
			g.fillArc(xpl, y, height, height, -90, 180);
			
			g.setColor(inColor);
			
			g.fillArc(x+thickness, ypt, hm2t, hm2t, 90, 180);
			g.fillRect(xpr, ypt, l, hm2t);
			g.fillArc(xpl+thickness, ypt, hm2t, hm2t, -90, 180);
			
			g.setColor(strColor);
			g.setFont(font);
			
			g.drawString(str, strLocation[0], strLocation[1]);
		}
		g.setFont(oldFont);
		g.setColor(old);
		super.paintComponent(g);
	}
	
	
	
}
