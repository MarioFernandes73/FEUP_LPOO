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
	
	private BufferedImage newGame;
	
	/**
	 * Create the frame.
	 */
	public GUI(String title) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
		this.panelMenu = new JPanel(new GridLayout(2,3));
		try
		{
		this.newGame = ImageIO.read(getClass().getResource("resources/menu/newGame.jpg"));				
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
			
		this.createButtons();
		
		this.panelGame = new PanelGame(new Game(new GameState(1)));

		pane = new JSplitPaneCustom(JSplitPane.VERTICAL_SPLIT, panelMenu, panelGame);
		pane.setVisible(true);
		this.add(pane);
	}
	
	private void createButtons()
	{
		this.buttonNewGame = new JButtonCustom(newGame);
		this.buttonCreateGame = new JButtonCustom(newGame);
		this.buttonSaveGame = new JButtonCustom(newGame);
		this.buttonLoadGame = new JButtonCustom(newGame);
		this.buttonOptions = new JButtonCustom(newGame);
		this.buttonQuit = new JButtonCustom(newGame);
		
		panelMenu.add(buttonNewGame);
		panelMenu.add(buttonCreateGame);
		panelMenu.add(buttonSaveGame);
		panelMenu.add(buttonLoadGame);
		panelMenu.add(buttonOptions);
		panelMenu.add(buttonQuit);
	}
	
	
}
