package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dkeep.logic.*;
import dkeep.logic.Character;
import dkeep.logic.Character.State;
import dkeep.logic.Guard.Personality;

public class TestDungeonGameLogic {
	
	// Point class (x,y) x-> horizontal direction, y-> vertical direction
	
		int[][] map1 = {{1,1,1,1,1},
						{1,2,0,3,1},
						{6,0,0,0,1},
						{6,8,0,0,1},
						{1,1,1,1,1}};
		
		int[][] map2 = {{1,1,1,1,1},
						{1,0,10,9,1},
						{1,0,0,0,1},
						{1,0,0,0,1},
						{6,0,2,9,1},
						{1,1,1,1,1}};
		
		@Test
		public void printDungeon(){
		Game g = new Game(1, map1, false,false,false,false, Personality.ROOKIE, 3);
		String mapString = "X X X X X \nX H   G X \nI       X \nI k     X \nX X X X X \n";
		assertEquals(g.getDungeon().printDungeonString(g.getAllObjects()), mapString);
		}
		
		@Test
		public void testHeroMovementFreeCell() {
			Game g = new Game(1, map1, false,false,false,false, Personality.ROOKIE, 3);
			assertEquals(g.getHero().getState(), State.ALIVE);		// hero state is alive
			assertEquals(g.getHero().toString(),'H'+"");			// hero has normal symbol
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moves to an empty tile (down)
			assertEquals(new Point(1,2), g.getHero().getCoord());	// ending position for the hero
		}
		
