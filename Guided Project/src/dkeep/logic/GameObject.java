package dkeep.logic;

public class GameObject {

	private char symbol;
	private int identifier;
	private Point coordinates;
	private boolean passable;
	private boolean movable;
	
	public GameObject(Point coordinates, int identifier, boolean passable, boolean movable)
	{
		this.identifier = identifier;
		this.symbol = Auxiliary.identifierSwitch(identifier);
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
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public int getIdentifier()
	{
		return this.identifier;
	}
	
	public void setIdentifier(int identifier)
	{
		this.identifier = identifier;
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
		if(this.getCoord() == object.getCoord() && this.getIdentifier() == object.getIdentifier())
		{
			return true;
		}
		return false;
	}
	
}
