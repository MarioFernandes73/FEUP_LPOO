package dkeep.logic;

public class GameObject {

	private char symbol;
	private Point coordinates;
	private boolean passable;
	private boolean movable;
	
	public GameObject(Point coordinates, char symbol, boolean passable, boolean movable)
	{
		this.symbol = symbol;
		this.coordinates = coordinates;
		this.passable = passable;
		this.movable=movable;
	}
	
	public boolean canMove()
	{
		return movable;
	}
	
	public boolean isPassable()
	{
		return passable;
	}
	
	public void setPassable(boolean passable)
	{
		this.passable=passable;
	}
	public void setSymbol(char symbol)
	{
		this.symbol = symbol;
	}
	
	public void move(Point p)
	{
		this.coordinates = p;
	}
	
	public Point getCoord()
	{
		return coordinates;
	}
	
	public boolean equals(GameObject object)
	{
		return (this.getCoord() == object.getCoord() && this.symbol == object.symbol);
	}
	
	@Override
	public String toString()
	{
		return this.symbol+"";
	}
}
