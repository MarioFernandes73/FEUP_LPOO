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
		final int heroTileMoved = hero.movement(heroMovement, dungeon);	//hero moves and will (return) the state he is (dead, finishing the level, activating a lever, opening a door).
		final boolean runningNpcs = npcsMovement();						//npcs move and interact
		final boolean runningHero = heroStateMachine(heroTileMoved);	//hero interact
		dungeon.updateDungeon(hero, npcs, key, lever, doors);			//dungeon updates (effectively and visually)
		running = (runningHero && runningNpcs);	
		return running;													//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
	}
	
	public boolean npcsMovement()
	{
		for (int i = 0; i < npcs.size(); i++)
		{
			final Character npc = npcs.get(i);
			final String move = npc.createMovement();
			final int npcTileMoved = npc.movement(move, dungeon);
			final boolean running = npcStateMachine(npc, npcTileMoved);
			if(running == false)
			{
				return false;
			}
		}
		return true;
	}
	
	
	//returns true if the level is running or false if the level is over
	//based on the state of the npc and the tile has has just moved to determines what happens in the dungeon
	//based on the tile he has just moved to, determines the action of the npc.
	public boolean npcStateMachine(Character npc, int npcTileMoved)
	{
		//if()
	
		return true;
	}
	
	//returns true if the level is running or false if the level is over
	//based on the the state of the hero and the tile he has just moved to determines what happens in the dungeon
	//based on the tile he has just moved to, determines the action of the hero.
	public boolean heroStateMachine(int heroTileMoved)
	{		
		if(hero.isDead() || heroTileMoved == 2)		// hero has either died or finished the level
		{
			return false;
		}
		else if(heroTileMoved == 1)		// hero has made a valid movement
		{
			return true;
		}
		else if(heroTileMoved == 3)		// hero has walked over a lever
		{
			dungeon.changeDoors(doors);
		}
		else if(heroTileMoved == 4)		//hero has walked over a key
		{
			hero.setKey(true);			
		}
		return true;
	}	
}
