package com.maomihz.data;
import java.io.IOException;

import com.maomihz.gui.Gui;

public class Main {
	public static Gui window;

	public static void main(String[] args) throws IOException {
		window = new Gui();
		window.setVisible(true);
	}
}
