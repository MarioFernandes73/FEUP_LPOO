package logic;

public class Hero extends GameObjects{
	
	public enum State {ALIVE, DEAD};
	private State currentState;
	
	public Hero(int x, int y, char symbol)
	{
		super(x,y,symbol);
		currentState = State.ALIVE;
	}

	public boolean movement(String movement)
	{
		boolean levelRunning = true;
		switch(movement)
		{
		case "w":
		{
			
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
