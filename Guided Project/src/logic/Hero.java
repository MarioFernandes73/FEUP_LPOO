package logic;

public class Hero extends Character{
	
	private boolean key;
	
	public Hero(int x, int y, char symbol)
	{
		super(x,y,symbol);
		key = false;
	}
	
	public boolean getKey()
	{
		return key;
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
