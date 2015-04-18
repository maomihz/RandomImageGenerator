package com.maomihz.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.maomihz.data.Main;

public class PresetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PresetButton> buttons = new ArrayList<PresetButton>();

	public PresetPanel(OperationalPanel operationPanel) {
		setBackground(new Color(225, 225, 225));
		setVisible(false);
		setBorder(new TitledBorder(null, "Presets", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		// set all preset buttons
		buttons.add(new PresetButton("Kindle", 758, 1024, true, operationPanel));
		buttons.add(new PresetButton("iPhone", 320, 480, false, operationPanel));
		buttons.add(new PresetButton("iPhone(Retina)", 640, 960, false,
				operationPanel));
		buttons.add(new PresetButton("iPad", 1024, 1024, false, operationPanel));
		buttons.add(new PresetButton("iPad(Retina)", 2048, 2048, false,
				operationPanel));
		buttons.add(new PresetButton("PC(1024x768)", 1024, 768, false,
				operationPanel));
		// add all buttons to the panel
		for (JButton button : buttons) {
			add(button);
		}
	}
}

class PresetButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int presetWidth, presetHeight;
	private OperationalPanel targetPanel;
	private boolean graySale;

	PresetButton(String s, int width, int height, boolean grayScale,
			OperationalPanel targetPanel) {
		super(s);
		setSize(getPreferredSize());
		presetWidth = width;
		presetHeight = height;
		this.graySale = grayScale;
		this.targetPanel = targetPanel;
		addActionListener(this);
	}

	public int getPresetWidth() {
		return presetWidth;
	}

	public int getPresetHeight() {
		return presetHeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		targetPanel.getChckbxGrayscale().setSelected(graySale);
		targetPanel.setWidthText(String.valueOf(presetWidth));
		targetPanel.setHeightText(String.valueOf(presetHeight));
		Main.window.update();
	}

}