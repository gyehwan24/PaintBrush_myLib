package mylib;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class KRadioButton extends KAbstractButton{

	boolean selected;  
	ArrayList<KRadioButton> radioList = new ArrayList<>();  
	
	public KRadioButton(String text) {
		super(text);
	}
	public void add(KRadioButton button) {    //라디오버튼 그룹에 라디오버튼 추가하는 메소드
		this.radioList.add(button);    
	}
	
	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		if(getSelected()) {
			g.setColor(new Color(15,217,234));
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
