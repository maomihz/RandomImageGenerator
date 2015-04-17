import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PresetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PresetButton> buttons = new ArrayList<PresetButton>();

	public PresetPanel(OperationalPanel operationPanel) {
		setSize(300, 150);
		setBackground(new Color(225, 225, 225));
		setLocation(0, 450);
		setVisible(false);
		setBorder(new TitledBorder(null, "Presets", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		// set all preset buttons
		buttons.add(new PresetButton("Kindle", 758, 1024, operationPanel));
		buttons.add(new PresetButton("iPhone", 320, 480, operationPanel));
		buttons.add(new PresetButton("iPhone(Retina)", 640, 960, operationPanel));
		buttons.add(new PresetButton("iPad", 1024, 1024, operationPanel));
		buttons.add(new PresetButton("iPad(Retina)", 2048, 2048, operationPanel));
		buttons.add(new PresetButton("PC(1024x768)", 1024, 768, operationPanel));
		//add all buttons to the panel
		for (JButton button : buttons) {
			add(button);
		}

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

class PresetButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int presetWidth, presetHeight;
	private OperationalPanel targetPanel;

	PresetButton(String s, int width, int height, OperationalPanel targetPanel) {
		super(s);
		setSize(getPreferredSize());
		presetWidth = width;
		presetHeight = height;
		this.targetPanel = targetPanel;
		addActionListener(this);
	}

	public int getPresetWidth() {
		return presetWidth;
	}

	public int getPresetHeight() {
		return presetHeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		targetPanel.getChckbxGrayscale().setSelected(false);
		targetPanel.setWidthText(String.valueOf(presetWidth));
		targetPanel.setHeightText(String.valueOf(presetHeight));
		Main.window.update();
	}

}