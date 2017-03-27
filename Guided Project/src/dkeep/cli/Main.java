package dkeep.cli;

import dkeep.logic.*;

import java.util.Scanner;

import dkeep.logic.Game;


public class Main {

	
	//Default Dungeon (used for early tests)

	
	
	public static void main(String[] args)
	{
		int maximumLevels = 2;
		
		Game game = new Game(new GameState(1));
		
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
				if(!analyzeGameState(game, running, maximumLevels))
					break;
			}
		}
		sc.close();
		System.out.println(game.printDungeonString());
		System.out.println("GAME OVER!");
		
	}

	private static boolean analyzeGameState(Game game, boolean running, int maximumLevels) {
		if(game.getHero().isDead())
		{
			return false;
		}
		
		if(running == false)		// level has ended
		{
			boolean heroIsDead = game.getHero().isDead();
			if((game.getGameState().currentLevel == maximumLevels) || heroIsDead)
			{
				return false;	
			}
			else
			{
				running = true;
				game = new Game(new GameState(2));						
			}
		}
		return true;
	}
	
	
}
