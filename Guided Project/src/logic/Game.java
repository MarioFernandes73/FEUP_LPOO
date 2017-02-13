package logic;

public class Game {

	private boolean running;
	private Dungeon dungeon;
	private int level;
	private Hero hero;
	private Guard guard;
	private Key key;
	private Lever lever;

	public Game(int level)
	{
		dungeon = new Dungeon(level);
		hero = new Hero(1,1,'H');
		this.level = level;
		
		hero = dungeon.spawnHero();
		guard = dungeon.spawnGuard();
		key = dungeon.spawnKey(level);
		lever = dungeon.spawnLever(level);
	}
	
	public String printDungeonString()
	{
		return dungeon.printDungeonString();
	}
	
	public boolean playerTurn(String hero_movement)
	{
		running = hero.movement(hero_movement, dungeon);
		dungeon.updateDungeon(hero, guard, key, lever);
		return running;
	}
	
}
