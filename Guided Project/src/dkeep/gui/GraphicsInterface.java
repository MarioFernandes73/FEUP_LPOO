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
		int currentLevel = 2;
		final int maximumLevels = 2;
		int[][] dungeonModel = null;
		
		if(currentLevel == 1)
		{
			dungeonModel = defaultDungeon1;
		}
		else
		{
			dungeonModel = defaultDungeon2;
		}
		
		Game game = new Game(currentLevel, dungeonModel);
		
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		
		while(running)
		{
			System.out.println(game.printDungeonString());
			System.out.print("Choose the direction to move your hero (w a s d): ");
			String heroMovement = sc.nextLine();
			System.out.println("");
			if(heroMovement.equals("w") || heroMovement.equals("a") || heroMovement.equals("s") || heroMovement.equals("d") )
			{
				running = initialize(); //initialized keeps drawing the window interface and keeping track of the listeners
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
						game = new Game(currentLevel, dungeonModel);						
					}
				}
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		btnS.setBounds(WINDOW_WIDTH/2-BUTTON_WIDTH/2, BUTTON_W_Y+2*BUTTON_HEIGHT+2*VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
		frmDungeonkeep.getContentPane().add(btnS);
		// D
		JButton btnD = new JButton("D");
		btnD.setBounds(2*WINDOW_WIDTH/3-BUTTON_WIDTH/2, BUTTON_W_Y+BUTTON_HEIGHT+VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
		frmDungeonkeep.getContentPane().add(btnD);
		// W
		JButton btnW = new JButton("W");
		btnW.setBounds(WINDOW_WIDTH/2-BUTTON_WIDTH/2, BUTTON_W_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		frmDungeonkeep.getContentPane().add(btnW);
		// A
		JButton btnA = new JButton("A");
		btnA.setBounds(WINDOW_WIDTH/3-BUTTON_WIDTH/2, BUTTON_W_Y+BUTTON_HEIGHT+VERTICAL_SPACE_BETWEEN_BUTTONS, BUTTON_WIDTH, BUTTON_HEIGHT);
		frmDungeonkeep.getContentPane().add(btnA);
		// NEW GAME
		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		btnNewGame.setBounds(365, 504, 98, 26);
		frmDungeonkeep.getContentPane().add(btnNewGame);
		
		
		
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
	}
}
