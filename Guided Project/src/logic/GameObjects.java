package logic;

import java.awt.Point;

public class GameObjects {

	private Point coordinates;
	private char symbol;
	
	public GameObjects(int x, int y, char symbol)
	{
		this.coordinates = new Point(x,y);
		this.symbol=symbol;
	}
}
