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
}
