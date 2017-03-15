package dkeep.logic;

public class Hero extends Character{
	
	private boolean key;
	
	public Hero(Point coord)
	{
		super(coord,'H',true,true);
		key = false;
	}
	
	public boolean getKey()
	{
		return key;
	}
	
	public Point[] attack()
	{
		return this.getCoord().getAdjacentPos();
	}
	
	public boolean carryKey()
	{
		this.setSymbol('K');
		key = true;
		return true;
	}
	
}