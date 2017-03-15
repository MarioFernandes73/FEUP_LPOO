package dkeep.logic;

import dkeep.logic.Door.State;
import dkeep.logic.Guard.Personality;

public abstract class Auxiliary {

	

	public static GameObject getNewEntity(Point coord, int identifier, Personality personality)
	{
		switch(identifier)
		{
		case 3:
		{
			return new Guard(coord, personality);
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
		case 2:
		{
			return new Hero(coord);
		}
		case 4:
		{
			return new DoorClosed(coord, State.InDoor);
		}
		case 5:
		{
			return new DoorOpened(coord, State.InDoor);
		}
		case 6:
		{
			return new DoorClosed(coord, State.OutDoor);
		}
		case 7:
		{
			return new DoorOpened(coord, State.OutDoor);
		}
		case 8:
		{
			return new Lever(coord);
		}
		case 9:
		{
			return new Key(coord);
		}
		case 10:
		{
			return new Ogre(coord);
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
