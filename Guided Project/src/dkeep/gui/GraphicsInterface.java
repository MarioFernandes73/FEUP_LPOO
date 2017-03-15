package dkeep.gui;

import dkeep.logic.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import dkeep.logic.Game;
import dkeep.logic.Guard.Personality;
import java.awt.Font;

public class GraphicsInterface {

	private JFrame frmDungeonkeep;
	private JTextField textNumOgres;

	//Default Dungeon (used for early tests)
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


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsInterface window = new GraphicsInterface();
					window.frmDungeonkeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicsInterface() {
	/*	int currentLevel = 1;
		final int maximumLevels = 2;
		int ogreQuantity = 3;
		int[][] dungeonModel = null;
		Game game;
		GameState gameVariables;

		gameVariables.running = false;

		if(currentLevel == 1)
		{
			dungeonModel = defaultDungeon1;
		}
		else
		{
			dungeonModel = defaultDungeon2;
		}


		while(!gameVariables.running) {//we keep the game is this loop until the NEW GAME button is pressed
			initialize(gameVariables, game);
		}

		game = new Game(gameVariables); //add the right parameters later, the game will be created after new button is pressed

		while(gameVariables.running) {
			initialize(gameVariables, game);
			if(game.getHero().isDead())
			{
				break;
			}

			if(running == false)		// level has ended
			{
				boolean heroIsDead = game.getHero().isDead();
				if((currentLevel == maximumLevels) || heroIsDead)
				{
					break;		
				}
				else
				{
					running = true;
					currentLevel ++;
					dungeonModel = defaultDungeon2;
					game = new Game(currentLevel, dungeonModel, true, true, true, true, Personality.ROOKIE, 3);						
				}
			}
		}
	}

	System.out.println(game.printDungeonString());
	System.out.println("GAME OVER!");*/
}

/**
 * Initialize the contents of the frame.
 */
private void initialize(GameState gameVariables, Game game) {

	int WINDOW_WIDTH = 600;
	int WINDOW_HEIGHT = 600;

	frmDungeonkeep = new JFrame();
	frmDungeonkeep.getContentPane().setBackground(new Color(204, 204, 255));
	frmDungeonkeep.setBackground(Color.WHITE);
	frmDungeonkeep.getContentPane().setForeground(Color.WHITE);
	frmDungeonkeep.setTitle("Dungeon Keep");
	frmDungeonkeep.setBounds(350, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
	frmDungeonkeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmDungeonkeep.getContentPane().setLayout(null);

	int BUTTON_WIDTH = 70;
	int BUTTON_HEIGHT = 40;
	int BUTTON_W_Y = 350;
	int VERTICAL_SPACE_BETWEEN_BUTTONS = 10;

	// Buttons
	// S
	JButton btnS = new JButton("S");
	btnS.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(gameVariables.running)
				game.playerTurn("s");
		}
	});
	btnS.setBounds(WINDOW_WIDTH/2-BUTTON_WIDTH/2, BUTTON_W_Y+2*BUTTON_HEIGHT+2*VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
	frmDungeonkeep.getContentPane().add(btnS);

	// D
	JButton btnD = new JButton("D");
	btnS.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(gameVariables.running)
				game.playerTurn("d");
		}
	});
	btnD.setBounds(2*WINDOW_WIDTH/3-BUTTON_WIDTH/2, BUTTON_W_Y+BUTTON_HEIGHT+VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
	frmDungeonkeep.getContentPane().add(btnD);

	// W
	JButton btnW = new JButton("W");
	btnS.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(gameVariables.running)
				game.playerTurn("w");
		}
	});
	btnW.setBounds(WINDOW_WIDTH/2-BUTTON_WIDTH/2, BUTTON_W_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
	frmDungeonkeep.getContentPane().add(btnW);

	// A
	JButton btnA = new JButton("A");
	btnS.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(gameVariables.running)
				game.playerTurn("a");
		}
	});
	btnA.setBounds(WINDOW_WIDTH/3-BUTTON_WIDTH/2, BUTTON_W_Y+BUTTON_HEIGHT+VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
	frmDungeonkeep.getContentPane().add(btnA);



	// EXIT
	JButton btnExit = new JButton("EXIT");
	btnExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	btnExit.setBounds(137, 504, 98, 26);
	frmDungeonkeep.getContentPane().add(btnExit);


	textNumOgres = new JTextField();
	textNumOgres.setText("3");// 3 is the predefined number of ogres
	textNumOgres.setBounds(177, 37, 43, 20);
	frmDungeonkeep.getContentPane().add(textNumOgres);
	textNumOgres.setColumns(10);

	JLabel lblNumOgres = new JLabel("Number of Ogres");
	lblNumOgres.setBounds(75, 39, 102, 16);
	frmDungeonkeep.getContentPane().add(lblNumOgres);

	JTextArea txtrDungeonDisplay = new JTextArea();
	txtrDungeonDisplay.setFont(new Font("Courier New", Font.PLAIN, 16));
	txtrDungeonDisplay.setEditable(false);
	txtrDungeonDisplay.setBackground(Color.WHITE);
	txtrDungeonDisplay.setBounds(106, 92, 383, 211);
	txtrDungeonDisplay.setText(game.toString());
	frmDungeonkeep.getContentPane().add(txtrDungeonDisplay);


	JComboBox comboBoxPersonality = new JComboBox();
	comboBoxPersonality.setModel(new DefaultComboBoxModel(Personality.values()));
	comboBoxPersonality.setSelectedIndex(0);
	comboBoxPersonality.setMaximumRowCount(3);
	comboBoxPersonality.setBounds(398, 37, 102, 20);
	frmDungeonkeep.getContentPane().add(comboBoxPersonality);

	JLabel lblPersonality = new JLabel("Guard Personality");
	lblPersonality.setBounds(291, 39, 102, 16);
	frmDungeonkeep.getContentPane().add(lblPersonality);

	JLabel lblGameStatus = new JLabel("SampleText.");
	lblGameStatus.setBounds(105, 305, 146, 16);
	frmDungeonkeep.getContentPane().add(lblGameStatus);

	// NEW GAME
	JButton btnNewGame = new JButton("NEW GAME");
	btnNewGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				int number;
				if(!gameVariables.running && (number = Integer.parseInt(textNumOgres.getText())) > 0) { // if the number of ogres is valid, we can start a new game
					gameVariables.ogreQuantity = number;
					gameVariables.guardPersonality = (Personality)comboBoxPersonality.getSelectedItem();
					gameVariables.running = true;
				}
			}
			catch(NumberFormatException nfe) {//this exception will be thrown if the text is ogre quantity box is not a number
				lblGameStatus.setText("The ogre quantity field only recognizes numbers, please try again");
			}
		}
	});
	btnNewGame.setBounds(365, 504, 98, 26);
	frmDungeonkeep.getContentPane().add(btnNewGame);
}
}
