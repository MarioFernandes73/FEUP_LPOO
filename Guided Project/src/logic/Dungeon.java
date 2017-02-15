package logic;

import java.util.ArrayList;
import logic.Character.State;


public class Dungeon {
	
	private int height;
	private int width;
	private static int dungeon[][];
	
	//Default Dungeon (used for early tests)
	static int[][] defaultDungeon1 = 
		{{1,1,1,1,1,1,1,1,1,1},
		 {1,2,0,0,4,0,1,0,3,1},
		 {1,1,1,0,1,1,1,0,0,1},
		 {1,0,4,0,4,0,1,0,0,1},
		 {1,1,1,0,1,1,1,0,0,1},
		 {6,0,0,0,0,0,0,0,0,1},
		 {6,0,0,0,0,0,0,0,0,1},
		 {1,1,1,0,1,1,1,1,0,1},
		 {1,0,4,0,4,0,1,8,0,1},
		 {1,1,1,1,1,1,1,1,1,1}};
	
	static int[][] defaultDungeon2 = 
		{{1,1,1,1,1,1,1,1,1,1},
		 {4,0,0,0,10,0,0,0,9,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,2,0,0,0,0,0,0,0,1},
		 {1,1,1,1,1,1,1,1,1,1}};
	
	
	public Dungeon(int level)
	{
		if(level == 1)
		{
			dungeon = defaultDungeon1;			
		}
		else if(level == 2)
		{
			dungeon = defaultDungeon2;
		}

		height = dungeon.length;	//generic defaultDungeon height
		width = dungeon[0].length;	//length of the first line of the defaultDungeon; generic since defaultDungeon always has the same width
	}

	public int[][] getDungeon()
	{
		return dungeon;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
	public String printDungeonString()
	{
		String dungeonString = "";
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = Auxiliary.identifierSwitch(dungeon[i][j]);
				dungeonString += currentTile + " ";
			}
			dungeonString += "\n";
		}
		return dungeonString;
	}
	
	public void updateDungeon(Hero hero, Guard guard, Key key, Lever lever, ArrayList<Door> doors)
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final int currentTile = dungeon[i][j];
				if(currentTile != 0 && currentTile != 1)
				{
					dungeon[i][j] = 0;
				}
			}
		}
		updateDoors(doors);
		updateObjects(hero, guard, key, lever);
	}
	
	public void updateObjects(Hero hero, Guard guard, Key key, Lever lever)
	{
		if(key != null)
		{
			final Point keyCoord = key.getCoord();
			final int keyIdentifier = key.getIdentifier();
			updateTile(keyCoord, keyIdentifier);
		}
		if(lever != null)
		{
			final Point leverCoord = lever.getCoord();
			final int leverIdentifier = lever.getIdentifier();
			updateTile(leverCoord, leverIdentifier);
		}
		if (hero.isDead() == false)
		{
			final Point heroCoord = hero.getCoord();
			final int heroIdentifier = hero.getIdentifier();
			updateTile(heroCoord, heroIdentifier);
		}
		
		if(guard.isDead() == false)
		{
			final Point guardCoord = guard.getCoord();
			final int guardIdentifier = guard.getIdentifier();
			updateTile(guardCoord, guardIdentifier);
		}

	}
	
	public void updateDoors(ArrayList<Door> doors)
	{
		for (int i = 0; i < doors.size(); i++)
		{
			final Point doorCoord = doors.get(i).getCoord();
			final int doorIdentifier = doors.get(i).getIdentifier();
			updateTile (doorCoord, doorIdentifier);
		}
	}
	
	public void updateTile(Point p, int identifier)
	{
		final int xCoord = p.getX();
		final int yCoord = p.getY();
		dungeon[yCoord][xCoord] = identifier;
	}
	
	public void changeDoors(ArrayList<Door> doors)
	{
		for(int i = 0; i < doors.size(); i++)
		{
			final int currentIdentifier = doors.get(i).getIdentifier();
			if(currentIdentifier == 4)
			{
				doors.get(i).setIdentifier(5);
			}
			else if(currentIdentifier == 5)
			{
				doors.get(i).setIdentifier(4);
			}
			else if(currentIdentifier == 6)
			{
				doors.get(i).setIdentifier(7);
			}
			else if(currentIdentifier == 7)
			{
				doors.get(i).setIdentifier(6);
			}
		}
	}
	
	//checks adjacent tiles for a specific identifier (does nothing yet)
	public boolean checkAdjacent(int x, int y, int identifier)
	{
		if(x == 0 || x == width)
		{
			final int adjacentLeft = dungeon[x][y-1];
			final int adjacentRight = dungeon[x][y+1];
		}
		else if (y == 0 || y == width)
		{
			final int adjacentUp = dungeon[x-1][y];
			final int adjacentDown = dungeon[x+1][y];
		}
		return false;
	}

}
