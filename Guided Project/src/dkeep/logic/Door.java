package dkeep.logic;

public class Door extends GameObject{
	
	public enum State {OutDoor, InDoor}
	private State doorType;
	
	public Door(Point coord, char symbol, State doorType)
	{
		super(coord, symbol, false, false);
		this.setDoorType(doorType);
		
	}
	
	public Door changeOpenedState()
	{
		return null;
	}

	public State getDoorType() {
		return doorType;
	}

	public void setDoorType(State doorType) {
		this.doorType = doorType;
	}
	
	public boolean isEndingDoor()
	{
		return (this.doorType == State.OutDoor);
	}
}
