package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	
	private Club club;
	private int sleepTime;
	
	public Ogre(Point coord)
	{
		super(coord, 'O',true,true);
		this.sleepTime = 0;
	}

	public String createMovement()
	{
		if(sleepTime <= 0)
		{
		String randomMove = "";
		Random generator = new Random();
		int randomMoveIdentifier = generator.nextInt(4)+1;
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
		Point[] areaAttacked = this.getCoord().getAdjacentPos();
		return areaAttacked;
	}
	
	@Override
	public Point[] weaponAttack()
	{
		Point[] areaAttacked = null;
		if(club != null)
		{
			areaAttacked = this.movement(createMovement()).getAdjacentPos();
		}
		return areaAttacked;
	}
	
	public void stunned()
	{
		sleepTime = 2;
		this.setSymbol('8');
	}
	
	public void setWeapon(Club club)
	{
		this.club=club;
	}
	
	public Club getWeapon()
	{
		return this.club;
	}
}
