package logic;

public class GameObjects {

	private Point coordinates;
	private char symbol;
	
	public GameObjects(int x, int y, char symbol)
	{
		this.coordinates = new Point(x,y);
		this.symbol=symbol;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public Point getCoord()
	{
		return coordinates;
	}
}
