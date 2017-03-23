package dkeep.gui;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.GameObject;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game game;
	private GameObject[][] map;
	private BufferedImage empty;
	private BufferedImage wall;
	private BufferedImage heroAlive;
	private BufferedImage heroArmed;
	private BufferedImage heroArmedKey;
	private BufferedImage heroDead;
	private BufferedImage guardRookie;
	private BufferedImage guardDrunken;
	private BufferedImage guardSleep;
	private BufferedImage guardSuspicious;
	private BufferedImage normalDoorClosed;
	private BufferedImage normalDoorOpened;
	private BufferedImage exitDoorOpened;
	private BufferedImage exitDoorClosed;
	private BufferedImage lever;
	private BufferedImage key;
	private BufferedImage ogre;
	private BufferedImage ogreStunned;
	private BufferedImage club;
	private BufferedImage clubKey;
	
	/**
	 * Create the panel.
	 */
	public PanelGame(Game game) {
		this.game = game;
		map = game.getMap();
		
		try {
			empty = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
			wall = ImageIO.read(getClass().getResource("resources/game/wall.jpg"));
			/*heroAlive = ImageIO.read(getClass().getResource("resources/game/heroAlive.jpg"));
			heroArmed = ImageIO.read(getClass().getResource("resources/game/heroArmed.jpg"));
			heroArmedKey = ImageIO.read(getClass().getResource("resources/game/heroArmedKey.jpg"));
			heroDead = ImageIO.read(getClass().getResource("resources/game/heroDead.jpg"));
			guardRookie = ImageIO.read(getClass().getResource("resources/game/guardRookie.jpg"));
			guardDrunken = ImageIO.read(getClass().getResource("resources/game/guardDrunken.jpg"));
			guardSleep = ImageIO.read(getClass().getResource("resources/game/guardSleep.jpg"));
			guardSuspicious = ImageIO.read(getClass().getResource("resources/game/guardSuspicious.jpg"));
			normalDoorClosed = ImageIO.read(getClass().getResource("resources/game/normalDoorClosed.jpg"));
			normalDoorOpened = ImageIO.read(getClass().getResource("resources/game/normalDoorOpened.jpg"));
			exitDoorOpened = ImageIO.read(getClass().getResource("resources/game/exitDoorOpened.jpg"));
			exitDoorClosed = ImageIO.read(getClass().getResource("resources/game/exitDoorClosed.jpg"));
			lever = ImageIO.read(getClass().getResource("resources/game/lever.jpg"));
			key = ImageIO.read(getClass().getResource("resources/game/key.jpg"));
			ogre = ImageIO.read(getClass().getResource("resources/game/ogre.jpg"));
			ogreStunned = ImageIO.read(getClass().getResource("resources/game/ogreStunned.jpg"));
			club = ImageIO.read(getClass().getResource("resources/game/club.jpg"));
			clubKey = ImageIO.read(getClass().getResource("resources/game/clubKey.jpg"));*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		for(int i = 0; i<map.length; i++)
		{
			for (int j = 0; j<map[i].length; j++)
			{
				graphics.drawImage(auxSwitch(map[j][i].toString()),null,i*32,j*32);		
			}
		}
	}
	
	public BufferedImage auxSwitch(String tile)
	{
		switch(tile)
		{
		case " ":
		return empty;
		}
		
		
		
		return wall;
	}
	
	
}
