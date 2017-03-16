package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.logic.Game;
import dkeep.logic.GameState;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GraphicsInterface {

	private JFrame frmDungeonKeep;
	private IntegerJTextField textFieldOgreQuantity;
	
	private JButton btnMoveUp = new JButton("W");
	private JButton btnMoveLeft = new JButton("A");
	private JButton btnMoveRight = new JButton("D");
	private JButton btnMoveDown = new JButton("S");
	private JLabel lblGameState = new JLabel("You can start a new game.");
	private JTextArea textAreaGameState = new JTextArea();
	private JComboBox comboBoxGuardPersonality = new JComboBox();

	Game game;
	
	/**
	 * Launch the application.
	 */
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsInterface window = new GraphicsInterface();
					window.frmDungeonKeep.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 1080, 720);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel ogresQuantity = new JLabel("Number of Ogres");
		ogresQuantity.setFont(new Font("Calibri", Font.BOLD, 14));
		ogresQuantity.setBounds(50, 50, 120, 20);
		frmDungeonKeep.getContentPane().add(ogresQuantity);
		
		JLabel guardPersonality = new JLabel("Guard Personality");
		guardPersonality.setFont(new Font("Calibri", Font.BOLD, 14));
		guardPersonality.setBounds(50, 85, 120, 20);
		frmDungeonKeep.getContentPane().add(guardPersonality);
		
		textFieldOgreQuantity = new IntegerJTextField();
		textFieldOgreQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOgreQuantity.setFont(new Font("Calibri", Font.PLAIN, 14));
		textFieldOgreQuantity.setText("3");
		textFieldOgreQuantity.setBounds(180, 50, 30, 20);
		frmDungeonKeep.getContentPane().add(textFieldOgreQuantity);
		textFieldOgreQuantity.setColumns(10);
		
		comboBoxGuardPersonality.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxGuardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBoxGuardPersonality.setBounds(180, 85, 100, 20);
		frmDungeonKeep.getContentPane().add(comboBoxGuardPersonality);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnExit.setBounds(800, 570, 200, 50);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		lblGameState.setFont(new Font("Calibri", Font.BOLD, 14));
		lblGameState.setBounds(50, 630, 200, 20);
		frmDungeonKeep.getContentPane().add(lblGameState);
		
		
		textAreaGameState.setFont(new Font("Consolas", Font.BOLD, 20));
		textAreaGameState.setEditable(false);
		textAreaGameState.setBounds(50, 120, 700, 500);
		frmDungeonKeep.getContentPane().add(textAreaGameState);
		
		
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame("w");
			}
		});
		btnMoveUp.setEnabled(false);
		btnMoveUp.setBounds(875, 280, 50, 25);
		frmDungeonKeep.getContentPane().add(btnMoveUp);
		
		
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame("a");
			}
		});
		btnMoveLeft.setEnabled(false);
		btnMoveLeft.setBounds(800, 360, 50, 25);
		frmDungeonKeep.getContentPane().add(btnMoveLeft);
		
		
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame("d");
			}
		});
		btnMoveRight.setEnabled(false);
		btnMoveRight.setBounds(950, 360, 50, 25);
		frmDungeonKeep.getContentPane().add(btnMoveRight);
		
		
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame("s");
			}
		});
		btnMoveDown.setEnabled(false);
		btnMoveDown.setBounds(875, 440, 50, 25);
		frmDungeonKeep.getContentPane().add(btnMoveDown);
		
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Game(new GameState(1,Integer.parseInt(textFieldOgreQuantity.getText()),comboBoxGuardPersonality.getSelectedIndex()));
				textAreaGameState.setText(game.printDungeonString());
				updateButtons(true);
				lblGameState.setText("Level 1.");
			}
		});
		btnNewGame.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewGame.setBounds(800, 120, 200, 50);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
	}
	
	private void updateButtons(boolean state)
	{
		btnMoveUp.setEnabled(state);
		btnMoveLeft.setEnabled(state);
		btnMoveRight.setEnabled(state);
		btnMoveDown.setEnabled(state);
	}
	
	private void updateGame(String action)
	{
		game.playerTurn(action);
		textAreaGameState.setText(game.printDungeonString());
		if(game.getGameState().running == false)
		{
			updateButtons(false);
			if(game.getHero().isDead())
			{
				lblGameState.setText("Game Over! You Lose!");
			}
			else if(game.getGameState().currentLevel == 2)
			{
				lblGameState.setText("Game Over! You Win!");
			}
			else 
			{
				lblGameState.setText("Level 2");
				game = new Game(new GameState(2));
				textAreaGameState.setText(game.printDungeonString());
				updateButtons(true);
			}
		}		
	}	
}
