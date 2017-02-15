package logic;

import java.util.ArrayList;

public class Game {

	private boolean running;
	private Dungeon dungeon;
	private int level;
	private Hero hero;
	private Key key;
	private Lever lever;
	private ArrayList<Door> doors = new ArrayList<Door>();
	private ArrayList<Character> npcs = new ArrayList<Character>();

	public Game(int level)
	{
		dungeon = new Dungeon(level);
		this.level = level;
		running = true;
		spawnEntities();
	}
	
	public void spawnEntities()
	{
		for (int i = 0; i < dungeon.getHeight(); i++)
		{
			for (int j = 0; j< dungeon.getWidth(); j++)
			{
				final int tile = dungeon.getDungeon()[i][j];
				if(tile == 2)
				{
					hero = new Hero(j,i,tile);
				}
				else if (tile == 3)
				{
					npcs.add(new Guard(j,i,tile));
				}
				else if(tile == 4 || tile == 5 || tile == 6 || tile == 7)
				{
					doors.add(new Door(j,i,tile));
				}
				else if(tile == 8)
				{
					lever = new Lever(j,i,tile);
				}
				else if(tile == 9)
				{
					key = new Key(j,i,tile);
				}
				else if(tile == 10)
				{
					npcs.add(new Ogre(j,i,tile));
				}
			}
		}
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
		final int heroState = hero.movement(heroMovement, dungeon);		//hero moves
		final boolean runningNpcs = npcsMovement();						//npcs move and interact
		final boolean runningHero = heroStateStateMachine(heroState);	//hero interact
		dungeon.updateDungeon(hero, npcs, key, lever, doors);			//dungeon updates (effectively and visually)
		running = (runningHero || runningNpcs);	
		return running;													//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
	}
	
	public boolean npcsMovement()
	{
		for (int i = 0; i < npcs.size(); i++)
		{
			final Character npc = npcs.get(i);
			final String move = npc.createMovement();
			final int running = npc.movement(move, dungeon);
			if (running == 1)
			{
				return false;
			}
		}
		return true;
	}
	
	
	public boolean heroStateStateMachine(int heroState)
	{		
		if(hero.isDead() || heroState == 2)		// hero has either died or finished the level
		{
			return false;
		}
		else if(heroState == 1)		// hero has made a valid movement
		{
			return true;
		}
//		else if(heroState == 2)		// hero has finished the level
//		{
//
//		}
		else if(heroState == 3)		// hero has walked over a lever
		{
			dungeon.changeDoors(doors);
		}
		else if(heroState == 4)		//hero has walked over a key
		{
			hero.setKey(true);			
		}
		return true;
	}	
}
