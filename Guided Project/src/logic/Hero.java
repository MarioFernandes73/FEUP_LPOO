package logic;

public class Hero extends GameObjects{
	
	public enum State {ALIVE, DEAD};
	private State currentState;
	
	public Hero(int x, int y, char symbol)
	{
		super(x,y,symbol);
		currentState = State.ALIVE;
	}

	public boolean movement(String movement, char[][]dungeon)
	{
		boolean levelRunning = true;
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
		auxMovement(nextX, nextY, dungeon);
		return levelRunning;
	}
	
	public State getState()
	{
		return currentState;
	}
	
	public boolean auxMovement(int x, int y,char[][] dungeon)
	{
		char currentTile = dungeon[y][x];
		if(currentTile == 'X' || currentTile == 'G' || currentTile == 'O')
			return false;
		return true;
	}
}
