package dkeep.logic;

import dkeep.logic.Guard.Personality;

public abstract class Auxiliary {

	public static char identifierSwitch(int identifier)
	{
		switch(identifier)
		{
		case 0:
		{
			return ' ';
		}
		case 1:
		{
			return 'X';
		}
		case 2:
		{
			return 'H';
		}
		case 3:
		{
			return 'G';
		}
		case 4:
		{
			return 'I';
		}
		case 5:
		{
			return 'S';
		}
		case 6:
		{
			return 'I';
		}
		case 7:
		{
			return 'S';
		}
		case 8:
		{
			return 'k';
		}
		case 9:
		{
			return 'k';
		}
		case 10:
		{
			return 'O';
		}
		case 11:
		{
			return '*';
		}
		case 12:
		{
			return '$';
		}
		default:
		{
			return ' ';
		}
		}
	}

	public static GameObject getNewEntity(Point coord, int identifier, Personality personality)
	{
		switch(identifier)
		{
		case 3:
		{
			return new Guard(coord, identifier, personality);
		}
		default:
		{
			return getNewEntity(coord, identifier);
		}
		}
	}
	
	public static GameObject getNewEntity(Point coord, int identifier)
	{
		
		switch(identifier)
		{
		case 0:
		{
			return new Empty(identifier);
		}
		case 1:
		{
			return new Wall(identifier);
		}
		case 2:
		{
			return new Hero(coord, identifier);
		}
		case 4:
		case 5:
		case 6:
		case 7:
		{
			return new Door(coord, identifier);
		}
		case 8:
		{
			return new Lever(coord, identifier);
		}
		case 9:
		{
			return new Key(coord, identifier);
		}
		case 10:
		{
			return new Ogre(coord, identifier);
		}
		case 11:
		{
			return new Club(coord, identifier);
		}
		}
		return null;
	}
	
	public static String reverseMovement(String movement)
	{
		String finalMovement = null;
		
		if(movement == "w")
		{
			finalMovement = "s";
		}
		else if(movement == "s")
		{
			finalMovement = "w";
		}
		else if(movement == "a")
		{
			finalMovement = "d";
		}
		else if(movement == "d")
		{
			finalMovement = "a";
		}
		
		return finalMovement;
	}
}