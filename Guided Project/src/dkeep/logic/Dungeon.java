package dkeep.logic;

import java.util.ArrayList;


public class Dungeon {
	
	private GameObject dungeon[][];
	
	
	public Dungeon(GameObject dungeon[][])
	{
		this.dungeon = dungeon;
	}
	
	public GameObject[][] getDungeon()
	{
		return dungeon;
	}
	
	
	public void setTile(Point p, GameObject object)
	{
		dungeon[p.getY()][p.getX()] = object;
	}
	
	public String printDungeonString(ArrayList<GameObject> allObjects)
	{
		
		GameObject[][] dungeonInstant = new GameObject[dungeon.length][dungeon[0].length];
		
		for(int i = 0; i<dungeon.length; i++)
		{
			for(int j = 0; j<dungeon[i].length; j++)
			{
				dungeonInstant[i][j] = dungeon[i][j];
			}
		}
		
		String dungeonString = "";
		
		for(int i = 0; i<allObjects.size(); i++)
		{
			GameObject currentObject = allObjects.get(i);
			if(currentObject.getCoord() != null)
			{
				int yCoord = currentObject.getCoord().getY();
				int xCoord = currentObject.getCoord().getX();
				if(dungeonInstant[yCoord][xCoord] instanceof Key && currentObject instanceof Club)
				{
					dungeonInstant[yCoord][xCoord] = new Collision(currentObject.getCoord(),12);
				}
				else
				{
					dungeonInstant[yCoord][xCoord] = currentObject;
				}
			}
		}
		
		for (int i = 0; i < dungeonInstant.length; i++)
		{
			for (int j = 0; j < dungeonInstant[i].length; j++)
			{
				dungeonString += dungeonInstant[i][j].getSymbol() + " ";
			}
			dungeonString += "\n";
		}
		return dungeonString;
	}
	
	public boolean checkTile(Point p, GameObject object)
	{
		int xCoord = p.getX();
		int yCoord = p.getY();
		if(xCoord > 0 && yCoord > 0 && xCoord <= dungeon.length && yCoord <= dungeon[0].length)
		{
			if(dungeon[yCoord][xCoord].equals(object))
			{
				return true;
			}
		}

		return false;
	}	
	
	public GameObject getTile(Point p)
	{
		return dungeon[p.getY()][p.getX()];
	}

	public void openDoor(ArrayList<Door> doors, Point p)
	{
		for(int i = 0; i < doors.size(); i++)
		{
			final Point doorCoord = doors.get(i).getCoord();
			if(doorCoord.equals(p))
			{
				doors.get(i).changeOpenedState();
			}
		}
	}
}
