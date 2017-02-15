package logic;

public class Hero extends Character{
	
	private boolean key;
	
	public Hero(int x, int y, int identifier)
	{
		super(x,y,identifier);
		key = false;
	}
	
	public boolean getKey()
	{
		return key;
	}
	
	public void setKey(boolean key)
	{
		this.key = key;
	}
	
	@Override
	public int auxMovement(int nextX, int nextY, Dungeon dungeon) //returns true if the hero 
	{
		final int nextTile = dungeon.getDungeon()[nextY][nextX];
		
		if(nextTile == 0 || nextTile == 1)	//moves to a generic tile (wall, free space)
		{
			return super.auxMovement(nextX, nextY, dungeon);
		}	
		else if(nextTile == 7 ) // moves to an opened exit door
		{
			return 2;
		}
		else if(nextTile == 8)	//moves to a lever
		{
			return 3;
		}
		else if(nextTile == 9)	// moves to a key
		{
			return 4;
		}
		return 1;
	}
}
