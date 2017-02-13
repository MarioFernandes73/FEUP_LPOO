package logic;

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
	
	public void updateDungeon(Hero hero, Guard guard, Key key, Lever lever)
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
		
		if(key != null)
		{
			dungeon[key.getCoord().getY()][key.getCoord().getX()] = 'k';
		}
		if(lever != null)
		{
			dungeon[lever.getCoord().getY()][lever.getCoord().getX()] = 'k';
		}
		
		if (hero.getState() != State.DEAD)
		{
			dungeon[hero.getCoord().getY()][hero.getCoord().getX()] = 'H';
		}
		
		if(guard.getState() != State.DEAD)
		{
			dungeon[guard.getCoord().getY()][guard.getCoord().getX()] = 'G';
		}
	}
	
	public void updateDoors()
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
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				final char currentTile = dungeon[i][j];
				if(currentTile == 'k' && currentLevel == 2)
				{
					return new Key(j,i,'k');
				}
					
			}
		}
		return null;
	}
	

}
