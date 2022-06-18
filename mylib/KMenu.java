package mylib;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KMenu extends KContainer{
	
	ArrayList<KMenuItem> itemList = new ArrayList<>();

	public KMenu(String text) {
		super(text);
	}
	//메뉴에 메뉴아이템 추가 
	public void add(KMenuItem menuItem) {
		itemList.add(menuItem);
		menuItem.setBounds(20, 70+(itemList.size()-1)*30, 70, 30);
		super.add(menuItem);		
	}
	
	@Override public void processMouseEvent(MouseEvent e) {
		switch (e.getID()) {
		case MouseEvent.MOUSE_CLICKED:
			if(this.isCliked(e.getPoint())) {   //메뉴를 클릭한 경우
				System.out.println("메뉴 눌림");
				changeVisible();
				for(int i=0; i<itemList.size(); i++){
					if(itemList.get(i).isCliked(e.getPoint()) ) {  //클릭된 아이템 메뉴에게 명령한다.
						itemList.get(i).processMouseEvent(e);
					}
				}
			}
			break;
		}
	}
	@Override public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		if(visible) {
			for(int i=0; i<itemList.size(); i++){
				itemList.get(i).paint(g);
			}
		}
		g.drawString(text, x+5, y+height-10);
	}
	public void changeVisible() {
		if(visible) visible = false;
		else visible = true;
		System.out.println("visible change");
	}

}