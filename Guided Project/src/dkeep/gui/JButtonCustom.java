package dkeep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class JButtonCustom extends JButton {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public JButtonCustom(BufferedImage image)
	{
		this.image = image;

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		
		graphics2D.drawImage(image, 0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight(), null);
		
	}
	
}
