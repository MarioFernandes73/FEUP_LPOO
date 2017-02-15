package logic;

public class Guard extends Character {
	
	private String[] movementList;
	private int nextMovement;
	
	static String[] defaultMovementList = {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d","d","d","w","w","w","w","w"};
	
	public Guard(int x, int y, int identifier) 
	{
		super(x, y, identifier);
		movementList = defaultMovementList;
		nextMovement = 0;
	}
	public String nextMovement()
	{
		String movement = defaultMovementList[nextMovement];
		
		//prepares the movement for the next update
		if(nextMovement+1 == defaultMovementList.length)
			nextMovement = 0;
		else 
			nextMovement++;
		
		return movement;
	}
}
