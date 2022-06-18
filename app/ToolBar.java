package app;

import mylib.KToolBar;

public class ToolBar extends KToolBar{
	Buttons buttons = new Buttons();

	ToolBar() {
	
		for (int i = 0; i < buttons.FigureButtons.length; i++) {
			add(buttons.FigureButtons[i]);
		}
		
		for (int i = 0; i < buttons.ColorButtons.length; i++) {
			add(buttons.ColorButtons[i]);
		}
		
		for (int i = 0; i < buttons.CopyRemove_Buttons.length; i++) {
			add(buttons.CopyRemove_Buttons[i]);
		}
	}
}
