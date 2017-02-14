package logic;

public class Door extends GameObjects{

	private boolean endingDoor;
	
	public Door(int x, int y, char symbol, boolean endingDoor)
	{
		super(x,y,symbol);
		this.endingDoor = endingDoor;
	}
	
	public boolean getEndingDoor()
	{
		return endingDoor;
	}
}
