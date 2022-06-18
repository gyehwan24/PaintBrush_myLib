package mylib;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KMenuBar extends KContainer{
	ArrayList<KMenu> menuList = new ArrayList<>();
	
	public KMenuBar() {
		super();
	}
	//메뉴바에 메뉴 추가
	public void add(KMenu menu) {   
		menuList.add(menu);
		super.add(menu);
		menu.setBounds(20+((menuList.size()-1)*50), 40, 50, 30);
	}
	
	@Override public void processMouseEvent(MouseEvent e) {
		switch (e.getID()) {
		case MouseEvent.MOUSE_CLICKED:
			End = e.getPoint();
			for(int i=0; i<menuList.size(); i++) {
				if(menuList.get(i).isCliked(End)) {
					//menuList.get(i).processMouseEvent(e);
				}
			}
			break;
		}
	}
}
