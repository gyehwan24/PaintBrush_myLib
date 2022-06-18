package app;

import mylib.KFrame;
import mylib.KMouseListener;
import mylib.KMouseMotionListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import mylib.KActionListener;
import mylib.KAdapterFrame;
import mylib.KButton;

//여기는 응용
class PainterKFrame extends KFrame{
	//Canvas canvas = new Canvas();
	MenuBar menubar = new MenuBar(this);
	ToolBar toolbar = new ToolBar();
	Buttons buttons = new Buttons();
	Point Start = null, End = null;
	ArrayList<Thing> FigureList = new ArrayList<>(); //도형 리스트
	ArrayList<Thing> GroupList = new ArrayList<>(); //여러 그룹 리스트
	ArrayList<Color> ColorList = new ArrayList<>(); //색상 리스트
	static Color figureColor = Color.black; //도형의 색상, 기본은 검정
	
	Rectangle rect;
	Oval oval;
	Line line;
	Thing group;
	boolean MovingFigure = false; //도형 이동중 
	boolean MovingGroup = false; //그룹 이동하는중
	int MoveFigureIndex, MoveGroupIndex; //이동시킬 도형,그룹의 리스트인덱스

	
	public PainterKFrame() {
		//여기서 추가
		MyMouseListener listener = new MyMouseListener();
		addKMouseListener(listener);
		setKToolBar(toolbar);
		setKMenuBar(menubar);
		
		
	}

	class MyMouseListener implements KMouseListener{

