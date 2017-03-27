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
	public BufferedImage openDoor;
	public BufferedImage closedDoor;
	public BufferedImage key;
	public BufferedImage obstructedKey;
	public BufferedImage ogre;
	public BufferedImage ogreStunned;
	public BufferedImage club;
	
	public Assets() {

		
		loadGameStates();

		
		loadButtons();

		
		loadGameElements();

	}

	private void loadGameElements() {
		try {
		// GAME ELEMENTS
		empty = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
		wall = ImageIO.read(getClass().getResource("resources/game/wall.png"));
		normalHero = ImageIO.read(getClass().getResource("resources/game/normalHero.png"));
		heroArmed = ImageIO.read(getClass().getResource("resources/game/heroArmed.png"));
		heroCarryingKey = ImageIO.read(getClass().getResource("resources/game/heroCarryingKey.png"));
		guard = ImageIO.read(getClass().getResource("resources/game/guard.png"));
		guardStunned = ImageIO.read(getClass().getResource("resources/game/guardStunned.png"));
		openDoor = ImageIO.read(getClass().getResource("resources/game/openDoor.png"));
		closedDoor = ImageIO.read(getClass().getResource("resources/game/closedDoor.png"));
		key = ImageIO.read(getClass().getResource("resources/game/key.png"));
		obstructedKey = ImageIO.read(getClass().getResource("resources/game/obstructedKey.png"));
		ogre = ImageIO.read(getClass().getResource("resources/game/ogre.png"));
		ogreStunned = ImageIO.read(getClass().getResource("resources/game/ogreStunned.png"));
		club = ImageIO.read(getClass().getResource("resources/game/club.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadButtons() {
		try {
		// BUTTONS
		newGame = ImageIO.read(getClass().getResource("resources/menu/newGame.png"));
		createGame = ImageIO.read(getClass().getResource("resources/menu/createGame.png"));
		saveLoad = ImageIO.read(getClass().getResource("resources/menu/saveLoad.png"));
		options = ImageIO.read(getClass().getResource("resources/menu/options.png"));
		exit = ImageIO.read(getClass().getResource("resources/menu/exit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadGameStates() {
		try {
		// GAME STATES
		background = ImageIO.read(getClass().getResource("resources/game/empty.jpg"));
		youWin = ImageIO.read(getClass().getResource("resources/game/youWin.png"));
		youLose = ImageIO.read(getClass().getResource("resources/game/youLose.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
