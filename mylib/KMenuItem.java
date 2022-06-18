package mylib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class KMenuItem extends KAbstractButton{
	
	KMenu menu = new KMenu(null);
	public KMenuItem(String text) {
		super(text);
	}
	
	@Override public void processMouseEvent(MouseEvent e) {
		if(menu.visible) super.processMouseEvent(e);   //메뉴를 클릭했을 때만 메뉴아이템이 클릭되게 한다.
	}
	
	@Override public void paint(Graphics g) {
		if(menu.visible) {
			g.setColor(new Color(230,230,230));
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			g.drawString(text, x+5, y+height-10);
		}
	}

	@Override
	public void setSelected() {}

	@Override
	public boolean getSelected() {
		return false;
	}
}