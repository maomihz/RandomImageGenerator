package com.maomihz.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maomihz.data.GridBag;
import com.maomihz.data.ImageConstraints;
import com.maomihz.data.Main;

public class OperationalPanel extends JPanel implements GridBag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// All components
	private JCheckBox chckbxGrayscale, chckbxSaveMultiple;
	private ArrayList<JLabel> fieldLabels = new ArrayList<JLabel>(5);
	private ArrayList<JTextField> fields = new ArrayList<JTextField>(5);
	private JLabel title;
	private JButton btnRepaint, btnSave, btnExit, btnShowPresets;
	private LoadingWheel animation;
	// presetPanel that makes default options
	private PresetPanel presetPanel;
	// the property of image that will be changed by panel
	private ImageConstraints imageConstraints;

	// constructor
	public OperationalPanel(ImageConstraints imageConstraints) {
		this.imageConstraints = imageConstraints;
		// initialize the panel
		setBackground(Color.LIGHT_GRAY);
		presetPanel = new PresetPanel(this.imageConstraints);
		// add title to the panel
		title = new JLabel("Setting");
		title.setFont(new Font("serif", Font.BOLD, 40));
		add(title);
		// add animation to the panel
		animation = new LoadingWheel(50);
		add(animation);

		// add all field labels
		fieldLabels.add(new JLabel("Width"));
		fieldLabels.add(new JLabel("Height"));
		fieldLabels.add(new JLabel("Pixel Width"));
		fieldLabels.add(new JLabel("Pixel Height"));
		fieldLabels.add(new JLabel("Number"));
		fieldLabels.add(new JLabel("Name"));
		// add all labels and fields to the panel
		for (JLabel label : fieldLabels) {
			fields.add(new JTextField(""));
			add(label);
			add(fields.get(fieldLabels.indexOf(label)));
		}
		// set Default values
		getFieldByName("Width").setText("320");
		getFieldByName("Height").setText("480");
		getFieldByName("Pixel Width").setText("20");
		getFieldByName("Pixel Height").setText("20");
		getFieldByName("Number").setText("10");
		getFieldByName("Name").setText("random");

		chckbxGrayscale = new JCheckBox("GrayScale");
		chckbxGrayscale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Main.window.update();
			}
		});
		chckbxGrayscale.setOpaque(false);
		add(chckbxGrayscale);

		chckbxSaveMultiple = new JCheckBox("Save Multiple");
		chckbxSaveMultiple.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()) {
					getFieldByName("Number").setVisible(true);
					fieldLabels.get(4).setVisible(true);
				} else {
					getFieldByName("Number").setVisible(false);
					fieldLabels.get(4).setVisible(false);
				}
			}
		});
		chckbxSaveMultiple.setOpaque(false);
		chckbxSaveMultiple.setSelected(true);
		add(chckbxSaveMultiple);

		btnRepaint = new JButton("Preview");
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make change to image constraints
				imageConstraints.width = Integer.parseInt(fields.get(0)
						.getText());
				imageConstraints.height = Integer.parseInt(fields.get(1)
						.getText());
				imageConstraints.pixelWidth = Integer.parseInt(fields.get(2)
						.getText());
				imageConstraints.pixelHeight = Integer.parseInt(fields.get(3)
						.getText());
				imageConstraints.number = Integer.parseInt(fields.get(4)
						.getText());
				imageConstraints.name = fields.get(5).getText();
				// update the window
				Main.window.update();
			}
		});
		add(btnRepaint);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {

						int fileNameCount = 0;
						int fileNumCount = 0;
						int fileNum = Integer.parseInt(getFieldByName("Number")
								.getText());
						File destFolder = new File("RandomImages");
						if (!destFolder.exists())
							destFolder.mkdir();

						while (fileNumCount < fileNum) {
							File destination;
							do {
								animation.start();
								destination = new File("RandomImages"
										+ File.separator
										+ getFieldByName("Name").getText()
										+ "_"
										+ Main.window.getPreviewImage()
												.getWidth()
										+ "x"
										+ Main.window.getPreviewImage()
												.getHeight() + "_"
										+ fileNameCount + ".png");
								fileNameCount++;
								System.out.println(destination
										+ " Name exist!!!");
							} while (destination.exists());
							try {
								animation.start();
								ImageIO.write(Main.window.getPreviewImage(),
										"png", destination);
								fileNumCount++;
								System.out.println("I wrote an image");
							} catch (IOException e1) {
							}
							Main.window.update();
						}
						animation.stop();
					}
				}.start();
			}
		});
		add(btnSave);

		btnShowPresets = new JButton("Show Presets");
		btnShowPresets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (presetPanel.isVisible()) {
					((JButton) e.getSource()).setText("Show Presets");
					presetPanel.setVisible(false);
					System.out.println("Hide Presets");
				} else {
					((JButton) e.getSource()).setText("Hide Presets");
					presetPanel.setVisible(true);
					System.out.println("Show Presets");
				}
			}
		});
		add(btnShowPresets);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnExit);

		// set the layout to grid bag layout
		setGridBagLayout();
	}

	@Override
	public void setGridBagLayout() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		// set animation
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		layout.setConstraints(animation, constraints);
		// set title
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		layout.setConstraints(title, constraints);
		// set fields and field labels
		for (int i = 0; i < fields.size(); i++) {
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0;
			constraints.weighty = 0;
			constraints.gridx = 0;
			constraints.gridy = 1 + i;
			layout.setConstraints(fieldLabels.get(i), constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			constraints.weightx = 1;
			layout.setConstraints(fields.get(i), constraints);
		}
		// set check boxes
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 2 + fieldLabels.size();
		layout.setConstraints(chckbxGrayscale, constraints);
		constraints.gridx = 1;
		layout.setConstraints(chckbxSaveMultiple, constraints);
		// set buttons
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 3 + fieldLabels.size();
		layout.setConstraints(btnRepaint, constraints);
		constraints.gridx = 1;
		layout.setConstraints(btnSave, constraints);
		constraints.gridx = 2;
		layout.setConstraints(btnShowPresets, constraints);
		constraints.gridx = 1;
		constraints.gridy = 4 + fieldLabels.size();
		layout.setConstraints(btnExit, constraints);
	}

	public PresetPanel getPresetPanel() {
		return presetPanel;
	}

	public ImageConstraints getImageConstraints() {
		return imageConstraints;
	}

	private JTextField getFieldByName(String name) {
		for (int i = 0; i < fields.size(); i++) {
			if (name == fieldLabels.get(i).getText())
				return fields.get(i);
		}
		return null;
	}
}
