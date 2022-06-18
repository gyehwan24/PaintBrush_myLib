package mylib;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KComponent {

	public int x, y, width, height;
	public String text = null;
	public String actionCommand;
	static boolean visible = false;   //기본값은 false, menu를 클릭하면 true로 
	ArrayList<KMouseListener> MouseListenerList = new ArrayList<>();
	
	public KComponent() {}
	public KComponent(String text) {
		this.text = text;
	}
	public void setBounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public void setActionCommand(String str) {
		this.actionCommand = str;
	}

	public void paint(Graphics g){}
	
	public void addKMouseListener(KMouseListener listener) {
		MouseListenerList.add(listener);	
	}
	
	
	public boolean isCliked(Point p) {
		if(this.x<=p.x && p.x<=this.x+this.width && 
				this.y<=p.y && p.y<=this.y+this.height) {
			return true;
		}
		else return false;
	}
	
	public void processMouseEvent(MouseEvent e) {

		for(KMouseListener list : MouseListenerList) {
			switch(e.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				list.mousePressed(e);
				break;
			case MouseEvent.MOUSE_RELEASED:
				list.mouseReleased(e);
				break;
			case MouseEvent.MOUSE_CLICKED:
				list.mouseClicked(e);
				break;
			}
		}
	}
}
