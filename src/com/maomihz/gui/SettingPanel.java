package com.maomihz.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class SettingPanel extends JPanel implements GridBag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OperationalPanel operationPanel;
	private PresetPanel presetPanel;

	public SettingPanel(OperationalPanel operationPanel, PresetPanel presetPanel) {
		super();
		this.operationPanel = operationPanel;
		this.presetPanel = presetPanel;
		add(operationPanel);
		add(presetPanel);
		setGridBagLayout();
	}

	public OperationalPanel getOperationPanel() {
		return operationPanel;
	}

	public PresetPanel getPresetPanel() {
		return presetPanel;
	}

	public int getWidthOption() {
		return Integer.parseInt(operationPanel.getWidthText());
	}

	public int getHeightOption() {
		return Integer.parseInt(operationPanel.getHeightText());
	}

	public int getPixelWidthOption() {
		return Integer.parseInt(operationPanel.getPixelWidthField().getText());
	}

	public int getPixelHeightOption() {
		return Integer.parseInt(operationPanel.getPixelHeightField().getText());
	}

	public boolean getGrayScale() {
		return operationPanel.isGrayScale();
	}

	@Override
	public void setGridBagLayout() {
		// set the layout
		GridBagLayout layout = new GridBagLayout();
		// initiate constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.ipadx = 0;
		constraints.ipadx = 1;
		//add two panels
		layout.setConstraints(operationPanel, constraints);
		constraints.gridy = 3;
		constraints.gridheight = 1;
		layout.setConstraints(presetPanel, constraints);
		setLayout(layout);
	}
}
