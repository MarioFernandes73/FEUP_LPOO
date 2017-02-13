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
		switch(movement)
		{
		case "w":
		{
			if(this.getCoordinates().getY() - 1 >= 0)
				
				
				
		}
		case 'a':
		{
			
		}
		case 's':
		{
			
		}
		case 'd':
		{
			
		}
		}
		return levelRunning;
	}
	
	public State getState()
	{
		return currentState;
	}
}
