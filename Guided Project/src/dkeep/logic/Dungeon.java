package dkeep.logic;

import java.util.ArrayList;


public class Dungeon {
	
	private GameObject dungeon[][];
	
	
	public Dungeon(GameObject dungeon[][])
	{
		this.dungeon = dungeon;
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
				dungeonInstant[yCoord][xCoord] = currentObject;
				
				if(currentObject instanceof Ogre)
				{
					if(((Ogre) currentObject).getWeapon() != null && ((Ogre) currentObject).getWeapon().getCoord() != null)
					{
						int yCoordWeapon = ((Ogre) currentObject).getWeapon().getCoord().getY();
						int xCoordWeapon = ((Ogre) currentObject).getWeapon().getCoord().getX();
						if(dungeonInstant[yCoordWeapon][xCoordWeapon] instanceof Key)
						{
							dungeonInstant[yCoordWeapon][xCoordWeapon] = new Collision(currentObject.getCoord(),12);
						}
						else if(!(dungeonInstant[yCoordWeapon][xCoordWeapon] instanceof Collision))
						{
							dungeonInstant[yCoordWeapon][xCoordWeapon] = ((Ogre) currentObject).getWeapon();
						}
					}
				}
				
				
				
			}
		}
		
		for (int i = 0; i < dungeonInstant.length; i++)
		{
			for (int j = 0; j < dungeonInstant[i].length; j++)
			{
				dungeonString += dungeonInstant[i][j].toString() + " ";
			}
			dungeonString += "\n";
		}
		return dungeonString;
	}
	
	public GameObject getTile(Point p)
	{
		return dungeon[p.getY()][p.getX()];
	}
}
