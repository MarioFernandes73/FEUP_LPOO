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
	
	public boolean movement(String movement, Dungeon dungeon)
	{
		int nextX = 0, nextY = 0;
		switch(movement)
		{
			case "w":
			{
				nextY = this.getCoord().getY() - 1;
				nextX = this.getCoord().getX();	
				break;
			}
			case "a":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() -1;
				break;
			}
			case "s":
			{
				nextY = this.getCoord().getY() + 1;
				nextX = this.getCoord().getX();
				break;
			}
			case "d":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() + 1;
				break;
			}
		}
		final boolean validMove = auxMovement(nextX, nextY, dungeon);
		
		if (validMove)
		{
			coordinates.setLocation(nextX, nextY);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// returns possible if the current movement is a valid one or false if it's invalid
	public boolean auxMovement(int nextX,int nextY, Dungeon dungeon)
	{
		final char nextTile = dungeon.getDungeon()[nextY][nextX];
		if(nextTile == ' ')
		{
			return true;
		}
		else if(nextTile == 'X' || nextTile == 'I')
		{
			return false;
		}
		
		return true;
	}

}

