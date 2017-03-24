package dkeep.gui;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import dkeep.logic.Game;
import dkeep.logic.GameState;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelMenu;
	private PanelGame panelGame;
	private JButtonCustom buttonNewGame;
	private JButtonCustom buttonCreateGame;
	private JButtonCustom buttonSaveGame;
	private JButtonCustom buttonLoadGame;	
	private JButtonCustom buttonOptions;
	private JButtonCustom buttonQuit;
	
	private JSplitPaneCustom pane;
	
	private Assets gameImages; //Contains all used images
	
	/**
	 * Create the frame.
	 */
	public GUI(String title) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.gameImages = new Assets();
		this.panelMenu = new JPanel(new GridLayout(2,3));
			
		this.createButtons();
		
		this.panelGame = new PanelGame(gameImages);

		pane = new JSplitPaneCustom(JSplitPane.VERTICAL_SPLIT, panelMenu, panelGame);
		pane.setVisible(true);
		getContentPane().add(pane);
	}
	
	private void createButtons()
	{
		this.buttonNewGame = new JButtonCustom(gameImages.newGame);
		buttonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.setGame(new Game(new GameState(1)));
			}
		});
		this.buttonCreateGame = new JButtonCustom(gameImages.newGame);
		this.buttonSaveGame = new JButtonCustom(gameImages.newGame);
		this.buttonLoadGame = new JButtonCustom(gameImages.newGame);
		this.buttonOptions = new JButtonCustom(gameImages.newGame);
		this.buttonQuit = new JButtonCustom(gameImages.newGame);
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panelMenu.add(buttonNewGame);
		panelMenu.add(buttonCreateGame);
		panelMenu.add(buttonSaveGame);
		panelMenu.add(buttonLoadGame);
		panelMenu.add(buttonOptions);
		panelMenu.add(buttonQuit);
	}
	
	
}
