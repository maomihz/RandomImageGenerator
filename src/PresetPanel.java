import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PresetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PresetPanel(OperationalPanel operationPanel) {
		setBorder(new TitledBorder(null, "Presets", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setSize(300, 150);
		setBackground(new Color(225, 225, 225));
		setLocation(0, 450);
		setVisible(false);

		JButton presetKindle = new JButton("Kindle");
		presetKindle.setBounds(6, 24, 83, 29);
		presetKindle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(true);
				operationPanel.setWidthText("758");
				operationPanel.setHeightText("1024");
				Main.window.update();
			}
		});
		setLayout(null);
		add(presetKindle);

		JButton presetIphone = new JButton("iPhone");
		presetIphone.setBounds(82, 24, 86, 29);
		presetIphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("320");
				operationPanel.setHeightText("480");
				Main.window.update();
			}
		});
		add(presetIphone);

		JButton presetIphoneRetina = new JButton("iPhone(Retina)");
		presetIphoneRetina.setBounds(161, 24, 133, 29);
		presetIphoneRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("640");
				operationPanel.setHeightText("960");
				Main.window.update();
			}
		});
		add(presetIphoneRetina);

		JButton presetIpad = new JButton("iPad");
		presetIpad.setBounds(92, 49, 75, 29);
		presetIpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("1024");
				operationPanel.setHeightText("1024");
				Main.window.update();
			}
		});
		add(presetIpad);

		JButton presetIpadRetina = new JButton("iPad(Retina)");
		presetIpadRetina.setBounds(171, 49, 117, 29);
		presetIpadRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("2048");
				operationPanel.setHeightText("2048");
				Main.window.update();
			}
		});
		add(presetIpadRetina);

		JButton presetComputer1024_768 = new JButton("Computer(1024x768)");
		presetComputer1024_768.setBounds(16, 77, 178, 29);
		presetComputer1024_768.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("1024");
				operationPanel.setHeightText("768");
				Main.window.update();
			}
		});
		add(presetComputer1024_768);

		JButton btnShowPresets = new JButton("Show Presets");
		btnShowPresets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isVisible()) {
					((JButton) e.getSource()).setText("Show Presets");
					setVisible(false);
					System.out.println("Hide Presets");
				} else {
					((JButton) e.getSource()).setText("Hide Presets");
					setVisible(true);
					System.out.println("Show Presets");
				}
			}
		});
		btnShowPresets.setBounds(74, 389, 150, 29);
		operationPanel.add(btnShowPresets);
	}
}
