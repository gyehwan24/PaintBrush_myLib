package app;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

class Thing implements Serializable{
	public int x,y,width,height; //도형을 그릴 때 필요한 변수들
	ArrayList<Thing> compositeList= new ArrayList<>(); ;  //여러 도형을 담을 리스트
	Point off = null;
	protected boolean fill = false;
	public int myTypeIs;  //자신의 도형 타입을 알려주는 변수  
	Color myColor = Color.black; //기본 색상은 검정색
	
	PainterKFrame frame;
	public Thing(int x, int y, int width, int height, boolean fill, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
		this.myColor = color;
	}
	public void draw(Graphics g) {}
	
	//마우스 좌표를 인자로 받고 도형이 클릭되었는지 알려주는 boolean 함수
	public boolean isCliked(Point p) {
		if(this.x<=p.x && p.x<=this.x+this.width && 
				this.y<=p.y && p.y<=this.y+this.height) {
			return true;
		}
		else return false;
	}
}

class FigureGroup extends Thing{
	

	public FigureGroup(int x, int y, int width, int height, boolean fill, Color color) {
		super(x, y, width, height, fill, color);
	}

	@Override public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}

public class Figure extends Thing{  
	
	public Figure(int x, int y, int width, int height, boolean fill, Color color) {
		super(x, y, width, height, fill, color);
	}
}

class Rectangle extends Figure{
	public Rectangle(int x, int y, int width, int height, boolean fill, Color color) {
		super(x, y, width, height, fill, color);
		this.myTypeIs = 0;
		
	}
	@Override public void draw(Graphics g) {
		if(fill) {
			g.setColor(myColor);
			g.fillRect(x, y, width, height);
		}else {
			g.setColor(myColor);
			g.drawRect(x, y, width, height);
		}
	}
}

class Oval extends Figure{
	public Oval(int x, int y, int width, int height, boolean fill, Color color) {
		super(x, y, width, height, fill, color);
		this.myTypeIs = 1;
	}
	@Override public void draw(Graphics g) {
		if(fill) {
			g.setColor(myColor);
			g.fillOval(x, y, width, height);
		}else {
			g.setColor(myColor);
			g.drawOval(x, y, width, height);
		}
	}
}

class Line extends Figure{
	
	public Line(int x, int y, int width, int height, boolean fill, Color color) {
		super(x, y, width, height, fill, color);
		this.myTypeIs = 2;
	}
	@Override public void draw(Graphics g) {
		g.setColor(myColor);
		g.drawLine(x, y, x+width, y+height);
	}

}

