package dkeep.logic;

/**
 * 
 * Represents a door, extends GameObject
 *
 */
public class Door extends GameObject{
	
	/**
	 * 
	 * Used to express a door's state in game.
	 * inDoor means it allows the current level to finish when open
	 * outDoor means it won't allow the current level to finish when open
	 *
	 */
	public enum State {OutDoor, InDoor}
	
	/**
	 * Represents the door's state
	 */
	private State doorType;
	
	/**
	 * Door constructor, calls GameObject constructor
	 * 
	 * @param coord door coordiantes
	 * @param symbol door symbol
	 * @param doorType door state
	 */
	public Door(Point coord, char symbol, State doorType)
	{
		super(coord, symbol, false, false);
		this.setDoorType(doorType);
		
	}
	
	/**
	 * This function only exists for further override on derived classes
	 * 
	 * @return
	 */
	public Door changeOpenedState()
	{
		return null;
	}

	/**
	 * 
	 * @return returns door state
	 */
	public State getDoorType() {
		return doorType;
	}

	/**
	 * Changes door state
	 * 
	 * @param doorType new door state
	 */
	public void setDoorType(State doorType) {
		this.doorType = doorType;
	}
	
	/**
	 * Lets us know if the door state
	 * @return door state
	 */
	public boolean isEndingDoor()
	{
		return (this.doorType == State.OutDoor);
	}
}
