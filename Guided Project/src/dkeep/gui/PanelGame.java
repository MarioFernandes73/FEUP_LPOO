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
	private Assets gameImages;

	/**
	 * Create the panel.
	 */
	public PanelGame(Assets images) {
		this.gameImages = images;

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
					graphics.drawImage(auxSwitch(game.getDungeon().getDungeonInstant()[i][j].toString()),j*32,i*32,(j+1)*32, (i+1)*32,null);
				}
			}

		}
		else
		{
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			graphics.drawImage(gameImages.background, 0, 0, (int)screen.getWidth(), (int)screen.getHeight(), null);
		}
	}

	public BufferedImage auxSwitch(String tile)
	{
		switch(tile)
		{
		case " ":
			return gameImages.empty;
		case "X":
			return gameImages.wall;
		case "H":
			return gameImages.normalHero;
		case "G":
			return gameImages.guard;
		case "S":
			return gameImages.openDoor;
		case "I":
			return gameImages.closedDoor;
		case "k":
			return gameImages.key;
		case "$":
			return gameImages.obstructedKey;
		case "O":
			return gameImages.ogre;
		case "*":
			return gameImages.club;
		case "g":
			return gameImages.guardStunned;
		case "A":
			return gameImages.heroArmed;
		case "K":
			return gameImages.heroCarryingKey;
		case "8":
			return gameImages.ogreStunned;
			
		}



		return gameImages.wall;
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
