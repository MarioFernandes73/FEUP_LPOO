package dkeep.cli;

import dkeep.logic.*;

import java.util.Scanner;

import dkeep.logic.Game;
import dkeep.logic.Guard.Personality;

public class Main {

	
	//Default Dungeon (used for early tests)
	 static int[][] defaultDungeon1 = 
		{{1,1,1,1,1,1,1,1,1,1},
		 {1,2,0,0,4,0,1,0,3,1},
		 {1,1,1,0,1,1,1,0,0,1},
		 {1,0,4,0,4,0,1,0,0,1},
		 {1,1,1,0,1,1,1,0,0,1},
		 {6,0,0,0,0,0,0,0,0,1},
		 {6,0,0,0,0,0,0,0,0,1},
		 {1,1,1,0,1,1,1,1,0,1},
		 {1,0,4,0,4,0,1,8,0,1},
		 {1,1,1,1,1,1,1,1,1,1}};
	
	static int[][] defaultDungeon2 = 
		{{1,1,1,1,1,1,1,1,1,1},
		 {6,0,0,0,10,0,0,0,9,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,0,0,0,0,0,0,0,0,1},
		 {1,2,0,0,0,0,0,0,0,1},
		 {1,1,1,1,1,1,1,1,1,1}};
	
	
	public static void main(String[] args)
	{
		int currentLevel = 1;
		final int maximumLevels = 2;
		int ogreQuantity = 3;
		int[][] dungeonModel = null;
		
		if(currentLevel == 1)
		{
			dungeonModel = defaultDungeon1;
		}
		else
		{
			dungeonModel = defaultDungeon2;
		}
		
		Game game = new Game(currentLevel, dungeonModel, false, false, false, true, Personality.SUSPICIOUS, ogreQuantity);
		
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
					boolean heroIsDead = game.getHero().isDead();
					if((currentLevel == maximumLevels) || heroIsDead)
					{
						break;		
					}
					else
					{
						running = true;
						currentLevel ++;
						dungeonModel = defaultDungeon2;
						game = new Game(currentLevel, dungeonModel, true, true, true, true, Personality.ROOKIE, 3);						
					}
				}
			}
		}
		
		System.out.println(game.printDungeonString());
		System.out.println("GAME OVER!");
		
	}
	
	
}
