package logic;

import java.util.Random;

public class Guard extends Character {

	public enum Personality {ROOKIE, DRUNKEN, SUSPICIOUS};

	private int sleepTimeLeft = -1; //it's only manipulated in the DRUNKEN guard(time is measured in plays), NOTE: -1 means at least one play has gone by with the guard awake. 0 means that the guard was asleeping in the last play
	private static int TIME_PER_SLEEP = 4;

	private Personality personality; 
	private String[] movementList;
	private int nextMovement;
	private int movementDirection = 1;//1 means it will move in the standard direction, -1 it will move in hte opposite direction
	static String[] defaultMovementList = {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d","d","d","w","w","w","w","w"};

	public Guard(int x, int y, int identifier, Personality persona) 
	{
		super(x, y, identifier);
		movementList = defaultMovementList;
		nextMovement = 0;
		personality = persona;
	}

	@Override
	public String createMovement()
	{
		String movement = defaultMovementList[nextMovement];
		switch(personality)
		{
		case ROOKIE:
			//nothing changes
			break;

		case DRUNKEN:
		{
			if(sleepTimeLeft == -1) //if the value is -1, it means it is awaken for more than one play
			{
				Random sleepGenerator = new Random();
				sleepTimeLeft = (sleepGenerator.nextInt(100)+1<=10)? TIME_PER_SLEEP : -1; //there's 10% probability of putting the guard asleep
			}
			else if(sleepTimeLeft == 0) //if the value is 0, it means this will be the first awaken move after the sleep, so the movement direction might change
			{
				Random directionChangeGenerator = new Random();
				movementDirection = (directionChangeGenerator.nextInt(100)+1<=50) ? -1 : 1; //there's 50% probability of changing the guard direction after he wakes up
				sleepTimeLeft -= 1;
			}
			else //the guard is asleep
			{
				sleepTimeLeft -= 1;
			}
		}
		break;
		case SUSPICIOUS:
			break;
		}
		return movement;
	}

	@Override
	public void move(Point p)
	{
		this.getCoord().setLocation(p);
		nextMovement++;
		if(nextMovement >= movementList.length)
		{
			nextMovement=0;
		}
	}

	@Override
	public Point[] attack()
	{
		Point[] areaAttacked = Auxiliary.getAdjacentPos(this.getCoord());
		return areaAttacked;
	}
}
