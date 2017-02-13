package logic;

import java.util.Arrays;

public class Guard extends GameObjects {
	
	private char[] movementList;
	
	static char[] defaultMovementList = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d',};
	
	public Guard(int x, int y, char symbol) 
	{
		super(x, y, symbol);
	}

	public char[] getMovementList() 
	{
		return movementList;
	}

	public nextMovement()
	{
		movement()
	}

	
	
}
