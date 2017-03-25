package dkeep.logic;

/**
 * 
 * Represents a closed door, extends Door
 * The closed door representation symbol is 'I'
 * A closed door is neither passable, nor movable
 */
public class DoorClosed extends Door {
	
	/**
	 * DoorClosed constructor, calls Door constructor and marks itself as not passable
	 * @param coord closed door coordinates
	 * @param doorType closed door type
	 */
	public DoorClosed(Point coord, State doorType)
	{
		super(coord, 'I', doorType);
		this.setPassable(false);
	}

	/**
	 * The closed door changes its instance to an open door
	 * @return new open door
	 */
	@Override
	public DoorOpened changeOpenedState()
	{
		return new DoorOpened(this.getCoord(), this.getDoorType());
	}
}
