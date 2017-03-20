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

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GameRunning gameRunningPanel;
	
	/**
	 * Create the panel.
	 */
	public PanelGame() {

			createComponents();

	}

	private void createComponents()
	{		

		
		
		gameRunningPanel = new GameRunning(new Game(new GameState(1)));
		gameRunningPanel.setBounds(0,0,500,500);
		
		gameRunningPanel.setVisible(true);
		this.add(gameRunningPanel);
		
	}
	
}
