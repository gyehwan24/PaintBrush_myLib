package mylib;

import java.awt.*;

public class KButton extends KAbstractButton{

	public KButton(String content) {
		super(content);
	}
	@Override public void paint(Graphics g) {
		//super.paint(g);
		g.setColor(new Color(240,240,240));
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString(text, x+5, y+height-10);
	}
	
	@Override public void setSelected() {}
	@Override public boolean getSelected() {
		return false;
	}

}
