package dkeep.gui;

import dkeep.logic.Game;
import dkeep.logic.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class GameRunning extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game game;
	private int height = 10;
	private int width = 10;
	private Cell[][] cell =  new Cell[10][10];
	
	
	private BufferedImage wall;
	
	/**
	 * Create the panel.
	 */
	public GameRunning(Game game) {

		try 
		{
		this.game = game;
		this.setLayout(new GridLayout(width, height));
		populateGrid();
		
		this.wall = ImageIO.read(getClass().getResource("resources/game/wall.jpg"));
		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
/*	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		//drawDungeon(graphics);
	}*/
	
	private void drawDungeon(Graphics2D graphics)
	{
		GameObject[][] dungeon = game.getDungeon().getMap();
		for(int i = 0; i < dungeon.length; i++)
		{
			for(int j = 0; j < dungeon[i].length; j++)
			{
				switch(dungeon[i][j].toString())
				{
				case "X":
					graphics.drawImage(wall, j,i, null);
					break;
				}
			}
		}
	}
	
	private void populateGrid()
	{
		for(int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				this.add(cell[i][j] = new Cell(i, j));
			}
		}
	}

}
