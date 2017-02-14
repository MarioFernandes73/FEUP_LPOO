package logic;

import java.util.ArrayList;

public class Game {

	private boolean running;
	private Dungeon dungeon;
	private int level;
	private Hero hero;
	private Guard guard;
	private Key key;
	private Lever lever;
	private ArrayList<Door> doors;

	public Game(int level)
	{
		dungeon = new Dungeon(level);
		hero = new Hero(1,1,'H');
		this.level = level;
		
		hero = dungeon.spawnHero();
		guard = dungeon.spawnGuard();
		key = dungeon.spawnKey(level);
		lever = dungeon.spawnLever(level);
		doors = dungeon.spawnDoors();
	}
	
	public String printDungeonString()
	{
		return dungeon.printDungeonString();
	}
	
	public Hero getHero()
	{
		return hero;
	}
	
	public boolean playerTurn(String heroMovement)
	{
		guard.movement(guard.nextMovement(), dungeon);
		final int heroState = hero.movement(heroMovement, dungeon);
		final boolean running = heroStateStateMachine(heroState);
		dungeon.updateDungeon(hero, guard, key, lever, doors);
		return running;
	}
	
	public boolean heroStateStateMachine(int heroState)
	{
		if((heroState == 0 && hero.isDead()) || heroState == 3)		// hero has either died or finished the level
		{
			return false;
		}
		else if(heroState == 1)		// hero has made a valid movement
		{
			
		}
		else if(heroState == 2)		// hero has walked over a level/key
		{
			if(key == null)
			{
				dungeon.changeDoors();
			}
			else
			{
				hero.setKey(true);
			}
		}
		return true;
	}
	
}