		@Test
		public void testHeroMovementWall() {
			Game g = new Game(1, map1,false,false,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("w");										// moves to a wall tile (up)
			assertEquals(new Point(1,1), g.getHero().getCoord());	// ending position for the hero
		}
		
		@Test
		public void testHeroCapturedByGuard() {
			Game g = new Game(1, map1,false,true,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("d");										// moves to an adjacent tile of a guard
			assertTrue(g.getHero().isDead());						// hero is dead
			assertFalse(g.gameRunning());							// game is over
		}

	
		@Test
		public void testHeroMoveClosedDoor() {
			Game g = new Game(1, map1, false, false,false,false, Personality.ROOKIE, 3);
			assertTrue(g.getDungeon().getTile(new Point(0,3)) instanceof Door);		//Point(0,3) is a door
			assertEquals(g.getDungeon().getTile(new Point(0,3)).toString(),'I'+"");	//Door is closed
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moving south
			assertEquals(new Point(1,2),g.getHero().getCoord());	// hero is on the south tile
			g.playerTurn("a");										// trying to move to closed door
			assertEquals(new Point(1,2),g.getHero().getCoord());	// hero is still on the same tile
			assertTrue(g.gameRunning());							// game is still running
		}
		
		@Test
		public void testHeroOpenDoors() {
			Game g = new Game(1, map1, false, false,false,false, Personality.ROOKIE, 3);
			assertTrue(g.getDungeon().getTile(new Point(0,3)) instanceof Door);		//Point(0,3) is a door
			assertEquals(g.getDungeon().getTile(new Point(0,3)).toString(),'I'+"");	//Door is closed
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moving south
			g.playerTurn("s");										// moving to lever tile
			assertEquals(new Point(1,3),g.getHero().getCoord());	// hero is on lever tile
			assertEquals(g.getDungeon().getTile(new Point(0,3)).toString(),'S'+"");	// door is opened
			assertEquals(g.getDungeon().getTile(new Point(0,2)).toString(),'S'+"");	// other door is opened
		}
		
		@Test
		public void testHeroFinishesLevel() {
			Game g = new Game(1, map1, false, false,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");		// moving south
			g.playerTurn("s");		// hero is on lever tile
			g.playerTurn("a");		// hero moved to exit
			assertFalse(g.gameRunning());	// game stopped
			assertFalse(g.getHero().isDead());	// hero is alive
		}
		
		@Test
		public void testHeroMoveAdjOgre(){
			Game g = new Game(2, map2, false, true,false,false, Personality.ROOKIE, 3);
			assertEquals(g.getHero().getState(), State.ARMED);			// hero is armed
			assertEquals(g.getHero().toString(), 'A'+"");					// hero has A symbol
			assertEquals(new Point(2,4), g.getHero().getCoord());		// beginning position for the hero
			g.playerTurn("w");		//moving north
			g.playerTurn("w");		//moving to adjacent of ogre
			assertEquals(new Point(2,2), g.getHero().getCoord());		//final position of the hero
			assertTrue(g.getHero().isDead());		//hero died
			assertTrue(!g.gameRunning());			//game stopped running
		}
		
		@Test
		public void testHeroMoveKey(){
			Game g = new Game(2, map2, false, false ,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(2,4), g.getHero().getCoord());		// beginning position for the hero
			g.playerTurn("d"); //moving to key tile
			assertEquals(g.getHero().toString(),'K'+"");		//hero symbol is K
			assertTrue(g.getHero().getKey());				//hero has key
		}
		
		@Test
		public void testHeroMoveExitNoKey(){
			Game g = new Game(2, map2, false, false ,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(2,4), g.getHero().getCoord());		// beginning position for the hero
			g.playerTurn("a");		//move east
			assertEquals(new Point(1,4), g.getHero().getCoord());		// hero next to an exit
			assertEquals(g.getDungeon().getTile(new Point(0,4)).toString(),'I'+"");	// door is closed
			g.playerTurn("a");		//move against exit (without key)
			assertEquals(new Point(1,4), g.getHero().getCoord());		// hero same position
			assertEquals(g.getDungeon().getTile(new Point(0,4)).toString(),'I'+"");	// door is still closed
			assertTrue(g.gameRunning()); //game is still running
		}
		
		@Test
		public void testHeroMoveExitWithKey(){
			Game g = new Game(2, map2, false, false ,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(2,4), g.getHero().getCoord());		// beginning position for the hero
			g.playerTurn("d");		//move west (takes key)
			g.playerTurn("a");		//move east
			g.playerTurn("a");		//move east
			assertEquals(new Point(1,4), g.getHero().getCoord());		// hero next to an exit
			g.playerTurn("a");		//move against exit (with key)
			assertEquals(new Point(1,4), g.getHero().getCoord());		// hero same position
			assertEquals(g.getDungeon().getTile(new Point(0,4)).toString(),'S'+"");	// door is opened
			assertTrue(g.gameRunning()); //game is still running
		}
		
		@Test
		public void testHeroEndsKeep(){
			Game g = new Game(2, map2, false, false ,false,false, Personality.ROOKIE, 3);
			assertEquals(new Point(2,4), g.getHero().getCoord());		// beginning position for the hero
			g.playerTurn("d");		//move west (takes key)
			g.playerTurn("a");		//move east
			g.playerTurn("a");		//move east
			g.playerTurn("a");		//move against exit (with key)
			assertEquals(g.getDungeon().getTile(new Point(0,4)).toString(),'S'+"");	// door is opened
			g.playerTurn("a");		//move to exit
			assertEquals(g.getHero().getCoord(), new Point(0,4));	//hero at the exit tile
			assertFalse(g.getHero().isDead());	//hero is alive
			assertFalse(g.gameRunning()); //game is not running
		}
		
		@Test(timeout=1000)
		public void testOgreRandomMovement(){
			boolean moveLeft = false, moveRight = false, moveDown = false, moveUp = false;
			while(!moveLeft || !moveRight || !moveDown || !moveUp)
			{
				Game g = new Game(2,map2,true, false, false, false, Personality.ROOKIE, 3);
				ArrayList<Character> ogres = g.getNpcs();
				for(int i = 0; i<ogres.size(); i++)
				{
					assertEquals(new Point(2,1), ogres.get(i).getCoord());	//all ogres start in the same spot
				}
				g.playerTurn("s");	//hero moves against a wall (turn passes)
				for(int i = 0; i<ogres.size(); i++)
				{
					Point ogreCoord = ogres.get(i).getCoord();
					if(ogreCoord.equals(new Point(1,1)))	//at least 1 ogre moved left
					{
						moveLeft = true;
					}
					else if(ogreCoord.equals(new Point(3,1)))	//at least 1 ogre moved right
					{
						moveRight = true;
					}
					else if(ogreCoord.equals(new Point(2,2)))	//at least 1 ogre moved down
					{
						moveDown = true;
					}
					else if(ogreCoord.equals(new Point(2,1)))	////at least 1 ogre moved up (against a wall)
					{
						moveUp = true;
					}
					else
					{
						fail("invalid movement");
					}
				}
			}
			assertTrue(moveLeft && moveRight && moveUp && moveDown);	// ogres can move anywhere
		}
		
		@Test(timeout=1000)
		public void testOgreRandomAttack(){
			boolean attackLeft = false, attackUp = false, attackDown = false, attackRight= false;
			while(!attackLeft || !attackUp || !attackDown || !attackRight)
			{
				Game g = new Game(2,map2,false, true, true, false, Personality.ROOKIE, 3);	//game has enemies attacking and with weapons (but not moving)
				ArrayList<Character> ogres = g.getNpcs();	
				g.playerTurn("s");	//hero moves against a wall (turn passes)
				for(int i = 0; i<ogres.size(); i++)
				{
					Point weaponCoord = ogres.get(i).getWeapon().getCoord();
					if(weaponCoord.equals(new Point(1,1)))	//at least 1 ogre attacked left
					{
						attackLeft = true;
					}
					else if(weaponCoord.equals(new Point(2,2)))	//at least 1 ogre attacked down
					{
						attackDown = true;
					}
					else if(weaponCoord.equals(new Point(2,0)))	//at least 1 ogre attacked up
					{
						attackUp = true;
					}
					else if(weaponCoord.equals(new Point(3,1)))	//at least 1 ogre attacked right
					{
						
						attackRight = true;
					}
					else
					{
						fail("invalid attack");
					}
				}				
			}
			assertTrue(attackLeft && attackUp && attackDown && attackRight);	// ogres can attack up, left and down
		}
		
		
		@Test(timeout=10000)
		public void testOgreAttackCollision(){
			boolean attackRight = false;
			while(!attackRight)
			{
				Game g = new Game(2,map2,false, true, true, false, Personality.ROOKIE, 3);	//game has enemies attacking and with weapons (but not moving)
				ArrayList<Character> ogres = g.getNpcs();		
				g.playerTurn("s");	//hero moves against a wall (turn passes)
				for(int i = 0; i<ogres.size(); i++)
				{
					Point weaponCoord = ogres.get(i).getWeapon().getCoord();
					if(weaponCoord.equals(new Point(3,1)))	//at least 1 ogre attacked right
					{
						attackRight = true;
						System.out.println(g.getDungeon().printDungeonString(g.getAllObjects()));
						assertEquals(g.getDungeon().printDungeonString(g.getAllObjects()).charAt(17),'$');
						break;
					}
				}				
			}
		}
}
