package dkeep.logic;

public class Point {

	private int x;
	private int y;
	
	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Point)
		{
			Point newPoint = (Point)obj;
			if(this.x == newPoint.getX() && this.y == newPoint.getY())
				return true;
		}
		return false;
	}

	public Point[] getAdjacentPos()
	{
		Point[] adjacentPos = new Point[5];
		final int xCoord = this.getX();
		final int yCoord = this.getY();
		final Point adjacentUp = new Point(xCoord, yCoord-1);
		final Point adjacentDown = new Point(xCoord, yCoord+1);
		final Point adjacentLeft = new Point(xCoord-1, yCoord);
		final Point adjacentRight = new Point(xCoord+1, yCoord);
		adjacentPos[0]=this;
		adjacentPos[1]=adjacentUp;
		adjacentPos[2]=adjacentDown;
		adjacentPos[3]=adjacentLeft;
		adjacentPos[4]=adjacentRight;

		return adjacentPos;
	}
	
	
}
