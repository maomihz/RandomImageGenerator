package com.maomihz.data;

import java.awt.GridBagConstraints;

public class Setter {
	private GridBagConstraints constraints;

	Setter(GridBagConstraints constraints) {
		this.constraints = constraints;
	}

	public void reset(int fillMethod) {
		constraints.fill = fillMethod;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 0;
		constraints.gridheight = 0;
		constraints.weightx = 0;
		constraints.weighty = 0;
	}

	public void reset() {
		this.reset(GridBagConstraints.NONE);
	}
}