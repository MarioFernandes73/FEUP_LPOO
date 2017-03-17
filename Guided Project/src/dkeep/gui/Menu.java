package dkeep.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private BufferedImage newGame;
	private JButton buttonNewGame;
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		try
		{
			//load images
			this.background = ImageIO.read(getClass().getResource("resources/menu/background.jpg"));	
			this.newGame = ImageIO.read(getClass().getResource("resources/menu/newGame.jpg"));				
			
			//create components
			createComponents();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(this.background, 0, 0, null);
	}
	
	private void createComponents()
	{
		buttonNewGame = new JButton(new ImageIcon(newGame));
		buttonNewGame.setBounds(300,500,400,100);
		this.add(buttonNewGame);
	}
	
	public JButton getButtonNewGame()
	{
		return this.buttonNewGame;
	}
	
	public Component[] getComponents()
	{
		Component[] components = {buttonNewGame};
		
		
		return components;
	}
}
