package mylib;

import java.awt.Color;
import java.awt.Graphics;

public class KCheckBox extends KAbstractButton{
	boolean selected;
	
	public KCheckBox(String text) {
		super(text);
		selected = false;
	}

	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		if(getSelected()) {
			g.setColor(new Color(153,217,234));
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
		}else {
			g.setColor(new Color(240,240,240));
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
		}
		g.drawString(text, x+5, y+height-10);
	}
	
	@Override 
	public void setSelected() {
		if(selected == true) selected = false;
		else if(selected == false) selected = true;
	}
	@Override 
	public boolean getSelected() {
		return selected;
	}
	
}
