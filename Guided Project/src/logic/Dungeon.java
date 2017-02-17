package logic;

import java.util.ArrayList;


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
	
	public void updateDungeon(Hero hero, ArrayList<Character> npcs, Key key, Lever lever, ArrayList<Door> doors)
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
		updateNpcs(npcs);
		updateObjects(hero, key, lever);
	}
	
	public void updateObjects(Hero hero, Key key, Lever lever)
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

			final Point heroCoord = hero.getCoord();
			final int heroIdentifier = hero.getIdentifier();
			updateTile(heroCoord, heroIdentifier);
		
	}
	
	public void updateNpcs(ArrayList<Character> npcs)
	{
		for (int i = 0; i<npcs.size(); i++)
		{
			final Character npc = npcs.get(i);
			if(npc.isDead() == false)
			{
				final Point guardCoord = npc.getCoord();
				final int guardIdentifier = npc.getIdentifier();
				updateTile(guardCoord, guardIdentifier);
			}
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
	
	//returns true if a given identifier is located in an adjacent tile, false otherwise
	public boolean checkAdjacent(Point p, int identifier)
	{
		final int x = p.getX();
		final int y = p.getY();
		if(x > 0 && dungeon[y][x-1]==identifier)//checks left
		{
			System.out.println("LEFT");
			return true;
		}
		if (x < (width-1) && dungeon[y][x+1]==identifier)//checks right
		{
			System.out.println("RIGHT");
			return true;
		}
		if((y > 0) && dungeon[y-1][x]==identifier)//checks top
		{
			System.out.println("TOP");
			return true;
		}
		if ((y < (height-1)) && dungeon[y+1][x]==identifier)//checks bottom
		{
			System.out.println("BOTTOM");
			return true;
		}
		return false;
	}
	
	public int getTile(Point p)
	{
		final int xCoord = p.getX();
		final int yCoord = p.getY();
		return dungeon[yCoord][xCoord];
	}

	public void openDoor(ArrayList<Door> doors, Point p)
	{
		for(int i = 0; i < doors.size(); i++)
		{
			final Point doorCoord = doors.get(i).getCoord();
			if(doorCoord == p)
			{
				doors.get(i).changeOpenedState();
			}
		}
	}
}
