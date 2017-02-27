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

	@Override
	public boolean carryKey()
	{
		key = true;
		return true;
	}
	
}