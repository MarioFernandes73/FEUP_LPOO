package logic;

import java.util.Random;

public class Ogre extends Character {
	
	private boolean club;
	
	public Ogre(int x, int y, int identifier)
	{
		super(x,y,identifier);
		this.club = true;
	}

	public String createMovement()
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
	
	public void checkClubPos(Point p, Dungeon dungeon)
	{
		final int nextX = p.getX();
		final int nextY = p.getY();
		final int nextTile = dungeon.getDungeon()[nextY][nextX];
		int nextTileIdentifier = 0;
		//maquina de estados..
	}
}
