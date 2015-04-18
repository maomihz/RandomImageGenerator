package com.maomihz.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class SettingPanel extends JPanel {

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
		// set the layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;
		s.gridx = 0;
		s.gridy = 0;
		s.weightx = 1;
		s.weighty = 1;
		s.gridwidth = 1;
		s.gridheight = 3;
		layout.setConstraints(operationPanel, s);
		s.gridy = 3;
		s.gridheight = 1;
		layout.setConstraints(presetPanel, s);
		setLayout(layout);
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
}
