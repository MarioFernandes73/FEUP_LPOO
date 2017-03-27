package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @brief represents the game itself, all the elements are controlled by this class 
 */
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * stores information about what features will be in the game, such as weapons, ability to attack, guard personality, etc.
	 */
	private GameState gameState;
	
	/**
	 * represents the board
	 */
	private Dungeon dungeon;
	
	/**
	 * represents the character the user will controll
	 */
	private Hero hero;
	
	/**
	 * represents a game element used to limit the map
	 */
	private Wall genericWallTile;
	
	/**
	 * represents a game element wich is empty, to where other characters can move
	 */
	private Empty genericEmptyTile;
	
	/**
	 * a list with all the doors currently present in the game
	 */
	private ArrayList<Door> doors = new ArrayList<Door>();
	
	/**
	 * a list with all the npcs currently present in the game
	 */
	private ArrayList<Character> npcs = new ArrayList<Character>();
	
	/**
	 * a list with all the elements currently present in the game
	 */
	private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
	
	/**
	 * Game constructor
	 * 
	 * @param gameState includes all the information regarding the game features
	 */
	public Game(GameState gameState)
	{
		genericEmptyTile = new Empty();
		genericWallTile = new Wall();
		this.gameState = gameState;
		dungeon = new Dungeon(createDungeon(gameState.currentLevel,gameState.dungeonModel));	

	}
	
	/**
	 * Creates a matrix composed by all game elements represented by objects
	 * 
	 * @param dungeonIdentifier the current game level
	 * 
	 * @param dungeonModel a matrix with all the game elements, except they are represented as numbers and not as objects
	 * 
	 * @return returns a matrix with all the game elements, now represented as objects
	 */
	public GameObject[][] createDungeon(int dungeonIdentifier,int[][] dungeonModel)
	{
		GameObject[][] defaultDungeon;
		
		defaultDungeon = makeDungeonScheme(dungeonModel);
		allObjects.addAll(npcs);
		setWeapons();
		
		return defaultDungeon;
	}
	
	private GameObject[][] makeDungeonScheme(int[][] dungeonModel) {
		
		GameObject[][] defaultDungeon = new GameObject[dungeonModel.length][dungeonModel[0].length];
		
		//cycle that creates the skeleton of the dungeon (nothing but walls and empty tiles)
		for(int i = 0; i < dungeonModel.length; i++){
			for(int j = 0; j < dungeonModel[i].length; j++){
				int identifier = dungeonModel[i][j];
				if(!specialIdentifier(identifier, defaultDungeon,i,j)){
					GameObject object = Auxiliary.getNewEntity(new Point(j,i), identifier);
					populateDungeon(identifier, object);
						if(!object.canMove())
							defaultDungeon[i][j] = object;
						else
							defaultDungeon[i][j] = genericEmptyTile;}}}
		return defaultDungeon;
		
	}

	private boolean specialIdentifier(int identifier, GameObject[][] defaultDungeon, int i, int j) {
		if(identifier == 0)		
		{
			defaultDungeon[i][j] = genericEmptyTile;
		}
		else if(identifier == 1)
		{
			defaultDungeon[i][j] = genericWallTile;
		}
		else if(identifier == 3)
		{
			GameObject objectGuard = Auxiliary.getNewEntity(new Point(j,i), identifier, gameState.guardPersonality);
			npcs.add((Guard) objectGuard);
			defaultDungeon[i][j] = genericEmptyTile;
		}
		else
			return false;
		
		return true;
	}

	private void populateDungeon(int identifier, GameObject object)
	{
		switch(identifier){
		case 2:hero = (Hero) object;
			if(this.gameState.currentLevel == 2)
				hero.setSymbol('A');
			allObjects.add(hero);
			break;
		case 4:
		case 5:
		case 6:
		case 7:doors.add((Door) object);
			break;
		case 10:npcs.add((Ogre) object);
			for(int k = 1; k<gameState.ogreQuantity; k++)
				npcs.add(new Ogre(object.getCoord()));}
	}
	
	private void setWeapons()
	{
		if(gameState.attackingEnemiesWeapons)
		{
			for (int i = 0; i < npcs.size(); i++)
			{
				Character currentNpc = npcs.get(i);
				if(currentNpc instanceof Ogre)
				{
					((Ogre)currentNpc).setWeapon(new Club(null));
				}
			}
		}
	}
	
	
	/**
	 * @return returns the game dungeon
	 */
	public Dungeon getDungeon()
	{
		return dungeon;
	}
	
	/**
	 * @return returns the game dungeon in a string format
	 */
	public String printDungeonString()
	{
		return dungeon.printDungeonString(allObjects);
	}
	
	/**
	 * @return returns the game hero character
	 */
	public Hero getHero()
	{
		return hero;
	}
	
	/**
	 * Moves the hero and changes all other elements accordingly
	 * 
	 * @param heroMovement string that represents the hero's moving direction for the current turn
	 * 
	 * @return returns true if the hero is alive AND the current level hasn't yet been finished, returns false otherwise
	 */
	public boolean playerTurn(String heroMovement)
	{
		//hero turn
		Point heroNextCoord = hero.movement(heroMovement);					//hero moves and will (return) the state he is (dead, finishing the level, activating a lever, opening a door).
		GameObject nextTile = dungeon.getTile(heroNextCoord);				//next tile identifier
		boolean runningHero = InteractionStateMachine(hero, nextTile, heroNextCoord);		//hero interact
		if(gameState.attackingHero)
			heroAttack();
		//npc turn
		if(gameState.movingEnemies)
			npcsMovement();			//npcs move
		if(gameState.attackingEnemies)
			npcsAttack();			//npcs attack	
		gameState.running = (!(hero.isDead()) && runningHero);	//changes running to the appropriate state according to this turn
		return gameState.running;		//if either the hero died on his own / finished the level or the Npcs did something to prevent the hero from winning ex: killed him returns 0)
	}
	
	/**
	 * This function is responsible for letting the hero stun the ogres
	 */
	public void heroAttack()
	{
		Point[] tilesAttacked = hero.attack();
		for(int i = 0; i<tilesAttacked.length; i++)
		{
			for(int j = 0; j<npcs.size(); j++)
			{
				Character currentNpc = npcs.get(j);
				if(currentNpc.getCoord().equals(tilesAttacked[i]) && currentNpc instanceof Ogre)
				{
					((Ogre)currentNpc).stunned();
				}
			}
		}
	}
	
	/**
	 * Lets all the other characters attack(kill) the hero
	 */
	public void npcsAttack()
	{
		boolean heroHit = false;
		for(int i = 0; i < npcs.size(); i++){
			Character currentNpc = npcs.get(i);
			Point[] tilesAttacked = currentNpc.attack();				//area of attack of the npc (standard)
			Point[] tilesAttackedWeapon = currentNpc.weaponAttack();	//area of attack of the weapon of the npc
			if(tilesAttacked != null){
				for(int j = 0; j < tilesAttacked.length; j++){
					if(tilesAttacked[j].equals(hero.getCoord()))
						heroHit = true;}}
			heroHit = npcsWeaponsAttack(currentNpc, tilesAttackedWeapon, heroHit);
			
			if(heroHit){
				hero.died();
				return;}}
	}
	
	private boolean npcsWeaponsAttack(Character currentNpc, Point[] tilesAttackedWeapon, boolean heroHit2) {
		boolean heroHit = heroHit2;
		if(currentNpc.getWeapon()!= null && tilesAttackedWeapon != null && gameState.attackingEnemiesWeapons)
		{
			currentNpc.getWeapon().move(tilesAttackedWeapon[0]);
			for(int j = 0; j < tilesAttackedWeapon.length; j++)
			{
				//check if weapon has killed hero
				if(tilesAttackedWeapon[j].equals(hero.getCoord()))
					heroHit = true;
			}
		}
		return heroHit;
	}

	/**
	 * Moves all npcs currently in game
	 */
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
	
	/**
	 * Determines what happens in the dungeon based on the character currently being analyzed and the tile he will move to
	 * 
	 * @param character the game character we're currently analyzing
	 * 
	 * @param nextTile the tile to where the character will move
	 * 
	 * @param characterNextCoord the coordinates to where the character will move
	 * 
	 * @return returns true if the current level hasn't yet been finished, returns false if it has been finished
	 */
	public boolean InteractionStateMachine(Character character, GameObject nextTile, Point characterNextCoord)
	{
		
		if(nextTile.isPassable())
		{
			character.move(characterNextCoord);
			
			if(!interactionPassable(nextTile,character))
				return false;
		}
		else
		{
			interactionNotPassable(nextTile, character);
		}
		return true;
	}	
	
	private boolean interactionPassable(GameObject nextTile, Character character) {
		if(nextTile instanceof Door && ((Door)nextTile).isEndingDoor() && character instanceof Hero)
		{
			return false;	//hero has finished the level
		}

		else if(nextTile instanceof Lever && character instanceof Hero)
		{
			changeDoors();
		}
		else if(nextTile instanceof Key && character instanceof Hero)
		{
			((Hero)character).carryKey();
			dungeon.setTile(nextTile.getCoord(), genericEmptyTile);
		}
		return true;
	}

	private void interactionNotPassable(GameObject nextTile, Character character) {
		if(nextTile instanceof DoorClosed && character instanceof Hero)
		{
			if(hero.getKey())
			{
				changeDoors();
			}
		}
		
	}

	/**
	 * Unlocks(opens) all current game doors
	 */
	public void changeDoors()
	{
		ArrayList<Door> newDoors = new ArrayList<Door>();
		for(int i = 0; i < doors.size(); i++)
		{
			dungeon.setTile(doors.get(i).getCoord(), doors.get(i).changeOpenedState());
			newDoors.add(doors.get(i).changeOpenedState());
		}
		this.doors = newDoors;
	}
	
	public ArrayList<GameObject> getAllObjects()
	{
		return this.allObjects;
	}
	
	public ArrayList<Character> getNpcs()
	{
		return this.npcs;
	}
	
	public GameState getGameState()
	{
		return this.gameState;
	}
	
	public void setLevel(int level)
	{
		this.gameState.currentLevel = level;
		if(level == 2)
		{
			this.hero.setSymbol('A');
			this.gameState.attackingHero = true;
		}

	}
	
	public GameObject[][] getMap()
	{
		return this.dungeon.getMap();
	}
}
