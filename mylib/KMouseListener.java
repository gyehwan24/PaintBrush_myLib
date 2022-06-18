package mylib;

import java.awt.event.MouseEvent;

public interface KMouseListener{
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseClicked(MouseEvent e);
	//public void mouseDragged(MouseEvent e);
}
//addMouseListener 는 컴포넌트에