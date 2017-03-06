package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic {
	
	// Point class (x,y) x-> horizontal direction, y-> vertical direction
	
	
		int[][] mapNoEnemies = {{1,1,1,1,1},
								{1,2,0,0,1},
								{7,0,0,0,1},
								{7,8,0,0,1},
								{1,1,1,1,1}};
		
		int[][] map = {{1,1,1,1,1},
						{1,2,0,3,1},
						{7,0,0,0,1},
						{7,8,0,0,1},
						{1,1,1,1,1}};
		
		@Test
		public void testHeroMovementFreeCell() {
			Game g = new Game(1, mapNoEnemies);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moves to an empty tile (down)
			assertEquals(new Point(1,2), g.getHero().getCoord());	// ending position for the hero
		}
		
		@Test
		public void testHeroMovementWall() {
			Game g = new Game(1, mapNoEnemies);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("w");										// moves to a wall tile (up)
			assertEquals(new Point(1,1), g.getHero().getCoord());	// ending position for the hero
		}
		
		@Test
		public void testHeroCapturedByGuard() {
			Game g = new Game(1, map);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("d");										// moves to an adjacent tile of a guard
			assertTrue(g.getHero().isDead());						// hero is dead
			assertFalse(g.gameRunning());							// game is over
		}

		
		@Test
		public void testHeroMoveClosedDoor() {
			Game g = new Game(1, mapNoEnemies);
			assertTrue(g.getDungeon().getTile(new Point(0,3)) instanceof Door);		//Point(0,3) is a door
			assertEquals(g.getDungeon().getTile(new Point(0,3)).getSymbol(),'S');	//Door is closed
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moving south
			assertEquals(new Point(1,2),g.getHero().getCoord());	// hero is on the south tile
			g.playerTurn("a");										// trying to move to closed door
			assertEquals(new Point(1,2),g.getHero().getCoord());	// hero is still on the same tile
			assertTrue(g.gameRunning());							// game is still running
		}
		
		@Test
		public void testHeroOpenDoors() {
			Game g = new Game(1, mapNoEnemies);
			assertTrue(g.getDungeon().getTile(new Point(0,3)) instanceof Door);		//Point(0,3) is a door
			assertEquals(g.getDungeon().getTile(new Point(0,3)).getSymbol(),'S');	//Door is closed
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");										// moving south
			g.playerTurn("s");										// moving to lever tile
			assertEquals(new Point(1,3),g.getHero().getCoord());	// hero is on lever tile
			assertEquals(g.getDungeon().getTile(new Point(0,3)).getSymbol(),'S');	// door is opened
			assertEquals(g.getDungeon().getTile(new Point(0,2)).getSymbol(),'S');	// other door is opened
		}
		
		@Test
		public void testHeroFinishesLevel() {
			Game g = new Game(1, mapNoEnemies);
			assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
			g.playerTurn("s");		// moving south
			g.playerTurn("s");		// hero is on lever tile
			g.playerTurn("a");		// hero moved to exit
			assertFalse(g.gameRunning());	// game stopped
			assertFalse(g.getHero().isDead());	// hero is alive
		}
}
