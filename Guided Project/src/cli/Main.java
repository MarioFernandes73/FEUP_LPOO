package cli;

import logic.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		Game game = new Game();
		
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		
		while(running)
		{
			System.out.println(game.printDungeonString());
			System.out.println("Choose the direction to move your hero (w a s d): ");
			String hero_movement = sc.nextLine();
			if(hero_movement.equals("w") || hero_movement.equals("a") || hero_movement.equals("s") || hero_movement.equals("d") )
			{
				game.playerTurn(hero_movement);
			}
		}
	}
	
	
}
