package mylib;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class KAbstractButton extends KComponent {
	
	public ArrayList<KActionListener> ActionListenerList = new ArrayList<>(); //액션리스너 리스트
	
	public KAbstractButton(String text) {
		super(text);
	}
	
	public void addKActionListener(KActionListener listener) {
		ActionListenerList.add(listener);
	}
	
	public void processMouseEvent(MouseEvent e) {
		//자신이 가지고 있는 액션 리스너 목록에 있는 Action Listener들을 호출해준다.
		for(KActionListener list : ActionListenerList) {
			list.actionPerformed(new ActionEvent(this, MouseEvent.MOUSE_CLICKED, actionCommand));
		}
	}
	public abstract void setSelected();
	public abstract boolean getSelected();
	
}