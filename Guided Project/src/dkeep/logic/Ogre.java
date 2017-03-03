package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	
	private boolean club;
	private int sleepTime;
	
	public Ogre(Point coord, int identifier)
	{
		super(coord, identifier,true,true);
		this.club = true;
		this.sleepTime = 0;
	}

	public String createMovement()
	{
		if(sleepTime <= 0)
		{
		String randomMove = "";
		Random generator = new Random();
		int randomMoveIdentifier = generator.nextInt(3)+1;
		if(randomMoveIdentifier == 1)
		{
			randomMove = "w";
		}
		else if(randomMoveIdentifier == 2)
		{
			randomMove = "a";
		}
		else if(randomMoveIdentifier == 3)
		{
			randomMove = "s";
		}
		else if(randomMoveIdentifier == 4)
		{
			randomMove = "d";
		}
		
		return randomMove;
		}
		else
		{
			sleepTime--;
			if(sleepTime <= 0)
			{
				this.setSymbol('O');
			}
			return null;
		}
			
	}
	
	@Override
	public Point[] attack()
	{
		Point[] areaAttacked = Auxiliary.getAdjacentPos(this.getCoord());
		return areaAttacked;
	}
	
	@Override
	public Point[] weaponAttack()
	{
		Point[] areaAttacked = null;
		if(club)
		{
			final String randomPosition = createMovement();
			final Point clubPosition = this.movement(randomPosition);
			areaAttacked = Auxiliary.getAdjacentPos(clubPosition);
		}
		return areaAttacked;
	}
	
	public void stunned()
	{
		sleepTime = 2;
		this.setSymbol('8');
	}
}
