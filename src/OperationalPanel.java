import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OperationalPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField pixelWidthField;
	private JTextField pixelHeightField;
	private JTextField fileNameField;
	private JTextField numberField;
	private JCheckBox chckbxGrayscale;
	final LoadingWheel animation;

	public JCheckBox getChckbxGrayscale() {
		return chckbxGrayscale;
	}

	public OperationalPanel() {
		setSize(300, 450);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		animation = new LoadingWheel(50);
		animation.setLocation(10, 10);
		add(animation);
		animation.setVisible(false);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(10, 122, 118, 16);
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHeight);

		JLabel lblWidthPerPixel = new JLabel("Pixel Width");
		lblWidthPerPixel.setBounds(10, 156, 118, 16);
		lblWidthPerPixel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblWidthPerPixel);

		JLabel lblHeightPerPixel = new JLabel("Pixel Height");
		lblHeightPerPixel.setBounds(10, 190, 118, 16);
		lblHeightPerPixel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHeightPerPixel);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 90, 118, 16);
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblWidth);

		widthField = new JTextField();
		widthField.setText("640");
		widthField.setBounds(128, 84, 134, 28);
		add(widthField);
		widthField.setColumns(10);

		heightField = new JTextField();
		heightField.setText("960");
		heightField.setBounds(128, 116, 134, 28);
		add(heightField);
		heightField.setColumns(10);

		pixelWidthField = new JTextField();
		getPixelWidthField().setText("20");
		getPixelWidthField().setBounds(128, 150, 134, 28);
		add(getPixelWidthField());
		getPixelWidthField().setColumns(10);

		pixelHeightField = new JTextField();
		getPixelHeightField().setText("20");
		getPixelHeightField().setBounds(128, 184, 134, 28);
		add(getPixelHeightField());
		getPixelHeightField().setColumns(10);

		final JLabel lblNumber = new JLabel("Number");
		lblNumber.setVisible(false);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumber.setBounds(10, 316, 118, 16);
		add(lblNumber);

		numberField = new JTextField();
		numberField.setText("1");
		numberField.setVisible(false);
		numberField.setBounds(128, 310, 134, 28);
		add(numberField);
		numberField.setColumns(10);

		chckbxGrayscale = new JCheckBox("GrayScale");
		chckbxGrayscale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()) {
					Gui.isGrayScale = true;
				} else {
					Gui.isGrayScale = false;
				}
				Main.window.update();
			}
		});
		chckbxGrayscale.setBounds(128, 218, 134, 23);
		chckbxGrayscale.setOpaque(false);
		add(chckbxGrayscale);

		final JCheckBox chckbxSaveMultiple = new JCheckBox("Save Multiple");
		chckbxSaveMultiple.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()) {
					numberField.setVisible(true);
					lblNumber.setVisible(true);
					Gui.saveMultiple = true;
				} else {
					numberField.setVisible(false);
					lblNumber.setVisible(false);
					Gui.saveMultiple = false;
				}
			}
		});
		chckbxSaveMultiple.setBounds(128, 247, 134, 23);
		chckbxSaveMultiple.setOpaque(false);
		add(chckbxSaveMultiple);

		JButton btnRepaint = new JButton("Preview");
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.window.update();
			}
		});
		btnRepaint.setBounds(16, 350, 91, 29);
		add(btnRepaint);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {

						int fileNameCount = 0;
						int fileNumCount = 0;
						int fileNum = Integer.parseInt(numberField.getText());
						File destFolder = new File("RandomImages");
						if (!destFolder.exists())
							destFolder.mkdir();

						while (fileNumCount < fileNum) {
							File destination;
							do {
								animation.setVisible(true);
								destination = new File("RandomImages"
										+ File.separator
										+ fileNameField.getText()
										+ "_"
										+ Main.window.imagePanel.getImage()
												.getWidth()
										+ "x"
										+ Main.window.imagePanel.getImage()
												.getHeight() + "_"
										+ fileNameCount + ".png");
								fileNameCount++;
								System.out.println(destination
										+ " Name exist!!!");
							} while (destination.exists());
							try {
								animation.setVisible(true);
								ImageIO.write(
										Main.window.imagePanel.getImage(),
										"png", destination);
								fileNumCount++;
								System.out.println("I wrote an image");
							} catch (IOException e1) {
							}
							Main.window.update();
						}
						animation.setVisible(false);
					}
				}.start();
			}
		});
		btnSave.setBounds(119, 350, 75, 29);
		add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(206, 350, 75, 29);
		add(btnExit);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(10, 282, 118, 16);
		add(lblName);

		fileNameField = new JTextField();
		fileNameField.setText("random");
		fileNameField.setBounds(128, 276, 70, 28);
		add(fileNameField);
		fileNameField.setColumns(10);

		JLabel lblpng = new JLabel(".png");
		lblpng.setBounds(199, 282, 39, 16);
		add(lblpng);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.PLAIN, 48));
		g.drawString("Settings", 80, 50);
	}

	public String getWidthText() {
		return widthField.getText();
	}

	public String getHeightText() {
		return heightField.getText();
	}

	public void setWidthText(String s) {
		widthField.setText(s);
	}

	public void setHeightText(String s) {
		heightField.setText(s);
	}

	public JTextField getPixelWidthField() {
		return pixelWidthField;
	}

	public JTextField getPixelHeightField() {
		return pixelHeightField;
	}
}
