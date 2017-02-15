package logic;

public class Door extends GameObjects{

	private boolean endingDoor;
	
	public Door(int x, int y, int identifier)
	{
		super(x,y,identifier);
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
		if(this.getIdentifier() == 4)
		{
			this.setIdentifier(5);
		}
	}
}
