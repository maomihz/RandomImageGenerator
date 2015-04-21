package com.maomihz.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class LoadingWheel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Secondary color will substitute primary color.
	// Set both primary and secondary color random.
	private Color primaryColor;
	private Color secondaryColor;

	private double length; // diameter of the outer circle
	private double innerLength; // diameter of the inner circle

	// Control the animation speed, in percentage.
	private double animationSpeed = 0.3;

	// if the wheel has started changing
	private boolean started;

	public LoadingWheel(int mySideLength) {
		length = mySideLength;
		innerLength = 3;
		setSize((int) length, (int) length);
		setOpaque(false); // Otherwise color won't be shown
		setVisible(false); // You must call start() method
	}

	// reset both radius, speed and color
	private void resetall() {
		reset();
		primaryColor = Gui.randColor();
		secondaryColor = Gui.randColor();
	}

	// reset radius and speed
	private void reset() {
		innerLength = 0;
		animationSpeed = 2;
	}

	// start to change
	public void start() {
		if (!started) {
			resetall();
			started = true;
			setVisible(true);
		}
	}

	// stop changing
	public void stop() {
		started = false;
		setVisible(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the color of the graphics
		g.setColor(primaryColor);
		// draw the bigger oval
		g.fillOval(0, 0, (int) length, (int) length);
		// ready to draw the second smaller oval
		g.setColor(secondaryColor);
		// draw the second oval
		g.fillOval(
				(int) length / 2 - (int) (innerLength / 2), // Center the oval
				(int) (length / 2) - (int) (innerLength / 2),
				(int) innerLength, (int) innerLength);
		// reset the radius of the smaller oval
		innerLength += animationSpeed / 100.0 * length;
		// reset if radius of the smaller oval is bigger that the bigger oval's
		if (innerLength > length) {
			primaryColor = secondaryColor;
			secondaryColor = Gui.randColor();
			reset();
		}
		// accelerate the speed
		animationSpeed += 0.1;
		// sleep 10 ms to wait for next paint
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
}
