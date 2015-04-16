import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoadingWheel extends JPanel implements ActionListener{
	private Color primaryColor = Gui.randColor(false);
	private Color secondaryColor = Gui.randColor(false);
	private int sideLength;
	private int secondarySideLength;
	private Point secondaryLoc;
	private int animationSpeed = 2;
	
	private Timer animator;
	
	public LoadingWheel(int sideLength) {
		this.sideLength = sideLength;
		setSize(sideLength,sideLength);
		setOpaque(false);
		secondarySideLength = 3;
		secondaryLoc = new Point(sideLength / 2, sideLength / 2);
		
		animator = new Timer(30,this);
		animator.start();
		
		Timer speedTimer = new Timer(400, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				animationSpeed += 1;
			}
		});
		speedTimer.start();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(primaryColor);
		g.fillOval(0, 0, sideLength, sideLength);
		g.setColor(secondaryColor);
		g.fillOval(sideLength / 2 - secondarySideLength / 2, sideLength / 2 - secondarySideLength / 2, secondarySideLength, secondarySideLength);
	}
	
	public void actionPerformed(ActionEvent e) {
		secondarySideLength += animationSpeed;
		if (secondarySideLength > sideLength) {
			primaryColor = secondaryColor;
			secondaryColor = Gui.randColor(false);
			secondarySideLength = 0;
			animationSpeed = 2;
		}
		repaint();
	}
	
	
}
