package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * represents a position coordinates
 *
 */
public class Point  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * x coordinate of the position
	 */
	private int x;
	
	/**
	 * y coordinate of the position
	 */
	private int y;
	
	/**
	 * Point constructor
	 * @param x x coordinate
	 * @param y x coordinate
	 */
	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	/**
	 * 
	 * @return returns x coordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 
	 * @return returns y coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * 2 Points are equal if they have the same x and y coordinates
	 */
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

	/**
	 * 
	 * @return returns a list with the coordinates of the points on top,bottom,left,right and the this point
	 */
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
