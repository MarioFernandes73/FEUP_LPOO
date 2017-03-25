package dkeep.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
	
	// GAME STATES
	public BufferedImage background;
	public BufferedImage youWin;
	public BufferedImage youLose;
	
	// BUTTONS IMAGE
	public BufferedImage newGame;
	public BufferedImage createGame;
	public BufferedImage saveLoad;
	public BufferedImage options;
	public BufferedImage exit;
	
	// GAME ELEMENTS IMAGE
	public BufferedImage empty;
	public BufferedImage wall;
	public BufferedImage normalHero;
	public BufferedImage heroArmed;
	public BufferedImage heroCarryingKey;
	public BufferedImage guard;
	public BufferedImage guardStunned;
	//public BufferedImage heroArmedKey;      //os que estao comentados, julgo nao serem precisos
	//public BufferedImage heroDead;
	//public BufferedImage guardRookie;
	//public BufferedImage guardDrunken;
	//public BufferedImage guardSleep;
	//public BufferedImage guardSuspicious;
	//public BufferedImage normalDoorClosed;
	//public BufferedImage normalDoorOpened;
	//public BufferedImage exitDoorOpened;
	//public BufferedImage exitDoorClosed;
	//public BufferedImage lever;
	public BufferedImage openDoor;
	public BufferedImage closedDoor;
	public BufferedImage key;
	public BufferedImage obstructedKey;
	public BufferedImage ogre;
	public BufferedImage ogreStunned;
	public BufferedImage club;
	//public BufferedImage clubKey;
	
	public Assets() {
		try {
			
		// GAME STATES
		background = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
		youWin = ImageIO.read(getClass().getResource("resources/game/youWin.png"));
		youLose = ImageIO.read(getClass().getResource("resources/game/youLose.png"));
		
		// BUTTONS
		newGame = ImageIO.read(getClass().getResource("resources/menu/newGame.png"));
		createGame = ImageIO.read(getClass().getResource("resources/menu/createGame.png"));
		saveLoad = ImageIO.read(getClass().getResource("resources/menu/saveLoad.png"));
		options = ImageIO.read(getClass().getResource("resources/menu/options.png"));
		exit = ImageIO.read(getClass().getResource("resources/menu/exit.png"));
		
		// GAME ELEMENTS
		empty = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
		wall = ImageIO.read(getClass().getResource("resources/game/wall.png"));
		normalHero = ImageIO.read(getClass().getResource("resources/game/normalHero.png"));
		heroArmed = ImageIO.read(getClass().getResource("resources/game/heroArmed.png"));
		heroCarryingKey = ImageIO.read(getClass().getResource("resources/game/heroCarryingKey.png"));
		guard = ImageIO.read(getClass().getResource("resources/game/guard.png"));
		guardStunned = ImageIO.read(getClass().getResource("resources/game/guardStunned.png"));
		//heroArmedKey = ImageIO.read(getClass().getResource("resources/game/heroArmedKey.jpg"));
		//heroDead = ImageIO.read(getClass().getResource("resources/game/heroDead.jpg"));
		//guardRookie = ImageIO.read(getClass().getResource("resources/game/guardRookie.jpg"));
		//guardDrunken = ImageIO.read(getClass().getResource("resources/game/guardDrunken.jpg"));
		//guardSleep = ImageIO.read(getClass().getResource("resources/game/guardSleep.jpg"));
		//guardSuspicious = ImageIO.read(getClass().getResource("resources/game/guardSuspicious.jpg"));*/
		//normalDoorClosed = ImageIO.read(getClass().getResource("resources/game/normalDoorClosed.jpg"));
		//normalDoorOpened = ImageIO.read(getClass().getResource("resources/game/normalDoorOpened.jpg"));
		//exitDoorOpened = ImageIO.read(getClass().getResource("resources/game/exitDoorOpened.jpg"));
		//exitDoorClosed = ImageIO.read(getClass().getResource("resources/game/exitDoorClosed.jpg"));
		//lever = ImageIO.read(getClass().getResource("resources/game/lever.jpg"));
		openDoor = ImageIO.read(getClass().getResource("resources/game/openDoor.png"));
		closedDoor = ImageIO.read(getClass().getResource("resources/game/closedDoor.png"));
		key = ImageIO.read(getClass().getResource("resources/game/key.png"));
		obstructedKey = ImageIO.read(getClass().getResource("resources/game/obstructedKey.png"));
		ogre = ImageIO.read(getClass().getResource("resources/game/ogre.png"));
		ogreStunned = ImageIO.read(getClass().getResource("resources/game/ogreStunned.png"));
		club = ImageIO.read(getClass().getResource("resources/game/club.png"));
		//clubKey = ImageIO.read(getClass().getResource("resources/game/clubKey.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
