package logic;

import logic.Character.State;

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
public boolean isDead(Dungeon dungeon)
{
	if(dungeon.checkAdjacent(getCoord().getX(),getCoord().getY(),3) ||
	   dungeon.checkAdjacent(getCoord().getX(),getCoord().getY(),10) )//checks if any of the hero enemies are in an adjacent tile
	{currentState = State.DEAD;}
	
	return super.isDead(dungeon);
}
}