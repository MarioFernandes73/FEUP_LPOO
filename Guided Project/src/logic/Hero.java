package logic;

public class Hero extends GameObjects{
	
	public enum State {ALIVE, DEAD};
	private State currentState;
	private boolean key;
	
	public Hero(int x, int y, char symbol)
	{
		super(x,y,symbol);
		currentState = State.ALIVE;
	}

	public boolean movement(String movement, Dungeon dungeon)
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
	
	public boolean auxMovement(int x, int y, Dungeon dungeon) //returns true if the hero 
	{
		char currentTile = dungeon.getDungeon()[y][x];
		
		if(x < 0 || y< 0 || x>dungeon.getWidth() || y>dungeon.getHeight()) //if the hero gets out of coordinates, we don't want to update its movement
			return false;
		
		if(currentTile == 'X' || currentTile == 'G' || currentTile == 'O')
			return false;
		return true;
	}
}
