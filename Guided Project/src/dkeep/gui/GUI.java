package dkeep.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
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
	private JDialog panelConfig;
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
	
		
		this.panelConfig = new Config();
		this.panelConfig.setLayout(new GridLayout(4,1));	
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
		
		this.panelGame = new PanelGame();
		
	
		
		pane = new JSplitPaneCustom(JSplitPane.VERTICAL_SPLIT, panelMenu, panelGame);
		pane.setVisible(true);
		getContentPane().add(pane);

	}
	
	private void createButtons()
	{
		this.buttonNewGame = new JButtonCustom(newGame);
		buttonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelGame.setGame(new Game(new GameState(1)));
			}
		});
		this.buttonCreateGame = new JButtonCustom(newGame);
		this.buttonSaveGame = new JButtonCustom(newGame);
		this.buttonLoadGame = new JButtonCustom(newGame);
		this.buttonOptions = new JButtonCustom(newGame);
		buttonOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.panelConfig.setVisible(true);
			}
		});
		this.buttonQuit = new JButtonCustom(newGame);
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
