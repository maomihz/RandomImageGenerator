package com.maomihz.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.maomihz.data.ImageConstraints;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// the picture
	private BufferedImage image;
	// the property of the picture
	private ImageConstraints imageConstraints;

	// Constructor
	public ImagePanel(BufferedImage image, ImageConstraints imageConstraints) {
		super();
		setBackground(Color.GRAY);
		this.image = image;
		this.imageConstraints = imageConstraints;
	}

	public BufferedImage getImage() {
		return image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
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

	public static Color randColor() {
		return randColor(false);
	}

	// [main method to generate image]!
	public void drawImage(BufferedImage image, int width, int height,
			int pixelWidth, int pixelHeight, boolean isGrayScale) {
		// throw exception if has weak parameters
		if (pixelWidth <= 0 || pixelHeight <= 0 || image == null
				|| image.getHeight(null) <= 0 || image.getWidth(null) <= 0) {
			throw new IllegalArgumentException();
		}
		// calculate the number of cells
		int xCellNum = (int) Math.ceil(width / (double) pixelWidth);
		int yCellNum = (int) Math.ceil(height / (double) pixelHeight);

		// draw cells
		Graphics g = image.getGraphics();
		for (int j = 0; j < yCellNum; j++) {
			for (int i = 0; i < xCellNum; i++) {
				g.setColor(randColor(isGrayScale));
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth,
						pixelHeight);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(imageConstraints.width, imageConstraints.height);
	}

	// update the width etc.
	public void update() {

		image = new BufferedImage(imageConstraints.width,
				imageConstraints.height, BufferedImage.TYPE_INT_ARGB);
		drawImage(getImage(), imageConstraints.width, imageConstraints.height,
				imageConstraints.pixelWidth, imageConstraints.pixelHeight,
				imageConstraints.grayScale);
		repaint();
		setSize(getPreferredSize());
	}
}
