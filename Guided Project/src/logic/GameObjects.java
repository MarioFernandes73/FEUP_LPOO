package logic;

public class GameObjects {

	private Point coordinates;
	private char symbol;
	private boolean visible;
	
	public GameObjects(int x, int y, char symbol)
	{
		this.coordinates = new Point(x,y);
		this.symbol=symbol;
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
}

