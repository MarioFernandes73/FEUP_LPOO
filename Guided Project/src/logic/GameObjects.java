package logic;

public class GameObjects {

	private Point coordinates;
	private int identifier;
	private char symbol;
	private boolean visible;
	
	public GameObjects(int x, int y, int identifier)
	{
		this.coordinates = new Point(x,y);
		this.identifier = identifier;
		this.symbol = Auxiliary.identifierSwitch(identifier);
		visible = true;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public Point getCoord()
	{
		return coordinates;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
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
		this.coordinates.setLocation(p);
	}
}

