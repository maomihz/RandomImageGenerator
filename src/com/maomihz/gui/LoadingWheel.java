package com.maomihz.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoadingWheel extends JComponent implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Secondary color will substitute primary color. 
	// Set both primary and secondary color random. 
	private Color primaryColor;
	private Color secondaryColor;;
	
	private double length; //diameter of the outer circle
	private double innerLength; //diameter of the inner circle
	
	// Control the animation speed, in percentage. 
	private double animationSpeed = 0.5;
	
	// Use timer to trigger action. 
	private Timer animator;
	private Timer accelerator;
	
	private boolean started;

	public LoadingWheel(int mySideLength) {
		length = mySideLength;
		innerLength = 3;
		setSize((int)length, (int)length);
		setOpaque(false); //Otherwise color won't be shown
		setVisible(false); //You must call start() method
		
		animator = new Timer(10, this);
		accelerator = new Timer(100, this);
	}
	
	private void resetall() {
		reset();
		primaryColor = Gui.randColor();
		secondaryColor = Gui.randColor();
	}
	
	private void reset() {
		innerLength = 0;
		animationSpeed = 2;
	}
	
	public void start() {
		if (!started) {
			resetall();
			animator.start();
			accelerator.start();
			started = true;
			setVisible(true);
		}
	}
	
	public void stop() {
		if (started) {
			animator.stop();
			accelerator.stop();
			started = false;
			setVisible(false);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(primaryColor);
		g.fillOval(0, 0, (int)length, (int)length);
		
		g.setColor(secondaryColor);
		g.fillOval((int)length / 2 - (int)(innerLength / 2), // Center the oval
				(int)(length / 2) - (int)(innerLength / 2), 
				(int)innerLength, (int)innerLength);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == animator) {
			innerLength += animationSpeed / 100.0 * length;
			if (innerLength > length) {
				primaryColor = secondaryColor;
				secondaryColor = Gui.randColor();
				reset();
			}
		} else if (event.getSource() == accelerator) {
			animationSpeed += 0.3;
		}
		repaint();
	}

}
