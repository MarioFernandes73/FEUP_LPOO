package dkeep.logic;

import java.util.Random;

/**
 * 
 * Represents an ogre, extends Character
 * The ogre representaion symbol is 'O', if it is stunned changes to '8'
 * An ogre is both passable and movable
 *
 */
public class Ogre extends Character {
	
	/**
	 * Represents the ogre weapon
	 */
	private Club club;
	
	/**
	 * Represents the time left until the stun effect ends
	 */
	private int sleepTime;
	
	/**
	 * Ogre constructor, calls Character constructor
	 * @param coord ogre position coordinates
	 */
	public Ogre(Point coord)
	{
		super(coord, 'O',true,true);
		this.sleepTime = 0;
	}

	/**
	 * generates the next ogre movement
	 * @return string with movement representation
	 */
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
	
	/**
	 * @return returns a list with position coordinates affected by the ogre attack
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
	
	/**
	 * @return returns a list with position coordinates affected by the ogre's club attack
	 */
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
	
	/**
	 * Stuns the ogre, changing its representation to 8 and giving him 2 sleep time
	 */
	public void stunned()
	{
		sleepTime = 2;
		this.setSymbol('8');
	}
	
	/**
	 * sets a given weapon to the ogre
	 * @param club weapon given ot the ogre
	 */
	public void setWeapon(Club club)
	{
		this.club=club;
	}
	
	/**
	 * @return ogre's weapon
	 */
	public Club getWeapon()
	{
		return this.club;
	}
}
