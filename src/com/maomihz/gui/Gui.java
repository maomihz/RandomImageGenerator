package com.maomihz.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.maomihz.data.GridBag;
import com.maomihz.data.ImageConstraints;

public class Gui extends JFrame implements GridBag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageConstraints imageConstraints = new ImageConstraints(320, 480,
			20, 20, 10, "random", false);
	// 2 main panels
	private PreviewPanel previewPanel = new PreviewPanel(imageConstraints);
	private SettingPanel settingPanel = new SettingPanel(imageConstraints);

	public Gui() throws IOException {
		// initialize the GuiFrame
		setSize(1000, 618);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Random Image Generator by Maomi");
		getContentPane().setLayout(null);
		setIconImage(ImageIO
				.read(new File("res" + File.separator + "icon.png")));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		getContentPane().setBackground(Color.GRAY);
		// setResizable(false);
		setLocationRelativeTo(null);
		// create and add setting panel
		settingPanel.setSize(300, 570);
		getContentPane().add(settingPanel);
		// add preview panel
		getContentPane().add(previewPanel);
		update();

		setGridBagLayout();
	}

	public void update() {
		previewPanel.getImagePanel().update();
	}

	public static Color randColor() {
		return ImagePanel.randColor();
	}

	public BufferedImage getPreviewImage() {
		return previewPanel.getImagePanel().getImage();
	}

	@Override
	public void setGridBagLayout() {
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		// initiate constraints
		SETTER.reset(VERTICAL);
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		constraints.fill = VERTICAL;
		// set setting panel
		layout.setConstraints(settingPanel, constraints);
		// set preview panel
		constraints.gridx = 2;
		constraints.gridwidth = 5;
		constraints.weightx = 1;
		constraints.fill = BOTH;
		layout.setConstraints(previewPanel, constraints);
	}
}
