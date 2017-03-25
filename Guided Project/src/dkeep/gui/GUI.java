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
	private JPanel panelElements;
	private PanelGame panelGame;
	private PanelCreateGame panelCreateGame;
	
	private DialogSaveLoad dialogSaveLoad;
	private JDialog dialogConfig;
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
	private JSplitPaneCustom pane;
	
	private Assets gameImages; //Contains all used images
	
	/**
	 * Create the frame.
	 */
	public GUI(String title) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.dialogConfig = new Config();
		this.dialogConfig.setLayout(new GridLayout(4,1));
	//	this.dialogCreateGame = new DialogCreateGame();
		this.gameImages = new Assets();

		this.panelMenu = new JPanel(new GridLayout(2,3));
		this.panelMoveButtons = new JPanel(new GridLayout(4,1));
			
		createButtons();
		createMoveButtons();
		
		this.labelGameState = new JLabel();
		this.labelGameState.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelMenu.add(labelGameState);
		
		
		//this.panelCreateGame = new PanelCreateGame(gameImages, labelGameState);
		
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
				GUI.this.panelGame.setGame(null);
				GUI.this.panelGame.setGame(new Game(new GameState(1)));
				GUI.this.buttonCreateGame.setEnabled(false);
				GUI.this.enableMoveButtons(true);
			}
		});

		this.buttonCreateGame = new JButtonCustom(gameImages.createGame);
		
		
		this.buttonSaveLoadGame = new JButtonCustom(gameImages.saveLoad);
		this.buttonSaveLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUI.this.panelGame.getGame() !=  null)
					GUI.this.dialogSaveLoad.saveGame(GUI.this.panelGame.getGame());
				GUI.this.dialogSaveLoad.setVisible(true);
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
				}
			}
		});
		
		panelMenu.add(buttonNewGame);
		panelMenu.add(buttonCreateGame);
		panelMenu.add(buttonSaveLoadGame);
		panelMenu.add(buttonOptions);
		panelMenu.add(buttonQuit);
	}
	
	
}