		public void mousePressed(MouseEvent e) {
			Start = e.getPoint();
			MovingFigure = false;  //도형 움직이기가 끝나면 초기화해준다.
			MovingGroup = false;  //도형 움직이기가 끝나면 초기화해준다.
			for(int i=0; i < FigureList.size() && FigureList !=null; i++) {
				if(FigureList.get(i).isCliked(Start)) {
					//도형이 눌린 조건에서 복사모드거나 삭제모드이면 그에 따라 실행해준다.
					if(toolbar.buttons.CopyRemove_Buttons[0].getSelected()) {
						if(FigureList.get(i).myTypeIs==0) {
							rect = new Rectangle(FigureList.get(i).x+5,FigureList.get(i).y+5,FigureList.get(i).width,FigureList.get(i).height,FigureList.get(i).fill, FigureList.get(i).myColor);
							FigureList.add(rect);
						}
						if(FigureList.get(i).myTypeIs==1) {
							oval = new Oval(FigureList.get(i).x+5,FigureList.get(i).y+5,FigureList.get(i).width,FigureList.get(i).height,FigureList.get(i).fill,FigureList.get(i).myColor);
							FigureList.add(oval);
						}
						if(FigureList.get(i).myTypeIs==2) {
							line = new Line(FigureList.get(i).x+10,FigureList.get(i).y+10,FigureList.get(i).width,FigureList.get(i).height,FigureList.get(i).fill,FigureList.get(i).myColor);
							FigureList.add(line);
						}
						break;
					}
					if(toolbar.buttons.CopyRemove_Buttons[1].getSelected()) {
						FigureList.remove(i);
						break;
					}
					
					FigureList.get(i).off = e.getPoint();
					MovingFigure = true;
					MoveFigureIndex = i;
					FigureList.get(i).off.x = Start.x - FigureList.get(i).x;
					FigureList.get(i).off.y = Start.y - FigureList.get(i).y;
				}
			}
			for(int i=0; i < GroupList.size(); i++) {
				GroupList.get(i).off = e.getPoint();
				for(int j=0; j < GroupList.get(i).compositeList.size(); j++) {
					GroupList.get(i).compositeList.get(j).off = e.getPoint();
				}
				if(GroupList.get(i).isCliked(Start)) {
					if(toolbar.buttons.CopyRemove_Buttons[1].getSelected()) {
						GroupList.remove(i);
						break;
					}
					MovingGroup = true;
					MoveGroupIndex = i;
					GroupList.get(i).off.x = Start.x - GroupList.get(i).x;
					GroupList.get(i).off.y = Start.y - GroupList.get(i).y;
					for(int j=0; j<GroupList.get(i).compositeList.size(); j++) {
						GroupList.get(i).compositeList.get(j).off.x = Start.x - GroupList.get(i).compositeList.get(j).x;
						GroupList.get(i).compositeList.get(j).off.y = Start.y - GroupList.get(i).compositeList.get(j).y;
					}
				}
			}
			e.getComponent().repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			End = e.getPoint();
			if (!MovingFigure && !MovingGroup && Start!=End && 
					!toolbar.buttons.CopyRemove_Buttons[0].getSelected() && !toolbar.buttons.CopyRemove_Buttons[1].getSelected()) {
				if (Buttons.FigureType == 0) { // 사각형
					rect = new Rectangle(Start.x, Start.y, End.x - Start.x, End.y - Start.y, toolbar.buttons.ColorButtons[3].getSelected(),figureColor);
					FigureList.add(rect);
				}
				if (Buttons.FigureType == 1) { // 타원
					oval = new Oval(Start.x, Start.y, End.x - Start.x, End.y - Start.y, toolbar.buttons.ColorButtons[3].getSelected(),figureColor);
					FigureList.add(oval);
				}
				if (Buttons.FigureType == 2) { // 선분
					line = new Line(Start.x, Start.y, End.x - Start.x, End.y - Start.y, toolbar.buttons.ColorButtons[3].getSelected(),figureColor);
					FigureList.add(line);
				}
			}

			if(Buttons.FigureType == 3) {  //복합도형
				//좌표 계산해서 그룹안에 들어온 도형은 그룹리스트에 넣는다.
				int leftX,topY,rightX,bottomY; //그룹의 좌표
				int i=0, count=0;
				//그룹에 속한 도형이 없을 수 있으므로 예외처리를 한다.
				try {
					for(; i < FigureList.size(); i++) {
						if(Start.x <= FigureList.get(i).x &&
							End.x >= FigureList.get(i).x+FigureList.get(i).width &&
							Start.y <= FigureList.get(i).y &&
							End.y >= FigureList.get(i).y+FigureList.get(i).height) {
							break;  //가장 처음 도형으로 좌표를 저장해놓는다. 그 후 비교
						}
					}
					leftX = FigureList.get(i).x;
					topY = FigureList.get(i).y;
					rightX = FigureList.get(i).x + FigureList.get(i).width;
					bottomY = FigureList.get(i).y + FigureList.get(i).height;
		
					for(int k=0; k < FigureList.size(); k++	) {
						if(Start.x <= FigureList.get(k).x &&
							End.x >= FigureList.get(k).x+FigureList.get(k).width &&
							Start.y <= FigureList.get(k).y &&
							End.y >= FigureList.get(k).y+FigureList.get(k).height) {
							if(leftX > FigureList.get(k).x) leftX = FigureList.get(k).x;
							if(topY > FigureList.get(k).y) topY = FigureList.get(k).y;
							if(rightX < FigureList.get(k).x+FigureList.get(k).width) rightX = FigureList.get(k).x+FigureList.get(k).width;
							if(bottomY < FigureList.get(k).y+FigureList.get(k).height) bottomY = FigureList.get(k).y+FigureList.get(k).height;
							count++; //그룹에 포함된 도형의 개수 세기
						}
					}
					
					if(count==1) throw new Exception();
					group = new FigureGroup(leftX, topY, rightX-leftX, bottomY-topY, false, Color.LIGHT_GRAY);
					for(int j=0; j < FigureList.size(); j++) {
						if(Start.x <= FigureList.get(j).x &&
							End.x >= FigureList.get(j).x+FigureList.get(j).width &&
							Start.y <= FigureList.get(j).y &&
							End.y >= FigureList.get(j).y+FigureList.get(j).height) {
							//그룹에 속한 도형들을 그룹의 리스트로 넣는다.
							group.compositeList.add(FigureList.get(j));
						}
					}
					GroupList.add(group);   //그룹리스트에 그룹(여러 도형이 들어있는)을 넣어준다.
					System.out.println("그룹에 속한 도형 : "+count+"개");
					//그룹에 또 다른 그룹이 속하면 그 그룹도 그룹에 넣어준다.
					for(int k=0; k < GroupList.size(); k++) {
						if(Start.x <= GroupList.get(k).x &&
							End.x >= GroupList.get(k).x+GroupList.get(k).width &&
							Start.y <= GroupList.get(k).y &&
							End.y >= GroupList.get(k).y+GroupList.get(k).height) {
							group.compositeList.add(GroupList.get(k));
						}
					}
				}catch(Exception er) {
					if(count==1) System.out.println("그룹에 속한 도형이 1개입니다.");
					if(!MovingFigure && !MovingGroup && count==0) System.out.println("그룹에 속한 도형이 없습니다.");
				}
			}
			if(MovingFigure) {  
				FigureList.get(MoveFigureIndex).x = e.getPoint().x - FigureList.get(MoveFigureIndex).off.x;
				FigureList.get(MoveFigureIndex).y = e.getPoint().y - FigureList.get(MoveFigureIndex).off.y;
			}
			//그룹을 움직이는 경우
			if(MovingGroup) {
				GroupList.get(MoveGroupIndex).x = e.getPoint().x - GroupList.get(MoveGroupIndex).off.x;
				GroupList.get(MoveGroupIndex).y = e.getPoint().y - GroupList.get(MoveGroupIndex).off.y;
				for(int i=0; i<GroupList.get(MoveGroupIndex).compositeList.size(); i++) {
					GroupList.get(MoveGroupIndex).compositeList.get(i).x = e.getPoint().x - GroupList.get(MoveGroupIndex).compositeList.get(i).off.x;
					GroupList.get(MoveGroupIndex).compositeList.get(i).y = e.getPoint().y - GroupList.get(MoveGroupIndex).compositeList.get(i).off.y;
				}
			}
			e.getComponent().repaint();
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			End = e.getPoint();
			if(toolbar.buttons.FigureButtons[0].isCliked(End)) toolbar.buttons.FigureButtons[0].processMouseEvent(e);
			if(toolbar.buttons.FigureButtons[1].isCliked(End)) toolbar.buttons.FigureButtons[1].processMouseEvent(e);
			if(toolbar.buttons.FigureButtons[2].isCliked(End)) toolbar.buttons.FigureButtons[2].processMouseEvent(e);
			if(toolbar.buttons.FigureButtons[3].isCliked(End)) toolbar.buttons.FigureButtons[3].processMouseEvent(e);
			if(toolbar.buttons.ColorButtons[0].isCliked(End)) toolbar.buttons.ColorButtons[0].processMouseEvent(e);
			if(toolbar.buttons.ColorButtons[1].isCliked(End)) toolbar.buttons.ColorButtons[1].processMouseEvent(e);
			if(toolbar.buttons.ColorButtons[2].isCliked(End)) toolbar.buttons.ColorButtons[2].processMouseEvent(e);
			if(toolbar.buttons.ColorButtons[3].isCliked(End)) toolbar.buttons.ColorButtons[3].processMouseEvent(e);
			if(toolbar.buttons.CopyRemove_Buttons[0].isCliked(End)) toolbar.buttons.CopyRemove_Buttons[0].processMouseEvent(e);
			if(toolbar.buttons.CopyRemove_Buttons[1].isCliked(End)) toolbar.buttons.CopyRemove_Buttons[1].processMouseEvent(e);
			if(toolbar.buttons.CopyRemove_Buttons[2].isCliked(End)) {  //전체 삭제
				System.out.println("전체 삭제");
				FigureList.removeAll(FigureList);
				GroupList.removeAll(GroupList);
			}
			if(menubar.fileMenu.isCliked(End)) menubar.fileMenu.processMouseEvent(e);
			if(menubar.save.isCliked(End)) menubar.save.processMouseEvent(e);
			if(menubar.open.isCliked(End)) menubar.open.processMouseEvent(e);
			
		}
	}

	public void paint(Graphics g) { // 그림을 그리는 부분. 
		super.paint(g);
		// 그룹 그려주는 부분
		for (int i = 0; i < GroupList.size(); i++) {
			g.setColor(Color.lightGray);
			GroupList.get(i).draw(g);
		}

		// 다시 한번 도형을 그려서 그룹핑에 색이 가리는 문제를 해결한다.
		for (int i = 0; i < FigureList.size(); i++) {
			FigureList.get(i).draw(g);
		}
	}
}


public class Main {
	public static void main(String[] args) {
		
		KAdapterFrame frame = new KAdapterFrame();
		frame.setSize(800,650);
		frame.setKFrame(new PainterKFrame());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(KAdapterFrame.EXIT_ON_CLOSE);
	}
}
