package dkeep.logic;

public class Hero extends Character{
	
	private boolean key;
	
	public Hero(Point coord, int identifier)
	{
		super(coord,identifier,true,true);
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

	public Point[] attack()
	{
		return Auxiliary.getAdjacentPos(this.getCoord());
	}
	
	@Override
	public boolean carryKey()
	{
		if(this.getState() == State.ARMED)
		{
			this.setSymbol('K');
		}
		key = true;
		return true;
	}
	
}