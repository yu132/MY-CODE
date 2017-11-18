package ui.swingUI.other;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class SpecialBorder extends LineBorder{

	private static final long serialVersionUID = 1L;
	
	private String direction;
	
	public SpecialBorder(Color color,String direction) {
        super(color, 1, false);
        this.direction=direction;
    }
	
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
       //int i;
        g.setColor(lineColor);
        /*for(i = 0; i < thickness; i++)  {
        	g.drawLine(x+i, height-i-i-1, width-i-i-1, height-i-i-1);
        	g.drawLine(x+i, y+i, x+i, height-i-i-1);
            g.drawLine(width-i-i-1, y+i, width-i-i-1, height-i-i-1);
            g.drawLine(x+i, height-i-i-1, x+i, height-i-i-1);
        	g.drawLine(x+i, y+i, width-i-i-1, y-i-i-1);
        	g.drawLine(x+i, height-i-i-1, width-i-i-1, height-i-i-1);
        }*/
        switch(direction){
        case "up":
        	g.drawLine(x, y, width-1, y);
        	g.drawLine(x, y, x, height-1);
        	g.drawLine(width-1, y, width-1, height-1);
        	break;
        case "mid":
	        g.drawLine(x, y, width-1, y);
	    	g.drawLine(x, height-1, width-1, height-1);
	    	break;
        case "left":
        	g.drawLine(x, y, width-1, y);
        	g.drawLine(x, y, x, height-1);
	    	g.drawLine(x, height-1, width-1, height-1);
        	break;
        case "right":
        	g.drawLine(x, y, width-1, y);
        	g.drawLine(width-1, y, width-1, height-1);
	    	g.drawLine(x, height-1, width-1, height-1);
        	break;
        }
        g.setColor(oldColor);
    }
}
