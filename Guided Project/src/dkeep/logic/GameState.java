package dkeep.logic;

import java.io.Serializable;

import dkeep.logic.Guard.Personality;

/**
 * 
 * Keeps track of the features activated in the game and extra variables,
 * such as moving enemies, weapon allwance, guard personality, ogre quantity, etc.
 *
 */
public class GameState implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indicates if there will be moving enemies in game
	 */
	public boolean movingEnemies = true;
	
	/**
	 * Indicates if there will be enemies attacking the hero
	 */
	public boolean attackingEnemies = true;
	
	/**
	 * Indicates if the enemies will have weapons(ogre-club)
	 */
	public boolean attackingEnemiesWeapons = true;
	
	/**
	 * Indicates if the hero will be able to attack(stun ogres)
	 */
	public boolean attackingHero = false;
	
	/**
	 * Indicates the personality of the existing guard
	 */
	public Personality guardPersonality = Personality.ROOKIE;
	
	/**
	 * Indicates the ogre quantity(3 by default)
	 */
	public int ogreQuantity = 3;
	
	/**
	 * Indicates the game runnign state
	 */
	public boolean running = true;
	
	/**
	 * Indicates the current level
	 */
	public int currentLevel;
	
	/**
	 * The begging state of dungeon(complete model) of the current level
	 */
	public int[][]dungeonModel;
	
	/**
	 * GameState constructor
	 * Selects the beggining state of the dungeon according to game level
	 * @param level game level
	 */
	public GameState(int level)
	{
		this.currentLevel = level;
		if(currentLevel == 1)
		{
			this.dungeonModel = defaultDungeon1;
		}
		else
		{
			this.dungeonModel = defaultDungeon2;
			this.attackingHero = true;
		}
	}
	
	/**
	 * More specialized game constructor
	 * Lets us choose difficulty
	 * And accepts a personalized dungeon
	 * It influences difficulty by setting/unsetting attakcing enemies, weapons, and enemy movement
	 * @param dungeonModel personalized dungeon wich will be selected to the game
	 * @param difficulty difficuly of the dungeon(0 or 1)
	 */
	public GameState(int[][] dungeonModel, int difficulty)
	{
		this.dungeonModel = dungeonModel;
		switch(difficulty)
		{
		case 0:
			this.attackingEnemies = false;
			this.attackingEnemiesWeapons = false;
			break;
		case 1:
			this.movingEnemies = false;
			break;
		}
	}
	
	/**
	 * More specialized game constructor
	 * @param level game level
	 * @param ogreQuantity ogre quantity
	 * @param guardPersonalityIndex guard personality
	 */
	 public GameState(int level, int ogreQuantity, int guardPersonalityIndex) 
	{
		this.currentLevel = level;
		if(currentLevel == 1)
		{
			this.dungeonModel = defaultDungeon1;
			
			switch(guardPersonalityIndex)
			{
			case 0:
				this.guardPersonality = Personality.ROOKIE;
				break;
			case 1:
				this.guardPersonality = Personality.DRUNKEN;
				break;
			case 2:
				this.guardPersonality = Personality.SUSPICIOUS;
				break;
			}		
		}
		else
		{
			this.dungeonModel = defaultDungeon2;
			this.attackingHero = true;
			this.ogreQuantity = ogreQuantity;
		}
	}

	/**
	 * default level 1 beggining dungeon
	 */
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
	
	/**
	 * default level 2 complete dungeon
	 */
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
	
}
