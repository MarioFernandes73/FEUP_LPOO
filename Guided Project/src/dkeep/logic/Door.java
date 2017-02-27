package dkeep.logic;

public class Door extends GameObject{

	private boolean endingDoor;
	
	public Door(Point coord, int identifier)
	{
		super(coord, identifier,false,false);
		if(identifier == 4 || identifier == 5)
		{
			this.endingDoor = false;
		}
		else if(identifier == 6 || identifier == 7)
		{
			this.endingDoor = true;
		}
	}
	
	public boolean getEndingDoor()
	{
		return endingDoor;
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
