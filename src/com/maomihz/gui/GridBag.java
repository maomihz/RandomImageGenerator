package com.maomihz.gui;

import java.awt.GridBagConstraints;

public interface GridBag {
	GridBagConstraints constraints = new GridBagConstraints();
	int BOTH = GridBagConstraints.BOTH, NONE = GridBagConstraints.NONE,
			HORIZONTAL = GridBagConstraints.HORIZONTAL,
			VERTICAL = GridBagConstraints.VERTICAL;

	void setGridBagLayout();
}
