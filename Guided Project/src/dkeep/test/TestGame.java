package dkeep.test;

import static org.junit.Assert.*;
import dkeep.logic.*;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Point;

public class TestGame {

	// Point class (x,y) x-> horizontal direction, y-> vertical direction
	
	@Test
	public void testHeroMovementFreeCell() {
		Game g = new Game(1);
		assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
		g.playerTurn("d");										// moves to an empty tile (right)
		assertEquals(new Point(2,1), g.getHero().getCoord());	// ending position for the hero
	}
	
	@Test
	public void testHeroMovementWall() {
		Game g = new Game(1);
		assertEquals(new Point(1,1), g.getHero().getCoord()); 	// beginning position for the hero
		g.playerTurn("w");										// moves to a wall tile (up)
		assertEquals(new Point(1,1), g.getHero().getCoord());	// ending position for the hero
	}

}
