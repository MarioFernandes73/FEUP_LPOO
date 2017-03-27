package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Represents the game objects container
 * You can call it the game board if you like so
 *
 */
public class Dungeon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents a dungeon without characters, only walls, empty spaces, doors and levers(only the skeleton)
	 */
	private GameObject dungeon[][];
	
	/**
	 * Represents a populated dungeon(with all characters)
	 * Look at it as the complete/final version of the dungeon
	 */
	private GameObject dungeonInstant[][];
	
	/**
	 * Dungeon constructor
	 * @param dungeon dungeon with no characters(only the skeleton)
	 */
	public Dungeon(GameObject dungeon[][])
	{
		this.dungeon = dungeon;
	}
	
	/**
	 * Puts a given game object in a given position of the dungeon
	 * @param p object position coordinates
	 * @param object object to be set
	 */
	public void setTile(Point p, GameObject object)
	{
		dungeon[p.getY()][p.getX()] = object;
	}
	
	/**
	 * Turns the complete dungeon into a string representation(with the according objects representaion symbol)
	 * @param allObjects list with objects currently present in the game
	 * @return dungeon's string representation
	 */
	public String printDungeonString(ArrayList<GameObject> allObjects)
	{
		
		dungeonInstant = new GameObject[dungeon.length][dungeon[0].length];
		
		for(int i = 0; i<dungeon.length; i++)
		{
			for(int j = 0; j<dungeon[i].length; j++)
			{
				dungeonInstant[i][j] = dungeon[i][j];
			}
		}
		
		String dungeonString = "";
		
		populateDungeon(allObjects);
		
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
	
	private void populateDungeon(ArrayList<GameObject> allObjects) {
		for(int i = 0; i<allObjects.size(); i++)
		{
			GameObject currentObject = allObjects.get(i);
			if(currentObject.getCoord() != null)
			{
				int yCoord = currentObject.getCoord().getY();
				int xCoord = currentObject.getCoord().getX();
				
				
				if(currentObject instanceof Ogre)
				{
					ogrePrint(currentObject, yCoord, xCoord);
					
				}
				else
				{
					dungeonInstant[yCoord][xCoord] = currentObject;
				}
				
				
			}
		}
		
	}

	private void ogrePrint(GameObject currentObject, int yCoord, int xCoord) {
		int yCoordOgre = ((Ogre) currentObject).getCoord().getY();
		int xCoordOgre = ((Ogre) currentObject).getCoord().getX();
		if(dungeonInstant[yCoordOgre][xCoordOgre] instanceof Key)
		{
			dungeonInstant[yCoordOgre][xCoordOgre] = new Collision(currentObject.getCoord());
		}
		else if(!(dungeonInstant[yCoordOgre][xCoordOgre] instanceof Collision))
		{
			dungeonInstant[yCoord][xCoord] = currentObject;
		}
		if(((Ogre) currentObject).getWeapon() != null && ((Ogre) currentObject).getWeapon().getCoord() != null)
		{
			makeCollision(currentObject);
		}
		
	}

	private void makeCollision(GameObject currentObject) {
		int yCoordWeapon = ((Ogre) currentObject).getWeapon().getCoord().getY();
		int xCoordWeapon = ((Ogre) currentObject).getWeapon().getCoord().getX();
		if(dungeonInstant[yCoordWeapon][xCoordWeapon] instanceof Key)
		{
			dungeonInstant[yCoordWeapon][xCoordWeapon] = new Collision(currentObject.getCoord());
		}
		else if(!(dungeonInstant[yCoordWeapon][xCoordWeapon] instanceof Collision))
		{
			dungeonInstant[yCoordWeapon][xCoordWeapon] = ((Ogre) currentObject).getWeapon();
		}
	}

	/**
	 * 
	 * @return returns the complete dungeon
	 */
	public GameObject[][] getDungeonInstant()
	{
		return this.dungeonInstant;
	}
	
	/**
	 * Returns the object located at a given position
	 * @param p position whose object we want to get
	 * @return object located at that position
	 */
	public GameObject getTile(Point p)
	{
		return dungeon[p.getY()][p.getX()];
	}
	
	/**
	 * 
	 * @return returns the skeleton version of the dungeon
	 */
	public GameObject[][] getMap()
	{
		return this.dungeon;
	}
}
