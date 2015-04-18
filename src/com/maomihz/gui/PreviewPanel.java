package com.maomihz.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.maomihz.data.GridBag;
import com.maomihz.data.ImageConstraints;

public class PreviewPanel extends JPanel implements GridBag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final BufferedImage DEFAULT_IMAGE = new BufferedImage(320,
			480, BufferedImage.TYPE_INT_ARGB);
	private ImagePanel imagePanel;
	private RevisionPanel revisionPanel;

	public PreviewPanel(ImageConstraints imageConstraints) {
		setBackground(Color.GRAY);
		imagePanel = new ImagePanel(DEFAULT_IMAGE, imageConstraints);
		revisionPanel = new RevisionPanel();
		setBackground(SystemColor.window);
		add(imagePanel);
		add(revisionPanel);
		setGridBagLayout();
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public RevisionPanel getRevisionPanel() {
		return revisionPanel;
	}

	public void setRevisionPanel(RevisionPanel revisionPanel) {
		this.revisionPanel = revisionPanel;
	}

	@Override
	public void setGridBagLayout() {
		GridBagLayout layout = new GridBagLayout();
		// initiate constraints
		SETTER.reset();
		constraints.gridwidth = 1;
		constraints.gridheight = 9;
		constraints.weightx = 1;
		constraints.weighty = 1;
		// add two panels
		layout.setConstraints(imagePanel, constraints);
		constraints.gridy = 9;
		constraints.gridheight = 1;
		layout.setConstraints(revisionPanel, constraints);
	}
}
