import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// the picture
	private BufferedImage image;

	// Constructor
	public ImagePanel(BufferedImage image) {
		super();
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}
}
