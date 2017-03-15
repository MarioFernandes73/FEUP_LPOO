package dkeep.logic;

public class DoorClosed extends Door {
	
	public DoorClosed(Point coord, State doorType)
	{
		super(coord, 'I', doorType);
		this.setPassable(false);
	}

	@Override
	public DoorOpened changeOpenedState()
	{
		return new DoorOpened(this.getCoord(), this.getDoorType());
	}
}
