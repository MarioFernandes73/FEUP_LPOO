package logic;

import java.util.Random;

public class Character extends GameObjects {

	public enum State {ALIVE, HASKEY, DEAD};
	protected State currentState;
	
	public Character(int x, int y, int identifier)
	{
		super(x,y,identifier);
		currentState = State.ALIVE;
	}
	
	public State getState()
	{
		return currentState;
	}
	
	public boolean isDead(Dungeon dungeon)
	{
		if (currentState == State.DEAD)
		{
			return true;
		}
		return false;
	}
	
	// constructs a random movement and makes it
	public String createMovement()
	{
		return "";
	}
	
	public int movement(String movement, Dungeon dungeon)
	{
		int nextX = 0, nextY = 0;
		switch(movement)
		{
			case "w":
			{
				nextY = this.getCoord().getY() - 1;
				nextX = this.getCoord().getX();	
				break;
			}
			case "a":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() -1;
				break;
			}
			case "s":
			{
				nextY = this.getCoord().getY() + 1;
				nextX = this.getCoord().getX();
				break;
			}
			case "d":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() + 1;
				break;
			}
		}
		
		if(nextY < 0 || nextX < 0 || nextY >=dungeon.getWidth() || nextX >= dungeon.getHeight())
		{
			return 0;
		}
		final int validMove = auxMovement(nextX, nextY, dungeon);//by default its 1, which means it is a valid movement without any consequences in the game state
		
		if (validMove != 0)
		{
			super.getCoord().setLocation(nextX, nextY);
		}
			return validMove;
	}
	
	// returns possible if the current movement is a valid one or false if it's invalid
	public int auxMovement(int nextX,int nextY, Dungeon dungeon)
	{
		final int nextTile = dungeon.getDungeon()[nextY][nextX];
		if(nextTile == 0)	//empty tile
		{
			return 1;
		}
		//blocked tile
		else if(nextTile == 1 || nextTile == 4 || nextTile == 6 || (nextX < 0 || nextY < 0 || nextX >= dungeon.getWidth() || nextY >= dungeon.getHeight()))
		{
			return 0;
		}
		//exit tile
		else if (nextTile == 7)
		{
			return 2;
		}
		//lever tile
		else if(nextTile == 8)
		{
			return 3;
		}
		//key tile
		else if(nextTile == 9)
		{
			return 4;
		}
		
		return 1;
	}
}
