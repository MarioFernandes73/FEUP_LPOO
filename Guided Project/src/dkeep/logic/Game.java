package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.logic.Guard.Personality;

public class Game {

	private boolean running;
	private Dungeon dungeon;
	private int level;
	private Hero hero;
	private Key key;
	private Lever lever;
	private int dungeonModel[][];
	private Wall genericWallTile;
	private Empty genericEmptyTile;
	private Club club;
	private ArrayList<Door> doors = new ArrayList<Door>();
	private ArrayList<Character> npcs = new ArrayList<Character>();
	private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
	
	public Game(int level, int[][]dungeonModel)
	{
		this.dungeonModel=dungeonModel;
		genericEmptyTile = new Empty(0);
		genericWallTile = new Wall(1);
		this.level = level;
		running = true;
		dungeon = new Dungeon(createDungeon(level));		
	}
	
	public GameObject[][] createDungeon(int dungeonIdentifier)
	{
		GameObject[][] defaultDungeon = new GameObject[dungeonModel.length][dungeonModel[0].length];
		
		//cycle that creates the skeleton of the dungeon (nothing but walls and empty tiles)
		for(int i = 0; i < dungeonModel.length; i++)
		{
			for(int j = 0; j < dungeonModel[i].length; j++)
			{
				int identifier = dungeonModel[i][j];
				if(identifier == 0)		
				{
					defaultDungeon[i][j] = genericEmptyTile;
				}
				else if(identifier == 1)
				{
					defaultDungeon[i][j] = genericWallTile;
				}
				else
				{
					if(identifier != 3)
					{
						GameObject object = Auxiliary.getNewEntity(new Point(j,i), identifier);
						if(identifier == 2)
						{
							hero = (Hero) object;
							if(level == 2)
							{
								hero.setSymbol('A');
							}
							allObjects.add(hero);
						}
						else if(identifier == 4 || identifier == 5 || identifier == 6 || identifier == 7)
						{
							doors.add((Door) object);
						}						
						else if(identifier == 8)
						{
							lever = (Lever) object;
							allObjects.add(lever);
						}
						else if(identifier == 9)
						{
							key = (Key) object;
							allObjects.add(key);
						}
						else if(identifier == 10)
						{
							Random ogreGenerator = new Random();
							npcs.add((Ogre) object);
							int ogreQuantity = ogreGenerator.nextInt(2);	// from 0 to 2 extra ogres
							for(int k = 0; k < ogreQuantity; k++)
							{
								npcs.add(new Ogre(object.getCoord(),10));
							}	
						}
						if(!object.canMove())
						{
							defaultDungeon[i][j] = object;
						}
						else
						{
							defaultDungeon[i][j] = genericEmptyTile;
						}
					}
					else if(identifier == 3)
					{
						GameObject objectGuard = Auxiliary.getNewEntity(new Point(j,i), identifier, Personality.DRUNKEN);
						npcs.add((Guard) objectGuard);
						defaultDungeon[i][j] = genericEmptyTile;
					}	
				}
			}
		}
		allObjects.addAll(npcs);
		allObjects.addAll(doors);
		if(level == 2)
		{
			club = new Club(null, 11);
			allObjects.add(club);
		}
		return defaultDungeon;
	}
	
	public Dungeon getDungeon()
	{
		return dungeon;
	}
	
	
	public String printDungeonString()
	{
		return dungeon.printDungeonString(allObjects);
	}
	
	public Hero getHero()
	{
		return hero;
	}
	
	public boolean playerTurn(String heroMovement)
	{
		//hero turn
		Point heroNextCoord = hero.movement(heroMovement);					//hero moves and will (return) the state he is (dead, finishing the level, activating a lever, opening a door).
		GameObject nextTile = dungeon.getTile(heroNextCoord);				//next tile identifier
		boolean runningHero = InteractionStateMachine(hero, nextTile, heroNextCoord);		//hero interact
		if(level == 2)
		{
			heroAttack();
		}
		
		//npc turn
		npcsMovement();			//npcs move
		npcsAttack();			//npcs attack	
		running = (!(hero.isDead()) && runningHero);	//changes running to the appropriate state according to this turn
		return running;		//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
	}
	
	public void heroAttack()
	{
		Point[] tilesAttacked = hero.attack();
		for(int i = 0; i<tilesAttacked.length; i++)
		{
			for(int j = 0; j<npcs.size(); j++)
			{
				Character currentNpc = npcs.get(j);
				if(currentNpc.getCoord().equals(tilesAttacked[i]))
				{
					currentNpc.stunned();
				}
			}
		}
	}
	
	public void npcsAttack()
	{
		boolean heroHit = false;
		for(int i = 0; i < npcs.size(); i++)
		{
			Point[] tilesAttacked = npcs.get(i).attack();				//area of attack of the npc (standard)
			Point[] tilesAttackedWeapon = npcs.get(i).weaponAttack();	//area of attack of the weapon of the npc
			for(int j = 0; j < tilesAttacked.length; j++)
			{
				if(tilesAttacked[j].equals(hero.getCoord()))
					heroHit = true;
			}
			if(tilesAttackedWeapon != null)
			{
				club.move(tilesAttackedWeapon[0]);
				for(int j = 0; j < tilesAttackedWeapon.length; j++)
				{
					//check if weapon has killed hero
					if(tilesAttackedWeapon[j].equals(hero.getCoord()))
						heroHit = true;
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
			Character npc = npcs.get(i);
			String npcMovement = npc.createMovement();
			if(npcMovement != null)
			{
				Point npcNextCoord = npc.movement(npcMovement);
				GameObject nextTile = dungeon.getTile(npcNextCoord);
				InteractionStateMachine(npc, nextTile, npcNextCoord);
			}
		}
	}
	
	//returns true if the level is running or false if the level is over
	//based on the the state of the hero and the tile he has just moved to determines what happens in the dungeon
	//based on the tile he has just moved to, determines the action of the hero.
	public boolean InteractionStateMachine(Character character, GameObject nextTile, Point characterNextCoord)
	{
		
		if(nextTile.isPassable())
		{
			character.move(characterNextCoord);
			
			if(nextTile.getIdentifier() == 7 && character instanceof Hero)
			{
				return false;	//hero has finished the level
			}
			else if(nextTile instanceof Lever && character instanceof Hero)
			{
				changeDoors(doors);
			}
			else if(nextTile instanceof Key && character instanceof Hero)
			{
				character.carryKey();
				key=null;
				dungeon.setTile(nextTile.getCoord(), genericEmptyTile);
			}
		}
		else
		{
			if(nextTile.getIdentifier() == 6 && character instanceof Hero)
			{
				if(hero.getKey())
				{
					changeDoors(doors);
				}
			}
		}
		return true;
	}	
	
	public void changeDoors(ArrayList<Door> doors)
	{
		for(int i = 0; i < doors.size(); i++)
		{
			doors.get(i).changeOpenedState();
		}
	}
	
	public boolean gameRunning()
	{
		return running;
	}

	public int getLevel() {
		
		return this.level;
	}
	
}
