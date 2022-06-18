package app;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.ArrayList;

import mylib.KActionListener;
import mylib.KMenu;
import mylib.KMenuBar;
import mylib.KMenuItem;

public class MenuBar extends KMenuBar{
	
	KMenu fileMenu = new KMenu("File");
	KMenuItem save = new KMenuItem("Save");
	KMenuItem open = new KMenuItem("Open");
	PainterKFrame frame;
	public MenuBar(PainterKFrame frame){
		this.frame = frame;
		MyActionListenerMenu listener = new MyActionListenerMenu();
		fileMenu.add(save);
		fileMenu.add(open);
		add(fileMenu);
		
		save.addKActionListener(listener);
		open.addKActionListener(listener);
		
	}
	
	class MyActionListenerMenu implements KActionListener{
		@Override 
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save) {
				System.out.println("save클릭");
				String outname = "drawing.png";
				try {
					FileOutputStream fos = new FileOutputStream(outname);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					ObjectOutputStream oos = new ObjectOutputStream(bos);
					writeObject(oos);
					oos.close();
				}catch(IOException err) {
					err.printStackTrace();
				}
			}
			
			if(e.getSource()==open) {
				System.out.println("open클릭");
				String inname = "drawing.png";
				try {
					FileInputStream fis = new FileInputStream(inname);
					BufferedInputStream bis = new BufferedInputStream(fis);
					ObjectInputStream ois = new ObjectInputStream(bis);
					readObject(ois);
					ois.close();
				}catch(IOException | ClassNotFoundException er){
					//er.printStackTrace();
					System.out.println("지정된 파일을 찾을 수 없습니다.");
				}
			}
		}
	}


	private void writeObject(ObjectOutputStream outputStream) throws IOException {
		outputStream.writeObject(frame.FigureList);
	}

	private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
		ArrayList<Thing> temp = (ArrayList<Thing>) inputStream.readObject();
		frame.FigureList.clear();
		frame.FigureList = temp;
	}
}

