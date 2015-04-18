package com.maomihz.data;

import java.awt.GridBagConstraints;

public interface GridBag {
	GridBagConstraints constraints = new GridBagConstraints();
	Setter SETTER = new Setter(constraints);
	int BOTH = GridBagConstraints.BOTH, NONE = GridBagConstraints.NONE,
			HORIZONTAL = GridBagConstraints.HORIZONTAL,
			VERTICAL = GridBagConstraints.VERTICAL;

	void setGridBagLayout();
}