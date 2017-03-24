package dkeep.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.GameObject;

public class PanelGame extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private Game game;
	private GameObject[][] map;
	private boolean running = false;
	private BufferedImage background;
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
	public PanelGame() {
	
		try {
			background = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
			empty = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
			wall = ImageIO.read(getClass().getResource("resources/game/wall.jpg"));
			heroAlive = ImageIO.read(getClass().getResource("resources/game/heroAlive.jpg"));
			/*heroArmed = ImageIO.read(getClass().getResource("resources/game/heroArmed.jpg"));
			heroArmedKey = ImageIO.read(getClass().getResource("resources/game/heroArmedKey.jpg"));
			heroDead = ImageIO.read(getClass().getResource("resources/game/heroDead.jpg"));
			guardRookie = ImageIO.read(getClass().getResource("resources/game/guardRookie.jpg"));
			guardDrunken = ImageIO.read(getClass().getResource("resources/game/guardDrunken.jpg"));
			guardSleep = ImageIO.read(getClass().getResource("resources/game/guardSleep.jpg"));
			guardSuspicious = ImageIO.read(getClass().getResource("resources/game/guardSuspicious.jpg"));*/
			normalDoorClosed = ImageIO.read(getClass().getResource("resources/game/normalDoorClosed.jpg"));
			normalDoorOpened = ImageIO.read(getClass().getResource("resources/game/normalDoorOpened.jpg"));
			exitDoorOpened = ImageIO.read(getClass().getResource("resources/game/exitDoorOpened.jpg"));
			exitDoorClosed = ImageIO.read(getClass().getResource("resources/game/exitDoorClosed.jpg"));
			/*lever = ImageIO.read(getClass().getResource("resources/game/lever.jpg"));
			key = ImageIO.read(getClass().getResource("resources/game/key.jpg"));
			ogre = ImageIO.read(getClass().getResource("resources/game/ogre.jpg"));
			ogreStunned = ImageIO.read(getClass().getResource("resources/game/ogreStunned.jpg"));
			club = ImageIO.read(getClass().getResource("resources/game/club.jpg"));
			clubKey = ImageIO.read(getClass().getResource("resources/game/clubKey.jpg"));*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setGame(Game game)
	{
		this.addKeyListener(this);
		this.game = game;
		map = game.getMap();
		running = true;
		this.setFocusable(true);
		this.requestFocus();
		repaint();
		
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                PanelGame.this.grabFocus();
            }
        });
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		
		
		if(running)
		{
			game.printDungeonString();
			for(int i = 0; i<map.length; i++)
			{
				for (int j = 0; j<map[i].length; j++)
				{
					graphics.drawImage(auxSwitch(game.getDungeon().getDungeonInstant()[i][j].toString()),null,j*32,i*32);
				}
			}
			
		}
		else
		{
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			graphics.drawImage(background, 0, 0, (int)screen.getWidth(), (int)screen.getHeight(), null);
		}
	}
	
	public BufferedImage auxSwitch(String tile)
	{
		switch(tile)
		{
		case " ":
		return empty;
		case "H":
		return heroAlive;
		case "S":
		case "I":
			return exitDoorClosed;
		}
		
		
		
		return wall;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(running)
		{
			int code = e.getKeyCode();
			switch(code)
			{
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				game.playerTurn("w");
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				game.playerTurn("a");
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				game.playerTurn("d");
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				game.playerTurn("s");
				break;
			}
			repaint();
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
