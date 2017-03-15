package dkeep.logic;

public class DoorOpened extends Door {

	public DoorOpened(Point coord, State doorType)
	{
		super(coord, 'S', doorType);
		this.setPassable(true);
	}
	
	@Override
	public DoorClosed changeOpenedState()
	{
		return new DoorClosed(this.getCoord(), this.getDoorType());
	}
}
