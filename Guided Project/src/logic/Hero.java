package logic;

public class Hero extends Character{
	
	private boolean key;
	
	public Hero(int x, int y, int identifier)
	{
		super(x,y,identifier);
		key = false;
		this.setVulnerable(true);
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
	public boolean leverInteraction()
	{
		return true;
	}
	
	@Override
	public boolean closedDoorInteraction()
	{
		if(key)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canFinishLevel()
	{
		return true;
	}
	
	@Override
	public boolean carryKey()
	{
		key = true;
		return true;
	}
	
	@Override
	public boolean isVulnerable()
	{
		return true;
	}
}