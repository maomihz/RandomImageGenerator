import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class Gui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnRepaint, btnSave;

	private JPanel presetPanel, previewPanel;
	public ImagePanel imagePanel;
	private OperationalPanel operationPanel;
	// image is gray scale
	public static boolean isGrayScale, saveMultiple;
	int fileNum;

	// image width
	int width, height;

	public Gui() throws IOException {
		// initialize the GuiFrame
		setSize(800, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Random Image Generator by Maomi");
		getContentPane().setLayout(null);
		setIconImage(ImageIO
				.read(new File("res" + File.separator + "icon.png")));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setResizable(false);

		operationPanel = new OperationalPanel();
		getContentPane().add(operationPanel);

		previewPanel = new JPanel();
		previewPanel.setSize(500, 600);
		previewPanel.setBackground(SystemColor.window);
		previewPanel.setLocation(300, 0);
		getContentPane().add(previewPanel);
		previewPanel.setLayout(null);

		imagePanel = new ImagePanel(new BufferedImage(345, 55,
				BufferedImage.TYPE_INT_ARGB));
		previewPanel.add(imagePanel);
		imagePanel.setPreferredSize(getPreferredSize());

		// preset panel
		presetPanel = new JPanel();
		presetPanel.setBorder(new TitledBorder(null, "Presets",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		presetPanel.setSize(300, 150);
		presetPanel.setBackground(new Color(225, 225, 225));
		presetPanel.setLocation(0, 450);
		getContentPane().add(presetPanel);
		presetPanel.setVisible(false);

		JButton presetKindle = new JButton("Kindle");
		presetKindle.setBounds(6, 24, 83, 29);
		presetKindle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(true);
				operationPanel.setWidthText("758");
				operationPanel.setHeightText("1024");
				update();
			}
		});
		presetPanel.setLayout(null);
		presetPanel.add(presetKindle);

		JButton presetIphone = new JButton("iPhone");
		presetIphone.setBounds(82, 24, 86, 29);
		presetIphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("320");
				operationPanel.setHeightText("480");
				update();
			}
		});
		presetPanel.add(presetIphone);

		JButton presetIphoneRetina = new JButton("iPhone(Retina)");
		presetIphoneRetina.setBounds(161, 24, 133, 29);
		presetIphoneRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("640");
				operationPanel.setHeightText("960");
				update();
			}
		});
		presetPanel.add(presetIphoneRetina);

		JButton presetIpad = new JButton("iPad");
		presetIpad.setBounds(92, 49, 75, 29);
		presetIpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("1024");
				operationPanel.setHeightText("1024");
				update();
			}
		});
		presetPanel.add(presetIpad);

		JButton presetIpadRetina = new JButton("iPad(Retina)");
		presetIpadRetina.setBounds(171, 49, 117, 29);
		presetIpadRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("2048");
				operationPanel.setHeightText("2048");
				update();
			}
		});
		presetPanel.add(presetIpadRetina);

		JButton presetComputer1024_768 = new JButton("Computer(1024x768)");
		presetComputer1024_768.setBounds(16, 77, 178, 29);
		presetComputer1024_768.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationPanel.getChckbxGrayscale().setSelected(false);
				operationPanel.setWidthText("1024");
				operationPanel.setHeightText("768");
				update();
			}
		});
		presetPanel.add(presetComputer1024_768);

		JButton btnShowPresets = new JButton("Show Presets");
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
		btnShowPresets.setBounds(74, 389, 150, 29);
		operationPanel.add(btnShowPresets);

		update();
	}

	private void drawImage(BufferedImage image, boolean isGrayScale,
			int pixelWidth, int pixelHeight) {
		if (pixelWidth <= 0 || pixelHeight <= 0 || image == null
				|| image.getHeight(null) <= 0 || image.getWidth(null) <= 0) {
			throw new IllegalArgumentException();
		}
		int xCellNum = (int) Math.ceil(image.getWidth(null)
				/ (double) pixelWidth);
		int yCellNum = (int) Math.ceil(image.getHeight(null)
				/ (double) pixelHeight);
		Graphics g = image.getGraphics();
		for (int j = 0; j < yCellNum; j++) {
			for (int i = 0; i < xCellNum; i++) {
				g.setColor(randColor(isGrayScale));
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth,
						pixelHeight);
			}
		}
	}

	public static Color randColor(boolean isGrayScale) {
		if (isGrayScale) {
			int c = (int) (Math.random() * 256);
			return new Color(c, c, c);
		} else {
			return new Color((int) (Math.random() * 256),
					(int) (Math.random() * 256), (int) (Math.random() * 256));
		}
	}

	void update() {
		int width = Integer.parseInt(operationPanel.getWidthText());
		int height = Integer.parseInt(operationPanel.getHeightText());
		int pixelWidth = Integer.parseInt(operationPanel.getPixelWidthField()
				.getText());
		int pixelHeight = Integer.parseInt(operationPanel.getPixelHeightField()
				.getText());
		imagePanel.setImage(new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB));
		drawImage(imagePanel.getImage(), isGrayScale, pixelWidth, pixelHeight);
		repaint();
		imagePanel.setSize(imagePanel.getPreferredSize());
		System.out.println("Image Regenerated");
	}
}
