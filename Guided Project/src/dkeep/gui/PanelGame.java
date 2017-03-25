package dkeep.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.GameObject;
import dkeep.logic.GameState;

public class PanelGame extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private Game game;
	private GameObject[][] map;
	private boolean running = false;
	private Assets gameImages;
	private JLabel labelGameState;

	/**
	 * Create the panel.
	 */
	public PanelGame(Assets images, JLabel labelGameState) {
		this.gameImages = images;
		this.labelGameState = labelGameState;
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                PanelGame.this.grabFocus();
            }
        });
		this.addKeyListener(this);
		
		this.setFocusable(true);
	}

	public void setGame(Game game)
	{
		this.game = null;
		this.game = game;
		if(game == null)
		{
			running = false;
			repaint();
			return;
		}
		this.labelGameState.setText("<html>Get to the lever!<br>Avoid the guard!</html>");
		
		map = game.getMap();
		running = true;
		this.requestFocus();
		
		repaint();

	}

	public void moveHero(String movement)
	{
		this.game.playerTurn(movement);
		repaint();
	}
	
	public Game getGame()
	{
		return this.game;
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
			Dimension screen = this.getSize();
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
			if(game.getGameState().running == false)
			{
				if((!game.getHero().isDead()) && game.getGameState().currentLevel == 1)
				{
					this.game = new Game(new GameState(2));
				}
				else if(game.getHero().isDead())
				{
					//YOU LOSE!
					this.running = false;
					this.game = null;
					this.gameImages.background = this.gameImages.youLose;
				}
				else if((!game.getHero().isDead()) && game.getGameState().currentLevel == 2)
				{
					//YOU WIN!
					this.running = false;
					this.game = null;
					this.gameImages.background = this.gameImages.youWin;
				}
			}

			
			repaint();
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
