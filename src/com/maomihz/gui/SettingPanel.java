package com.maomihz.gui;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.maomihz.data.GridBag;
import com.maomihz.data.ImageConstraints;

public class SettingPanel extends JPanel implements GridBag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OperationalPanel operationPanel;
	private PresetPanel presetPanel;

	public SettingPanel(ImageConstraints imageConstraints) {
		super();
		operationPanel = new OperationalPanel(imageConstraints);
		presetPanel = operationPanel.getPresetPanel();
		add(operationPanel);
		add(presetPanel);
		setGridBagLayout();
	}

	@Override
	public void setGridBagLayout() {
		// set the layout
		GridBagLayout layout = new GridBagLayout();
		// initiate constraints
		SETTER.reset(BOTH);
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		// add two panels
		layout.setConstraints(operationPanel, constraints);
		constraints.gridy = 3;
		constraints.gridheight = 1;
		layout.setConstraints(presetPanel, constraints);
		setLayout(layout);
	}
}
