package logic;

public class Game {

	private boolean running;
	private Dungeon dungeon;
	private Hero hero;

	public Game()
	{
		dungeon = new Dungeon();
		hero = new Hero(1,1,'H');
	}
	
	public String printDungeonString()
	{
		return dungeon.printDungeonString();
	}
	
	public boolean playerTurn(String hero_movement)
	{
		hero.movement(hero_movement);
		return running;
	}
	
	public boolean isAlive()
	{
		if(hero.getState() != DEAD)
		{
			return true;
		}
	}
	
}
