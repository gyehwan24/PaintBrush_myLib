package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import mylib.KAbstractButton;
import mylib.KActionListener;
import mylib.KButton;
import mylib.KCheckBox;
import mylib.KMouseListener;
import mylib.KRadioButton;

public class Buttons implements Serializable{
	KAbstractButton[] FigureButtons = new KAbstractButton[4];
	KAbstractButton[] ColorButtons = new KAbstractButton[4];
	KAbstractButton[] CopyRemove_Buttons = new KAbstractButton[3];
	static int FigureType = 0; //도형 모드, 기본값은 사각형
	PainterKFrame frame;
	
	public Buttons() {
		
		MyActionListenerFigure listener = new MyActionListenerFigure();
		FigureButtons[0] = new KButton("Rect");
		FigureButtons[1] = new KButton("Oval");
		FigureButtons[2] = new KButton("Line");
		FigureButtons[3] = new KButton("Group");
		for(int i = 0; i < FigureButtons.length; i++) {
			FigureButtons[i].addKActionListener(listener);
		}
		
		ColorButtons[0] = new KButton("Black");
		ColorButtons[1] = new KButton("Blue");
		ColorButtons[2] = new KButton("Red");
		ColorButtons[3] = new KCheckBox("Fill");
		for(int i = 0; i < ColorButtons.length; i++) {
			ColorButtons[i].addKActionListener(listener);
		}
		
		CopyRemove_Buttons[0] = new KRadioButton("Copy");
		CopyRemove_Buttons[1] = new KRadioButton("Delete");
		CopyRemove_Buttons[2] = new KButton("Clear");
		for(int i = 0; i < CopyRemove_Buttons.length; i++) {
			CopyRemove_Buttons[i].addKActionListener(listener);
		}
	} 
	
	class MyActionListenerFigure implements KActionListener{
		
		//각각의 버튼에서 호출하는 함수. 버튼에 따라 Figure mode를 세팅해준다.
		@Override public void actionPerformed(ActionEvent e) {
			//e.getSource() 함수는 해당 버튼의 참조를 돌려준다.
			if(e.getSource()==FigureButtons[0]) {
				System.out.println("사각형 버튼 클릭");
				FigureType=0;
			}
			if(e.getSource()==FigureButtons[1]) {
				System.out.println("타원 버튼 클릭");
				FigureType=1;
			}
			if(e.getSource()==FigureButtons[2]) {
				System.out.println("선분 버튼 클릭");
				FigureType=2;
			}
			if(e.getSource()==FigureButtons[3]) {
				System.out.println("그룹 버튼 클릭");
				FigureType=3;
			}
			
			if(e.getSource()==ColorButtons[0]) {
				System.out.println("검정색 클릭");
				frame.figureColor = Color.black;
			}
			if(e.getSource()==ColorButtons[1]) {
				System.out.println("파랑색 클릭");
				frame.figureColor = Color.blue;
			}
			if(e.getSource()==ColorButtons[2]) {
				System.out.println("빨강색 클릭");
				frame.figureColor = Color.red;
			}
			if(e.getSource()==ColorButtons[3]) {
				System.out.println("채우기 클릭");
				ColorButtons[3].setSelected();
			}
			
			if(e.getSource()==CopyRemove_Buttons[0]) {
				System.out.println("복사모드");
				CopyRemove_Buttons[0].setSelected();
			}
			if(e.getSource()==CopyRemove_Buttons[1]) {
				System.out.println("삭제모드");
				CopyRemove_Buttons[1].setSelected();
			}
		}
	}
}

