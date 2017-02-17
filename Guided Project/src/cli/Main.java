package cli;



import logic.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		int currentLevel = 1;
		final int maximumLevels = 2;
		Game game = new Game(currentLevel);
		
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		
		while(running)
		{
			System.out.println(game.printDungeonString());
			System.out.print("Choose the direction to move your hero (w a s d): ");
			String heroMovement = sc.nextLine();
			System.out.println("");
			if(heroMovement.equals("w") || heroMovement.equals("a") || heroMovement.equals("s") || heroMovement.equals("d") )
			{
				running = game.playerTurn(heroMovement);
				if(game.getHero().isDead())
				{
					break;
				}
				
				if(running == false)		// level has ended
				{
					final boolean heroIsDead = game.getHero().isDead();
					if((currentLevel == maximumLevels) || heroIsDead)
					{
						break;		
					}
					else
					{
						running = true;
						currentLevel ++;
						game = new Game(currentLevel);						
					}
				}
			}
		}
		
		System.out.println("GAME OVER!");
		
	}
	
	
}
