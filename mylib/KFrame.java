package mylib;

public class KFrame extends KContainer{

	protected KMenuBar theMenuBar;
	protected KToolBar theToolBar;
	protected KContainer contentPane;
	
	public void setKMenuBar(KMenuBar mb) {
		// 원래 메뉴바는 frame에 있는게 정상이지만...
		add(mb);
	}
	public void setKToolBar(KToolBar tb) {
		add(tb);
	}      
	
	public KFrame() {
		super();
	}
	public KFrame(String text) {
		super(text);
	}

}
