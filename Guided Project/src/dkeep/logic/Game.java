package dkeep.logic;

import java.util.ArrayList;

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

	//Default Dungeon (used for early tests)
	 int[][] defaultDungeon1 = 
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
	
	int[][] defaultDungeon2 = 
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
	
	public Game(int level)
	{
		genericEmptyTile = new Empty(0);
		genericWallTile = new Wall(1);
		this.level = level;
		running = true;
		dungeon = new Dungeon(createDungeon(level));		
	}
	
	public GameObject[][] createDungeon(int dungeonIdentifier)
	{
		if(dungeonIdentifier == 1)
		{
			dungeonModel = this.defaultDungeon1;
		}
		else
		{
			dungeonModel = this.defaultDungeon2;
		}
		
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
					GameObject object = Auxiliary.getNewEntity(new Point(j,i), identifier);
					if(identifier == 2)
					{
						hero = (Hero) object;
					}
					else if(identifier == 3)
					{
						npcs.add((Guard) object);
					}
					else if(identifier == 4 || identifier == 5 || identifier == 6 || identifier == 7)
					{
						doors.add((Door) object);
					}						
					else if(identifier == 8)
					{
						lever = (Lever) object;
					}
					else if(identifier == 9)
					{
						key = (Key) object;
					}
					else if(identifier == 10)
					{
						npcs.add((Ogre) object);
					}
					if(!object.canMove())
					{
						defaultDungeon[i][j] = object;
					}
					else
					{
						defaultDungeon[i][j] = genericEmptyTile;
						allObjects.add(object);
					}
					
				}
			}
		}
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
		Point heroNextCoord = hero.movement(heroMovement);								//hero moves and will (return) the state he is (dead, finishing the level, activating a lever, opening a door).
		GameObject nextTile = dungeon.getTile(heroNextCoord);									//next tile identifier
		boolean runningHero = InteractionStateMachine(hero, nextTile, heroNextCoord);		//hero interact
//npc turn
		npcsMovement();																			//npcs move
																			//npcs attack
//npcsAttack();	
		running = (hero.isDead() || runningHero);												//changes running to the appropriate state according to this turn
		return running;																			//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
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
			Point npcNextCoord = npc.movement(npc.createMovement());
			GameObject nextTile = dungeon.getTile(npcNextCoord);
			InteractionStateMachine(npc, nextTile, npcNextCoord);		
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
				return false;
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
	
	
}
