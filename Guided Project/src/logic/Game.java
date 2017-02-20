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
	
	public Dungeon getDungeon()
	{
		return dungeon;
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
		//prepare dungeon for next turn
		resetSymbols();
		//hero turn
		final Point heroNextCoord = hero.movement(heroMovement);								//hero moves and will (return) the state he is (dead, finishing the level, activating a lever, opening a door).
		final int nextTile = dungeon.getTile(heroNextCoord);									//next tile identifier
		final boolean runningHero = InteractionStateMachine(hero, nextTile, heroNextCoord);		//hero interact
		dungeon.updateDungeon(hero, npcs, key, lever, doors);									//dungeon updates (effectively and visually)
		//npc turn
		npcsMovement();																			//npcs move
																			//npcs attack
		dungeon.updateDungeon(hero, npcs, key, lever, doors);									//dungeon updates (effectively and visually)
		npcsAttack();	
		running = (hero.isDead() || runningHero);												//changes running to the appropriate state according to this turn
		return running;																			//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
	}
	
	public void resetSymbols()
	{
		if(key!=null && key.getSymbol() == '$')
		{
			key.setSymbol('k');
		}

	}
	
	public void npcsAttack()
	{
		boolean heroHit = false;
		for(int i = 0; i < npcs.size(); i++)
		{
			Point[] tilesAttacked = npcs.get(i).attack();
			Point[] tilesAttackedWeapon = npcs.get(i).weaponAttack();
			for(int j = 0; j < tilesAttacked.length; j++)
			{
				final boolean normalAttack = dungeon.checkTile(tilesAttacked[j], 2);
				heroHit = (heroHit | normalAttack);
			}
			if(tilesAttackedWeapon != null)
			{
				//check if weapon is on top of key
				final boolean checkWeaponPosKey = dungeon.checkTile(tilesAttackedWeapon[0], 9);
				final boolean checkWeaponPosSpace = dungeon.checkTile(tilesAttackedWeapon[0], 0);
				if(checkWeaponPosKey)
				{
					key.setSymbol('$');
				}
				else if(checkWeaponPosSpace)
				{
					dungeon.setTile(tilesAttackedWeapon[0], 11);
				}
				
				for(int j = 0; j < tilesAttackedWeapon.length; j++)
				{
					//check if weapon has killed hero
					final boolean weaponAttack = dungeon.checkTile(tilesAttackedWeapon[j], 2);
					heroHit = (heroHit | weaponAttack);
				}
			}
			
			if(heroHit)
			{
				hero.died();
				return;
			}
		}

	}
	
	public void npcsMovement()
	{
		for (int i = 0; i < npcs.size(); i++)
		{
			final Character npc = npcs.get(i);
			final String move = npc.createMovement();
			final Point npcTileMoved = npc.movement(move);
			final int nextTile = dungeon.getTile(npcTileMoved);
			final boolean runningNpc = InteractionStateMachine(npc, nextTile, npcTileMoved);		
		}
	}
	
	//returns true if the level is running or false if the level is over
	//based on the the state of the hero and the tile he has just moved to determines what happens in the dungeon
	//based on the tile he has just moved to, determines the action of the hero.
	public boolean InteractionStateMachine(Character character, int tileMoved, Point tileCoord)
	{
		final boolean isVulnerable = character.isVulnerable();
		if(tileMoved == 0)								//character selected an empty tile to move
		{
			character.move(tileCoord);
		}
		else if(tileMoved == 1)							// character selected a wall tile to move
		{
			
		}
		else if(tileMoved == 2)							// character moved to the hero tile
		{
			hero.died();
		}
		else if(tileMoved == 3)							// character moved to a guard tile;
		{
			if(isVulnerable)
			{
				character.died();
			}
		}
		else if(tileMoved == 4 || tileMoved == 6)		// character moved to a closed door
		{
			final boolean closedDoorInteraction = character.closedDoorInteraction();
			if(closedDoorInteraction)
			{
				dungeon.openDoor(doors, tileCoord);
			}
		}
		else if(tileMoved == 5)							// character moved to an opened normal door
		{
			character.move(tileCoord);
		}
		else if(tileMoved == 7)							// character moved to an opened exit door
		{
			character.move(tileCoord);
			character.getCoord().setLocation(tileCoord);
			final boolean advanceLevel = character.canFinishLevel();
			if(advanceLevel)
			{
				return false;
			}
			
		}
		else if(tileMoved == 8)							// character moved to a lever tile
		{
			character.move(tileCoord);
			final boolean leverInteraction = character.leverInteraction();
			if(leverInteraction)
			{
				dungeon.changeDoors(doors);				
			}	
		}
		else if(tileMoved == 9)							// character moved to a key tile
		{
			character.move(tileCoord);
			final boolean hasKey = character.carryKey();
			if(hasKey)
			{
				key=null;
			}
		}
		else if(tileMoved == 10)						// character moved to an ogre tile
		{
			if(isVulnerable)
			{
				character.died();
			}
		}
		
		return true;
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
					npcs.add(new Guard(j,i,tile));//construtor necessita de variavel personalidade
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
}
