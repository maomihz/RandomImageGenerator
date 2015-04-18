package com.maomihz.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame implements GridBag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 4 main panels
	public PreviewPanel previewPanel;
	public ImagePanel imagePanel;
	public SettingPanel settingPanel;

	public Gui() throws IOException {
		// initialize the GuiFrame
		setSize(800, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Random Image Generator by Maomi");
		getContentPane().setLayout(null);
		setIconImage(ImageIO
				.read(new File("res" + File.separator + "icon.png")));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setResizable(false);
		setLocationRelativeTo(null);
		// create and add preview panel
		previewPanel = new PreviewPanel();
		getContentPane().add(previewPanel);
		// create and add setting panel
		OperationalPanel o = new OperationalPanel();
		PresetPanel p = new PresetPanel(o);
		o.setPresetPanel(p);
		settingPanel = new SettingPanel(o, p);
		settingPanel.setSize(300, 570);
		getContentPane().add(settingPanel);
		// create and add image panel to preview panel
		imagePanel = new ImagePanel(new BufferedImage(345, 55,
				BufferedImage.TYPE_INT_ARGB));
		previewPanel.add(imagePanel);
		imagePanel.setPreferredSize(getPreferredSize());

		update();
	}

	// [main method to generate image]!
	private void drawImage(BufferedImage image, boolean isGrayScale,
			int pixelWidth, int pixelHeight) {
		if (pixelWidth <= 0 || pixelHeight <= 0 || image == null
				|| image.getHeight(null) <= 0 || image.getWidth(null) <= 0) {
			throw new IllegalArgumentException();
		}
		int xCellNum = (int) Math.ceil(image.getWidth(null)
				/ (double) pixelWidth);
		int yCellNum = (int) Math.ceil(image.getHeight(null)
				/ (double) pixelHeight);
		Graphics g = image.getGraphics();
		for (int j = 0; j < yCellNum; j++) {
			for (int i = 0; i < xCellNum; i++) {
				g.setColor(randColor(isGrayScale));
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth,
						pixelHeight);
			}
		}
	}

	public static Color randColor() {
		return randColor(false);
	}

	public static Color randColor(boolean isGrayScale) {
		if (isGrayScale) {
			int c = (int) (Math.random() * 256);
			return new Color(c, c, c);
		} else {
			return new Color((int) (Math.random() * 256),
					(int) (Math.random() * 256), (int) (Math.random() * 256));
		}
	}

	// update the width etc.
	void update() {
		int width = settingPanel.getWidthOption();
		int height = settingPanel.getHeightOption();
		int pixelWidth = settingPanel.getPixelWidthOption();
		int pixelHeight = settingPanel.getPixelHeightOption();
		imagePanel.setImage(new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB));
		drawImage(imagePanel.getImage(), settingPanel.getGrayScale(),
				pixelWidth, pixelHeight);
		repaint();
		imagePanel.setSize(imagePanel.getPreferredSize());
		System.out.println("Image Regenerated");
	}

	@Override
	public void setGridBagLayout() {
		GridBagLayout layout = new GridBagLayout();
		constraints.fill = BOTH;
		setLayout(layout);
		// initiate constraints
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.ipadx = 0;
		constraints.ipady = 0;
		// set setting panel
		layout.setConstraints(settingPanel, constraints);
		// set preview panel
		constraints.gridwidth = 5;
		layout.setConstraints(previewPanel, constraints);
	}
}
