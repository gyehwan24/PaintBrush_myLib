package mylib;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class KContainer extends KComponent{
	public ArrayList<KComponent> compoList = new ArrayList<>();
	Point Start,End = null;
	
	public KContainer() {}
	
	public KContainer(String text) {
		super(text);
	}

	public void add(KComponent component) {
		compoList.add(component);
	}
	
	public void paint(Graphics g) {  //컨테이너에서 가지고있는 컴포넌트들을 한번씩 그려준다.
		for(KComponent list : compoList) {
			list.paint(g);
		}

	}	
}