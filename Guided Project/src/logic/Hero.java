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
	
	public State getState()
	{
		return currentState;
	}
	
	
	@Override
	public boolean auxMovement(int nextX, int nextY, Dungeon dungeon) //returns true if the hero 
	{
		char nextTile = dungeon.getDungeon()[nextY][nextX];
		
		if(nextTile == 'X' || nextTile == 'I' || nextTile == ' ')
		{
			return super.auxMovement(nextX, nextY, dungeon);
		}
		
		return true;
	}
}
