package logic;

import java.util.Random;

public class Ogre extends Character {
	
	public Ogre(int x, int y, int identifier)
	{
		super(x,y,identifier);
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
}
