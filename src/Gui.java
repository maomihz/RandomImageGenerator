import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Gui extends JComponent {
	
	BufferedImage pic = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
	
	
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Gui gui = new Gui();
		window.add(gui);
		window.pack();		
		window.setVisible(true);
	}
	
	public Gui() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 600 && e.getX() < 650 && e.getY() > 300 && e.getY() < 330) {
					repaint();
				}
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800,600);
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(new Color(214,21,45));
//		g.fillRect(0, 0, 800, 600);

		Graphics picg = pic.getGraphics();
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				picg.setColor(randColor());
				picg.fillRect(i*80, j*80, 80, 80);
			}
		}
		
		g.drawImage(pic, 100,100,null);
		try {
			ImageIO.write(pic, "png", new java.io.File("Generated.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawRoundRect(600, 300, 50, 30, 5, 5);
		g.drawString("repaint", 603,318);
	}
	
	private Color randColor() {
		return new Color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
	}
}
