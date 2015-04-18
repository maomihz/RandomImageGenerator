package com.maomihz.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.maomihz.data.GridBag;
import com.maomihz.data.ImageConstraints;
import com.maomihz.data.Main;

public class PresetPanel extends JPanel implements GridBag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PresetButton> buttons = new ArrayList<PresetButton>();

	public PresetPanel(ImageConstraints imageConstraints) {
		setBackground(new Color(225, 225, 225));
		setVisible(false);
		setBorder(new TitledBorder(null, "Presets", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		// set all preset buttons
		buttons.add(new PresetButton("Kindle", 758, 1024, true,
				imageConstraints));
		buttons.add(new PresetButton("iPhone", 320, 480, false,
				imageConstraints));
		buttons.add(new PresetButton("iPhone(Retina)", 640, 960, false,
				imageConstraints));
		buttons.add(new PresetButton("iPad", 1024, 1024, false,
				imageConstraints));
		buttons.add(new PresetButton("iPad(Retina)", 2048, 2048, false,
				imageConstraints));
		buttons.add(new PresetButton("PC(1024x768)", 1024, 768, false,
				imageConstraints));
		// add all buttons to the panel
		for (JButton button : buttons) {
			add(button);
		}
		setGridBagLayout();
	}

	@Override
	public void setGridBagLayout() {
		GridBagLayout layout = new GridBagLayout();
		SETTER.reset(HORIZONTAL);
		constraints.weightx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		for (JButton button : buttons) {
			layout.setConstraints(button, constraints);
			constraints.gridy += 1;
		}
		setLayout(layout);
	}
}

class PresetButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageConstraints imageConstraints;
	private int presetWidth, presetHeight;
	private boolean presetGrayScale;

	PresetButton(String s, int width, int height, boolean grayScale,
			ImageConstraints imageConstraints) {
		super(s);
		setSize(getPreferredSize());
		this.imageConstraints = imageConstraints;
		presetWidth = width;
		presetHeight = height;
		presetGrayScale = grayScale;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		imageConstraints.width = presetWidth;
		imageConstraints.height = presetHeight;
		imageConstraints.grayScale = presetGrayScale;
		Main.window.update();
	}
}