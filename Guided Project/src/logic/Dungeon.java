package logic;

import java.util.ArrayList;
import logic.Character.State;


public class Dungeon {
	
	private int height;
	private int width;
	private static char dungeon[][];
	
	//Default Dungeon (used for early tests)
	static char[][] defaultDungeon1 = 
		{{'X','X','X','X','X','X','X','X','X','X'},
		 {'X','H',' ',' ','I',' ','X',' ','G','X'},
		 {'X','X','X',' ','X','X','X',' ',' ','X'},
		 {'X',' ','I',' ','I',' ','X',' ',' ','X'},
		 {'X','X','X',' ','X','X','X',' ',' ','X'},
		 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X','X','X',' ','X','X','X','X',' ','X'},
		 {'X',' ','I',' ','I',' ','X','k',' ','X'},
		 {'X','X','X','X','X','X','X','X','X','X'}};
	
	static char[][] defaultDungeon2 = 
		{{'X','X','X','X','X','X','X','X','X','X'},
		 {'I',' ',' ',' ','O',' ',' ',' ','k','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X','X','X','X','X','X','X','X','X','X'}};
	
	
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

	public char[][] getDungeon()
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
				dungeonString += dungeon[i][j] + " ";
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
				final char currentTile = dungeon[i][j];
				if(currentTile != 'X' && currentTile != ' ' && currentTile != 'k' && currentTile != 'I' && currentTile != 'S')
				{
					dungeon[i][j] = ' ';
				}
			}
		}
		
		updateObjects(hero, guard, key, lever, doors);
	}
	
	public void updateObjects(Hero hero, Guard guard, Key key, Lever lever, ArrayList<Door> doors)
	{
		if (hero.isDead() == false)
		{
			dungeon[hero.getCoord().getY()][hero.getCoord().getX()] = 'H';
		}
		
		if(guard.isDead() == false)
		{
			dungeon[guard.getCoord().getY()][guard.getCoord().getX()] = 'G';
		}
		if(key != null)
		{
			dungeon[key.getCoord().getY()][key.getCoord().getX()] = 'k';
		}
		if(lever != null)
		{
			dungeon[lever.getCoord().getY()][lever.getCoord().getX()] = 'k';
		}
		updateDoors(doors);
	}
	
	public void updateDoors(ArrayList<Door> doors)
	{
		
	}
	
	public void changeDoors()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if(dungeon[i][j] == 'I')
				{
					dungeon[i][j] = 'S';
				}
				else if(dungeon[i][j] == 'S')
				{
					dungeon[i][j] = 'I';
				}
			}
		}
	}
	
	//spawn functions: once the map is ready, the objects will spawn on the map on the marked locations.
	public Hero spawnHero()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = dungeon[i][j];
				if(currentTile == 'H')
					return new Hero(j,i,'H');
			}
		}
		return null;
	}
	
	public Guard spawnGuard()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = dungeon[i][j];
				if(currentTile == 'G')
					return new Guard(j,i,'G');
			}
		}
		return null;
	}
	
	public Lever spawnLever(int currentLevel)
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = dungeon[i][j];
				if(currentTile == 'k' && currentLevel == 1)
				{
					return new Lever(j,i,'k');
				}
					
			}
		}
		return null;
	}
	
	public Key spawnKey(int currentLevel)
	{
		if(currentLevel == 2)
		{
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
				{
					final char currentTile = dungeon[i][j];
					if(currentTile == 'k')
					{
						return new Key(j,i,'k');
					}
						
				}
			}
		}
		return null;
	}
	
	public ArrayList<Door> spawnDoors()
	{
		ArrayList<Door>doors = new ArrayList<Door>();
		for (int i =	 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = dungeon[i][j];
				if(currentTile == 'I' || currentTile == 'S')
				{
					final boolean trueDoor = checkAdjacent(i,j,'D');
					final Door door = new Door(j,i,currentTile,trueDoor);
					doors.add(door);
				}
					
			}
		}
		return doors;
	}
	
	//checks adjacent tiles for a specific symbol
	public boolean checkAdjacent(int x, int y, char symbol)
	{
		if(x == 0 || x == width)
		{
			final char adjacentLeft = dungeon[x][y-1];
			final char adjacentRight = dungeon[x][y+1];
			if(symbol == 'D' && (adjacentLeft == 'I' || adjacentRight == 'S'))
			{
				return true;
			}
		}
		else if (y == 0 || y == width)
		{
			final char adjacentUp = dungeon[x-1][y];
			final char adjacentDown = dungeon[x+1][y];
			if(symbol == 'D' && (adjacentUp == 'I' || adjacentDown == 'S'))
			{
				return true;
			}
		}
		return false;
	}

}
