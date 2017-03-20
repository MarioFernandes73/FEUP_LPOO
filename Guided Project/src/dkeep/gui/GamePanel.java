package dkeep.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import dkeep.logic.GameState;
import dkeep.logic.Game;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private BufferedImage back;
	private BufferedImage save;
	
	private JButton buttonBack;
	private JButton buttonSave;
	
	private GameRunning gameRunningPanel;
	private JPanel buttons;
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		try
		{			
			//load images
			this.background = ImageIO.read(getClass().getResource("resources/game/background.jpg"));	
			this.back = ImageIO.read(getClass().getResource("resources/game/back.jpg"));	
			this.save = ImageIO.read(getClass().getResource("resources/game/save.jpg"));	
			
			//create components
			createComponents();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void createComponents()
	{		
		buttons = new JPanel(); 
		buttons.setBounds(500,0,500,500);
		buttons.setBackground(Color.BLACK);
		
		this.buttonBack = new JButton(new ImageIcon(back));
		//this.buttonBack.setBounds(720,0,360,720);
		buttons.add(buttonBack);
		
		this.buttonSave = new JButton(new ImageIcon(save));
		//this.buttonSave.setBounds(100,200,100,100);
		buttons.add(buttonSave);
		
		buttons.setVisible(true);
		this.add(buttons);
		
		
		gameRunningPanel = new GameRunning(new Game(new GameState(1)));
		gameRunningPanel.setBounds(0,0,500,500);
		
		gameRunningPanel.setVisible(true);
		this.add(gameRunningPanel);
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(this.background, 0, 0, null);
	}
	
	@Override
	public Component[] getComponents()
	{
		Component[] components = {buttonBack};
		return components;
	}
	
	public JButton getButtonBack()
	{
		return this.buttonBack;
	}
}
