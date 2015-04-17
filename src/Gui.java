import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// image is gray scale
	public static boolean isGrayScale, saveMultiple;
	// 4 main panels
	private PreviewPanel previewPanel;
	public PresetPanel presetPanel;
	public ImagePanel imagePanel;
	private OperationalPanel operationPanel;

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
		setLocationRelativeTo(null);
		// create and add operation panel
		operationPanel = new OperationalPanel();
		getContentPane().add(operationPanel);
		// create and add preview panel
		previewPanel = new PreviewPanel();
		getContentPane().add(previewPanel);
		// create and add image panel to preview panel
		imagePanel = new ImagePanel(new BufferedImage(345, 55,
				BufferedImage.TYPE_INT_ARGB));
		previewPanel.add(imagePanel);
		imagePanel.setPreferredSize(getPreferredSize());
		// create and add preset panel
		presetPanel = new PresetPanel(operationPanel);
		getContentPane().add(presetPanel);

		update();
	}

	// [main method to generate image]!
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

	// update the width etc.
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
