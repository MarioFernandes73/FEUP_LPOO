package dkeep.logic;

import java.util.Random;

public class Guard extends Character {

	public enum Personality {ROOKIE, DRUNKEN, SUSPICIOUS};

	private int sleepTime; //it's only manipulated in the DRUNKEN guard (time is measured in plays)
	private Personality personality; 
	private String[] movementList;
	private int nextMovement;
	private int movingDirection;//1 means it will move in the standard direction, -1 it will move in the opposite direction
	static String[] defaultMovementList = {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d","d","d","w","w","w","w","w"};
	
	public Guard(Point coord, Personality personality) 
{
		super(coord, 'G',true,true);
		this.personality = personality;
		movementList = defaultMovementList;
		nextMovement = 0;
		sleepTime = 0;	
		movingDirection = 1;
	}

	@Override
	public String createMovement()
	{
		String movement = null;
		
		switch(personality)
		{
		case ROOKIE:
			movement = defaultMovementList[nextMovement];
			nextMovement++;
			if(nextMovement >= movementList.length)
			{
				nextMovement=0;
			}
			break;
		case DRUNKEN:
			if(sleepTime <= 0)
			{
				if(movingDirection == 1)
				{
					movement = defaultMovementList[nextMovement];
					nextMovement++;
					if(nextMovement >= movementList.length)
					{
						nextMovement=0;
					}
				}
				else if(movingDirection == -1)
				{
					nextMovement--;
					if(nextMovement < 0)
					{
						nextMovement = movementList.length-1;
					}
					movement = Auxiliary.reverseMovement(defaultMovementList[nextMovement]);
				}
				//chance to sleep
				Random sleepGenerator = new Random();
				int randomSleepIdentifier = sleepGenerator.nextInt(10)+1;	// range 1-10
				if(randomSleepIdentifier == 6 || randomSleepIdentifier == 7)	//20% chance sleep 1 turn
				{
					sleepTime = 1;
				}
				else if(randomSleepIdentifier == 8 || randomSleepIdentifier == 9)	// 20% chance to sleep 2 turns
				{
					sleepTime = 2;
				}
				else if(randomSleepIdentifier == 10)	// 10% to sleep 3 turns
				{
					sleepTime = 3;
				}
				if(sleepTime > 0)		//guard will be asleep for the next turn
				{
					randomSleepIdentifier = sleepGenerator.nextInt(2)+1;	//range 1-2
					if(randomSleepIdentifier == 1)		// 50% chance to move back
					{
						this.movingDirection = 1;
					}
					else if(randomSleepIdentifier == 2)		// 50% chance to move forward
					{
						this.movingDirection = -1;
					}
					this.setSymbol('g');
				}
			}
			else
			{
				sleepTime--;
				if(sleepTime == 0)
				{
					this.setSymbol('G');
				}
			}
			break;
		case SUSPICIOUS:
			if(movingDirection == 1)
			{
				movement = defaultMovementList[nextMovement];
				nextMovement++;
				if(nextMovement >= movementList.length)
				{
					nextMovement=0;
				}
			}
			else if(movingDirection == -1)
			{
				nextMovement--;
				if(nextMovement < 0)
				{
					nextMovement = movementList.length-1;
				}
				movement = Auxiliary.reverseMovement(defaultMovementList[nextMovement]);
			}
			Random directionGenerator = new Random();
			if(movingDirection == 1)//chance of turning back
			{
				int directionRandomIdentifier = directionGenerator.nextInt(Math.abs(sleepTime)+1);	//
				System.out.println(directionRandomIdentifier);
				if(directionRandomIdentifier > 1)	// 100% to keep going to the front on the first try, 75% at the second, 50% at the third...
				{
					movingDirection = -1;
				}
				else
				{
					sleepTime--;
				}
			}
			else if(movingDirection == -1)
			{
				if(directionGenerator.nextInt(10)+1 > 1) // 90% to turn to the front;
				{
					movingDirection = 1;
					sleepTime = 0;
				}
			}
			break;
		default:
			
			break;
		}		
		return movement;		
	}
	
	@Override
	public Point[] attack()
	{
		Point[] areaAttacked = null;
		if(this.sleepTime <= 0)
		{
			areaAttacked = this.getCoord().getAdjacentPos();
		}
		return areaAttacked;
	}
}
