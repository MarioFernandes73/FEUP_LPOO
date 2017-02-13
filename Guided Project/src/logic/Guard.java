package logic;

public class Guard extends Character {
	
	private String[] movementList;
	private int nextMovement;
	
	static String[] defaultMovementList = {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d","d","d",};
	
	public Guard(int x, int y, char symbol) 
	{
		super(x, y, symbol);
		movementList = defaultMovementList;
		nextMovement = 0;
	}	
}
