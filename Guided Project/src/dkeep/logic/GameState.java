package dkeep.logic;

import dkeep.logic.Guard.Personality;

public class GameState {
	public boolean movingEnemies = true;
	public boolean attackingEnemies = true;
	public boolean attackingEnemiesWeapons = true;
	public boolean attackingHero = false;
	public Personality guardPersonality = Personality.ROOKIE;
	public int ogreQuantity = 3;
	public boolean running = true;
	public int currentLevel;
	public int[][]dungeonModel;
	
	
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