package dkeep.logic;

import dkeep.logic.Guard.Personality;

public class GameState {
	public boolean movingEnemies;
	public boolean attackingEnemies;
	public boolean attackingEnemiesWeapons;
	public boolean attackingHero;
	public Personality guardPersonality;
	public int ogreQuantity;
	public boolean running;
	public int currentLevel;
	public int[][]dungeonModel;
	
}
