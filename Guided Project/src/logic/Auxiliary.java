package logic;

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
		default:
		{
			return ' ';
		}
		}
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
