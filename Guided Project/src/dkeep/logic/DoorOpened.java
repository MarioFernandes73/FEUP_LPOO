package dkeep.logic;

/**
 * 
 * Represents an open door, extends Door
 * The closed door representation symbol is 'S'
 * An open door is not movable, yet it's passable
 */
public class DoorOpened extends Door {

	/**
	 * DoorOpened constructor, calls Door constructor
	 * @param coord open door coordinates
	 * @param doorType open door state
	 */
	public DoorOpened(Point coord, State doorType)
	{
		super(coord, 'S', doorType);
		this.setPassable(true);
	}
	
	/**
	 * The open door changes its instance to a closed door
	 * @return new closed door
	 */
	@Override
	public DoorClosed changeOpenedState()
	{
		return new DoorClosed(this.getCoord(), this.getDoorType());
	}
}
