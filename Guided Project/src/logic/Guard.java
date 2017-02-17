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
	
	@Override
	public String createMovement()
	{
		String movement = defaultMovementList[nextMovement];		
		return movement;
	}
	
	@Override
	public void move(Point p)
	{
		this.getCoord().setLocation(p);
		nextMovement++;
	}
	
	@Override
	public boolean attack(Dungeon dungeon)
	{
		final Point coord = this.getCoord();
		final boolean heroHit = dungeon.checkAdjacent(coord, 2);
		return heroHit;
	}
}
