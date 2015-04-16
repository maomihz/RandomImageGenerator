import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Gui extends JFrame{
	JButton btnRepaint,btnSave;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField pixelWidthField;
	private JTextField pixelHeightField;
	private JTextField fileNameField;
	private JTextField numberField;
	
	private JPanel imagePanel;
	
	//the picture
	private BufferedImage image = new BufferedImage(345,55,BufferedImage.TYPE_INT_ARGB);
	
	//image is gray scale
	boolean isGrayScale;
	boolean saveMultiple;
	int fileNum;
	
	//image width
	int width,height;


	public Gui() throws IOException{
		setSize(800,600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Random Image Generator by Maomi");
		getContentPane().setLayout(null);
		setIconImage(ImageIO.read(new File("res" + File.separator + "icon.png")));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setResizable(false);


		JPanel operationPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("Serif", Font.PLAIN,48));
				g.drawString("Settings", 80, 50);
			}
		};
		operationPanel.setSize(300,450);
		operationPanel.setBackground(Color.LIGHT_GRAY);
		operationPanel.setLocation(0, 0);
		getContentPane().add(operationPanel);
		operationPanel.setLayout(null);
		
		final LoadingWheel animation = new LoadingWheel(50);
		animation.setLocation(10, 10);
		operationPanel.add(animation);
		animation.setVisible(false);
		
		
		final JPanel previewPanel = new JPanel();
		previewPanel.setSize(500,600);
		previewPanel.setBackground(SystemColor.window);
		previewPanel.setLocation(300, 0);
		getContentPane().add(previewPanel);
		previewPanel.setLayout(null);
		
		imagePanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);

			}
			public Dimension getPreferredSize() {
				return new Dimension(image.getWidth(), image.getHeight());
			}
		};
		imagePanel.setSize(imagePanel.getPreferredSize());
		imagePanel.setLocation(0,0);
		previewPanel.add(imagePanel);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(10, 122, 118, 16);
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		operationPanel.add(lblHeight);

		JLabel lblWidthPerPixel = new JLabel("Pixel Width");
		lblWidthPerPixel.setBounds(10, 156, 118, 16);
		lblWidthPerPixel.setHorizontalAlignment(SwingConstants.RIGHT);
		operationPanel.add(lblWidthPerPixel);

		JLabel lblHeightPerPixel = new JLabel("Pixel Height");
		lblHeightPerPixel.setBounds(10, 190, 118, 16);
		lblHeightPerPixel.setHorizontalAlignment(SwingConstants.RIGHT);
		operationPanel.add(lblHeightPerPixel);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 90, 118, 16);
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		operationPanel.add(lblWidth);

		widthField = new JTextField();
		widthField.setText("640");
		widthField.setBounds(128, 84, 134, 28);
		operationPanel.add(widthField);
		widthField.setColumns(10);

		heightField = new JTextField();
		heightField.setText("960");
		heightField.setBounds(128, 116, 134, 28);
		operationPanel.add(heightField);
		heightField.setColumns(10);

		pixelWidthField = new JTextField();
		pixelWidthField.setText("20");
		pixelWidthField.setBounds(128, 150, 134, 28);
		operationPanel.add(pixelWidthField);
		pixelWidthField.setColumns(10);

		pixelHeightField = new JTextField();
		pixelHeightField.setText("20");
		pixelHeightField.setBounds(128, 184, 134, 28);
		operationPanel.add(pixelHeightField);
		pixelHeightField.setColumns(10);
		
		final JLabel lblNumber = new JLabel("Number");
		lblNumber.setVisible(false);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumber.setBounds(10, 316, 118, 16);
		operationPanel.add(lblNumber);
		
		numberField = new JTextField();
		numberField.setText("1");
		numberField.setVisible(false);
		numberField.setBounds(128, 310, 134, 28);
		operationPanel.add(numberField);
		numberField.setColumns(10);

		final JCheckBox chckbxGrayscale = new JCheckBox("GrayScale");
		chckbxGrayscale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((JCheckBox)e.getSource()).isSelected()) {
					isGrayScale = true;
				} else {
					isGrayScale = false;
				}
				update();
			}
		});
		chckbxGrayscale.setBounds(128, 218, 134, 23);
		chckbxGrayscale.setOpaque(false);
		operationPanel.add(chckbxGrayscale);

		final JCheckBox chckbxSaveMultiple = new JCheckBox("Save Multiple");
		chckbxSaveMultiple.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (((JCheckBox)e.getSource()).isSelected()) {
					numberField.setVisible(true);
					lblNumber.setVisible(true);
					saveMultiple = true;
				} else {
					numberField.setVisible(false);
					lblNumber.setVisible(false);
					saveMultiple = false;
				}
			}
		});
		chckbxSaveMultiple.setBounds(128, 247, 134, 23);
		chckbxSaveMultiple.setOpaque(false);
		operationPanel.add(chckbxSaveMultiple);

		JButton btnRepaint = new JButton("Preview");
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnRepaint.setBounds(16, 350, 91, 29);
		operationPanel.add(btnRepaint);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						
						int fileNameCount = 0;
						int fileNumCount = 0;
						int fileNum = Integer.parseInt(numberField.getText());
						File destFolder = new File("RandomImages");
						if (!destFolder.exists()) destFolder.mkdir();
						
						while (fileNumCount < fileNum) {
							File destination;
							do {
								animation.setVisible(true);
								destination = new File("RandomImages" + File.separator + fileNameField.getText() + "_" + image.getWidth() + "x" + image.getHeight() + "_" + fileNameCount + ".png");
								fileNameCount++;
								System.out.println(destination + " Name exist!!!");
							} while (destination.exists());
							try {
								animation.setVisible(true);
								ImageIO.write(image, "png", destination);
								fileNumCount++;
								System.out.println("I wrote an image");
							} catch (IOException e1) {}
							update();
						}
						animation.setVisible(false);
					}
				}.start();
			}
		});
		btnSave.setBounds(119, 350, 75, 29);
		operationPanel.add(btnSave);

		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(206, 350, 75, 29);
		operationPanel.add(btnExit);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(10, 282, 118, 16);
		operationPanel.add(lblName);

		fileNameField = new JTextField();
		fileNameField.setText("random");
		fileNameField.setBounds(128, 276, 70, 28);
		operationPanel.add(fileNameField);
		fileNameField.setColumns(10);

		JLabel lblpng = new JLabel(".png");
		lblpng.setBounds(199, 282, 39, 16);
		operationPanel.add(lblpng);
		
		final JPanel presetPanel = new JPanel();
		presetPanel.setBorder(new TitledBorder(null, "Presets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		presetPanel.setSize(300,150);
		presetPanel.setBackground(new Color(225,225,225));
		presetPanel.setLocation(0,450);
		getContentPane().add(presetPanel);
		presetPanel.setVisible(false);
		
		JButton presetKindle = new JButton("Kindle");
		presetKindle.setBounds(6, 24, 83, 29);
		presetKindle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(true);
				widthField.setText("758");
				heightField.setText("1024");
				update();
			}
		});
		presetPanel.setLayout(null);
		presetPanel.add(presetKindle);
		
		JButton presetIphone = new JButton("iPhone");
		presetIphone.setBounds(82, 24, 86, 29);
		presetIphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(false);
				widthField.setText("320");
				heightField.setText("480");
				update();
			}
		});
		presetPanel.add(presetIphone);
		
		JButton presetIphoneRetina = new JButton("iPhone(Retina)");
		presetIphoneRetina.setBounds(161, 24, 133, 29);
		presetIphoneRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(false);
				widthField.setText("640");
				heightField.setText("960");
				update();
			}
		});
		presetPanel.add(presetIphoneRetina);
		
		JButton presetIpad = new JButton("iPad");
		presetIpad.setBounds(92, 49, 75, 29);
		presetIpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(false);
				widthField.setText("1024");
				heightField.setText("1024");
				update();
			}
		});
		presetPanel.add(presetIpad);
		
		JButton presetIpadRetina = new JButton("iPad(Retina)");
		presetIpadRetina.setBounds(171, 49, 117, 29);
		presetIpadRetina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(false);
				widthField.setText("2048");
				heightField.setText("2048");
				update();
			}
		});
		presetPanel.add(presetIpadRetina);
		
		JButton presetComputer1024_768 = new JButton("Computer(1024x768)");
		presetComputer1024_768.setBounds(16, 77, 178, 29);
		presetComputer1024_768.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGrayscale.setSelected(false);
				widthField.setText("1024");
				heightField.setText("768");
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

	
	private void drawImage(BufferedImage image, boolean isGrayScale, int pixelWidth, int pixelHeight) {
		if (pixelWidth <= 0 || pixelHeight <= 0 || image == null || image.getHeight(null) <= 0 || image.getWidth(null) <= 0) {
			throw new IllegalArgumentException();
		}
		int xCellNum = (int)Math.ceil(image.getWidth(null) / (double)pixelWidth);
		int yCellNum = (int)Math.ceil(image.getHeight(null) / (double)pixelHeight);
		Graphics g = image.getGraphics();
		for (int j=0;j<yCellNum;j++) {
			for (int i=0;i<xCellNum;i++) {
				g.setColor(randColor(isGrayScale));
				g.fillRect(i*pixelWidth, j*pixelHeight, pixelWidth, pixelHeight);
			}
		}
	}
	
	public static Color randColor(boolean isGrayScale) {
		if (isGrayScale) {
			int c = (int)(Math.random() * 256);
			return new Color(c,c,c);
		} else {
			return new Color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
		}
	}
	
	private void update() {
		int width = Integer.parseInt(widthField.getText());
		int height = Integer.parseInt(heightField.getText());
		int pixelWidth = Integer.parseInt(pixelWidthField.getText());
		int pixelHeight = Integer.parseInt(pixelHeightField.getText());
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		drawImage(image,isGrayScale,pixelWidth,pixelHeight);
		repaint();
		imagePanel.setSize(imagePanel.getPreferredSize());
		System.out.println("Image Regenerated");
	}

	public static void main(String[] args) throws IOException{
		Gui window = new Gui();
		window.setVisible(true);
	}
}
