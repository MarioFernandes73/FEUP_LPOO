package dkeep.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PaneCreateGame extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButtonCustom buttonEmpty;
	private JButtonCustom buttonWall;
	private JButtonCustom buttonHero;
	private JButtonCustom buttonOgre;
	private JButtonCustom buttonKey;
	private JButtonCustom buttonDoor;
	private JButton buttonSaveMap;
	private JButton buttonExit;
	
	private JSplitPane pane;
	private Assets gameImages;
	private JLabel labelGameState;
	private int width;
	private int height;
	
	private JPanel panelBoard;
	private JPanel panelElements;
	
	public PaneCreateGame(Assets gameImages, JLabel labelGameState, int width, int height) {
    	this.setLocation(500, 0);
		this.setSize(600, 400);
		this.setResizable(false);
		
		this.gameImages = gameImages;
    	this.labelGameState = labelGameState;
    	this.width = width;
    	this.height = height;


    	
    	createPanelBoard();
    	createPanelElements();
    	createButtonActions();
    	
    	this.pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelBoard, panelElements);
		pane.setDividerSize(0);  
    	pane.setResizeWeight(0.7);
    	
    	this.getContentPane().add(pane);
    	this.setVisible(true);
  	}

	private void createPanelBoard()
	{
		this.panelBoard = new JPanel();
		panelBoard.setBackground(Color.BLACK);
		panelBoard.setVisible(true);
	}
	
	private void createPanelElements()
	{
		this.panelElements = new JPanel(new GridLayout(4,2));
		
		this.buttonEmpty = new JButtonCustom(this.gameImages.empty);
		this.buttonWall = new JButtonCustom(this.gameImages.wall);
		this.buttonHero = new JButtonCustom(this.gameImages.heroArmed);
		this.buttonOgre = new JButtonCustom(this.gameImages.ogre);
		this.buttonKey = new JButtonCustom(this.gameImages.key);
		this.buttonDoor = new JButtonCustom(this.gameImages.closedDoor);
		this.buttonSaveMap = new JButton("Save Map");
		this.buttonExit = new JButton("Exit");

		this.panelElements.add(buttonEmpty);
		this.panelElements.add(buttonWall);
		this.panelElements.add(buttonHero);
		this.panelElements.add(buttonOgre);
		this.panelElements.add(buttonKey);
		this.panelElements.add(buttonDoor);
		this.panelElements.add(buttonSaveMap);
		this.panelElements.add(buttonExit);

		
		this.panelElements.setVisible(true);
	}
	
	private void createButtonActions()
	{
		this.buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.dispose();
			}
		});
		
	}
}
