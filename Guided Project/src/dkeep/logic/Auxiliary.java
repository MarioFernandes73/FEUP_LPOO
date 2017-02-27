package dkeep.logic;

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
		case 3:
		{
			return new Guard(coord, identifier);
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
	
	public static Point[] getAdjacentPos(Point p)
	{
		Point[] adjacentPos = new Point[5];
		final int xCoord = p.getX();
		final int yCoord = p.getY();
		final Point adjacentUp = new Point(xCoord, yCoord-1);
		final Point adjacentDown = new Point(xCoord, yCoord+1);
		final Point adjacentLeft = new Point(xCoord-1, yCoord);
		final Point adjacentRight = new Point(xCoord+1, yCoord);
		adjacentPos[0]=p;
		adjacentPos[1]=adjacentUp;
		adjacentPos[2]=adjacentDown;
		adjacentPos[3]=adjacentLeft;
		adjacentPos[4]=adjacentRight;

		return adjacentPos;
	}
}
