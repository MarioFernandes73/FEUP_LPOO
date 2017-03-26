package dkeep.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import dkeep.logic.Game;
import dkeep.logic.GameState;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panelMenu;
	private JPanel panelMoveButtons;
	private PanelGame panelGame;
	
	private DialogSaveLoad dialogSaveLoad;
	private Config dialogConfig;
	private JDialog dialogCreateGame;
	private JLabel labelGameState;
	
	private JButtonCustom buttonNewGame;
	private JButtonCustom buttonCreateGame;
	private JButtonCustom buttonSaveLoadGame;
	private JButtonCustom buttonOptions;
	private JButtonCustom buttonQuit;
	
	private JButton buttonMoveUp;
	private JButton buttonMoveLeft;
	private JButton buttonMoveRight;
	private JButton buttonMoveDown;
	
	private JSplitPaneCustom gamePane;
	private JSplitPane pane;
	private JDialog createPane;
	
	private int loadState = 0;
	
	private Assets gameImages; //Contains all used images
	
	/**
	 * Create the frame.
	 */
	public GUI(String title) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.dialogConfig = new Config();
		this.dialogCreateGame = new JDialog();
		createDialogCreateGame();
		this.gameImages = new Assets();

		this.panelMenu = new JPanel(new GridLayout(2,3));
		this.panelMoveButtons = new JPanel(new GridLayout(4,1));
			
		createButtons();
		createMoveButtons();
		
		this.labelGameState = new JLabel();
		this.labelGameState.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelMenu.add(labelGameState);
		
		this.panelGame = new PanelGame(gameImages, labelGameState);
		this.dialogSaveLoad = new DialogSaveLoad(panelGame.getGame());	
		
		this.gamePane = new JSplitPaneCustom(JSplitPane.HORIZONTAL_SPLIT, panelGame, panelMoveButtons,0.9);;
		
		this.pane = new JSplitPaneCustom(JSplitPane.VERTICAL_SPLIT, panelMenu, gamePane, 0.3);
		this.pane.setVisible(true);
		getContentPane().add(pane);

	}
	
	private void enableMoveButtons(boolean state)
	{
		this.buttonMoveUp.setEnabled(state);
		this.buttonMoveLeft.setEnabled(state);
		this.buttonMoveRight.setEnabled(state);
		this.buttonMoveDown.setEnabled(state);
	}
	
	private void createMoveButtons()
	{
		this.buttonMoveUp = new JButton("UP");
		this.buttonMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.moveHero("w");
			}
		});
		
		this.buttonMoveLeft = new JButton("LEFT");
		this.buttonMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.moveHero("a");
			}
		});
		
		this.buttonMoveRight = new JButton("RIGHT");
		this.buttonMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.moveHero("d");
			}
		});
		
		this.buttonMoveDown = new JButton("DOWN");
		this.buttonMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.moveHero("s");
			}
		});
		
		enableMoveButtons(false);
		
		this.panelMoveButtons.add(buttonMoveUp);
		this.panelMoveButtons.add(buttonMoveLeft);
		this.panelMoveButtons.add(buttonMoveRight);
		this.panelMoveButtons.add(buttonMoveDown);
	}
	
	private void createButtons()
	{
		this.buttonNewGame = new JButtonCustom(gameImages.newGame);
		this.buttonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.loadState = GUI.this.dialogSaveLoad.getLoaded();
				if(loadState == 1)
				{					
					GUI.this.panelGame.setGame(GUI.this.dialogSaveLoad.loadGame());
				}
				else if(loadState == 2)
				{
					GameState state = new GameState(GUI.this.dialogSaveLoad.getCustomKeep(),1);
					state.movingEnemies = GUI.this.dialogConfig.getMovingEnemies();
					state.attackingEnemies = GUI.this.dialogConfig.getAttackingEnemies();
					state.ogreQuantity = GUI.this.dialogConfig.getOgreQuantity();
					state.currentLevel = 2;
					GUI.this.panelGame.setGame(new Game(state));
				}
				else
				{
					int ogreQuantity = GUI.this.dialogConfig.getOgreQuantity();
					int guardPersonality = GUI.this.dialogConfig.getGuardPersonality();
					GUI.this.panelGame.setGame(new Game(new GameState(1,ogreQuantity, guardPersonality)));
					GUI.this.panelGame.getGame().getGameState().movingEnemies = GUI.this.dialogConfig.getMovingEnemies();
					GUI.this.panelGame.getGame().getGameState().attackingEnemies = GUI.this.dialogConfig.getAttackingEnemies();
					
				}
				GUI.this.buttonCreateGame.setEnabled(false);
				GUI.this.enableMoveButtons(true);
			}
		});

		this.buttonCreateGame = new JButtonCustom(gameImages.createGame);
		this.buttonCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.dialogCreateGame.setVisible(true);
			}
		});
		
		this.buttonSaveLoadGame = new JButtonCustom(gameImages.saveLoad);
		this.buttonSaveLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUI.this.panelGame.getGame() !=  null)
					GUI.this.dialogSaveLoad.saveGame(GUI.this.panelGame.getGame());
				GUI.this.dialogSaveLoad.setVisible(true);
				GUI.this.loadState = 0;
			}
		});
		
		this.buttonOptions = new JButtonCustom(gameImages.options);
		this.buttonOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.dialogConfig.setVisible(true);
			}
		});
		
		this.buttonQuit = new JButtonCustom(gameImages.exit);
		this.buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUI.this.panelGame.getGame() ==  null)
					System.exit(0);
				else
				{
					GUI.this.panelGame.setGame(null);
					GUI.this.buttonCreateGame.setEnabled(true);
					GUI.this.enableMoveButtons(false);
					GUI.this.loadState = 0;
				}
			}
		});
		
		panelMenu.add(buttonNewGame);
		panelMenu.add(buttonCreateGame);
		panelMenu.add(buttonSaveLoadGame);
		panelMenu.add(buttonOptions);
		panelMenu.add(buttonQuit);
	}
	
	public JPanel getGamePanel()
	{
		return this.panelGame;
	}
	
	public void createDialogCreateGame()
	{
		this.dialogCreateGame.setSize(250, 250);
		this.dialogCreateGame.setLayout(null);
		this.dialogCreateGame.setResizable(false);
		
		JLabel labelWidth = new JLabel("Width");
		labelWidth.setBounds(50,50,50,25);
		labelWidth.setVisible(true);
		
		JLabel labelHeight = new JLabel("Height");
		labelHeight.setBounds(50,100,50,25);
		labelHeight.setVisible(true);
		
		IntegerJTextField textWidth = new IntegerJTextField(5,15);
		textWidth.setText("10");
		textWidth.setBounds(150,50,50,25);
		textWidth.setVisible(true);
		
		IntegerJTextField textHeight = new IntegerJTextField(5,15);
		textHeight.setText("10");
		textHeight.setBounds(150,100,50,25);
		textHeight.setVisible(true);
		
		JButton buttonCreate = new JButton();
		buttonCreate.setText("Create Keep");
		buttonCreate.setBounds(50, 150, 150, 25);
		buttonCreate.setVisible(true);
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.createPane = new PaneCreateGame(gameImages,GUI.this.labelGameState, Integer.parseInt(textWidth.getText()),Integer.parseInt(textHeight.getText()));
				GUI.this.dialogCreateGame.dispose();
			}
		});
		
		this.dialogCreateGame.add(labelWidth);
		this.dialogCreateGame.add(labelHeight);
		this.dialogCreateGame.add(textWidth);
		this.dialogCreateGame.add(textHeight);
		this.dialogCreateGame.add(buttonCreate);
	}
	
}
