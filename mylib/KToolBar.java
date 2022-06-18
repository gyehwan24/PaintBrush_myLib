package mylib;

import java.util.ArrayList;

public class KToolBar extends KContainer{
	ArrayList<KAbstractButton> buttonList = new ArrayList<>();  //버튼을 담는 툴바
	public KToolBar() {}

	//버튼들을 더해서 툴바를 만드는 메소드. 오버라이딩 아님
	public void add(KAbstractButton button) {
		buttonList.add(button);      //한 툴바에 버튼을 추가하는 코드
		super.add(button); 			 //컴포넌트 리스트에 버튼을 추가하는 코드
		button.setBounds(20+((buttonList.size()-1)*51), 70, 50, 30);   //추가할때마다 좌표계산해서 옆에 추가
	}
}
