package dkeep.logic;

public class Door extends GameObject{
	
	public Door(Point coord, int identifier)
	{
		super(coord, identifier,false,false);
	}
	
	public void changeOpenedState()
	{
		this.setPassable(true);
		if(this.getIdentifier() == 4)
		{
			this.setIdentifier(5);	
		}
		else if(this.getIdentifier() == 6)
		{
			this.setIdentifier(7);
		}
		this.setSymbol('S');
	}
}
