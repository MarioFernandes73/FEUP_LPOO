package dkeep.logic;

public class Point {

	private int x;
	private int y;
	
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
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
	
	public Point getLocation()
	{
		return new Point(x,y);
	}
	
	public void setLocation(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void setLocation(Point p)
	{
		this.x=p.getX();
		this.y=p.getY();
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
	
}
