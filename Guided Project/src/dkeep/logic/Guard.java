package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * represents a guard, extends Character
 * The guard representation symbol is 'G', when stunned(drunken personality) is 'g'
 * A guard is both passable and movable
 */
public class Guard extends Character implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Expresses the guard personaly
	 * ROOKIE will take a default movement path
	 * DRUNKEN will randomly be stunned with possibilty of inverting the default movement path
	 * SUSPICIOUS will randomly take 1 step back while taking the default movemnt path
	 *
	 */
	public enum Personality {ROOKIE, DRUNKEN, SUSPICIOUS};

	/**
	 * Measured in rounds/plays
	 * Drunken guard: measures the time left until the stun effect ends
	 * Suspicious guard: measures when how many time has gone by since the last turn around(takes negative value)
	 */
	private int sleepTime; //it's only manipulated in the DRUNKEN guard (time is measured in plays)

	/**
	 * guard personality
	 */
	private Personality personality;

	/**
	 * guard movement path
	 */
	private String[] movementList;

	/**
	 * index of movement list
	 * tells what the next move will be
	 */
	private int nextMovement;

	/**
	 * set to 1 if movement is accordingly to the movement list
	 * set to -1 if movement is in opposite order of the movement list
	 */
	private int movingDirection;//1 means it will move in the standard direction, -1 it will move in the opposite direction

	/**
	 * default movement list
	 */
	static String[] defaultMovementList = {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d","d","d","w","w","w","w","w"};

	/**
	 * Guard constructor, calls Character constructor
	 * @param coord guard position coordinates
	 * @param personality guard personality
	 */
	public Guard(Point coord, Personality personality) 
	{
		super(coord, 'G',true,true);
		this.personality = personality;
		movementList = defaultMovementList;
		nextMovement = 0;
		sleepTime = 0;	
		movingDirection = 1;
	}

	/**
	 * generates the next guard movement
	 * @return string with movement representation
	 */
	@Override
	public String createMovement()
	{
		String movement = null;

		switch(personality)
		{
		case ROOKIE:
			movement = rookieMovement();
			break;
		case DRUNKEN:
			movement = drunkenMovement();
			break;
		case SUSPICIOUS:
			movement = suspiciousMovement();
			break;
		default:

			break;
		}		
		return movement;		
	}

	
	
	public String rookieMovement()
	{
		String movement = defaultMovementList[nextMovement];
		nextMovement++;
		if(nextMovement >= movementList.length)
		{
			nextMovement=0;
		}
		return movement;
	}
	
	public String drunkenMovement()
	{
		String movement = null;
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
			sleepchance();
			
		}
		else
		{
			sleepTime--;
			if(sleepTime == 0)
			{
				this.setSymbol('G');
			}
		}
		return movement;
	}
	
	private void sleepchance() {
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

	public String suspiciousMovement()
	{
		String movement = null;
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
		//chance of turning back
		turningChance();
		
		return movement;
	}
	
	private void turningChance() {
		Random directionGenerator = new Random();
		if(movingDirection == 1)
		{
			int directionRandomIdentifier = directionGenerator.nextInt(Math.abs(sleepTime)+1);
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
	}

	/**
	 * Causes the guard to attack
	 * @return a list with all position coordinates affected by the attack
	 */
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
