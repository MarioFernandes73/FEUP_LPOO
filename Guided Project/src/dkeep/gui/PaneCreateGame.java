package dkeep.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	private int width;
	private int height;
	private int[][] map;
	private int currentIdentifier;
	
	private JPanel panelBoard;
	private JPanel panelElements;
	
	private JLabel labelGameState;
	
	
	public PaneCreateGame(Assets gameImages,JLabel labelGameState, int width, int height) {
    	this.setLocation(500, 0);
		this.setSize(600, 400);
		this.setResizable(false);
		
		this.labelGameState = labelGameState;
		this.gameImages = gameImages;
    	this.width = width;
    	this.height = height;
    	this.map = new int[height][width];
    	this.currentIdentifier = 0;
    	
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
		this.panelBoard = new JPanel(new GridLayout(height,width));
		for(int i = 0; i < map.length; i++)
		{
			for (int j = 0; j< map[i].length; j++)
			{
				map[i][j] = 0;
				int k = i;
				int l = j;
				JButtonCustom currentButton = new JButtonCustom(gameImages.empty);
				currentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validatePosition(k,l))
						{
							map[k][l] = currentIdentifier;
							auxSwitch(currentButton,currentIdentifier);
						}
						else
						{
							PaneCreateGame.this.labelGameState.setText("Invalid tile position");
						}
					}
				});
				
				this.panelBoard.add(currentButton);
			}
		}
		panelBoard.setVisible(true);
	}
	
	protected void auxSwitch(JButtonCustom currentButton, int currentIdentifier) {
		switch(currentIdentifier)
		{
		case 1:
			currentButton.setImage(gameImages.wall);
			break;
		case 2:
			currentButton.setImage(gameImages.heroArmed);
			break;
		case 6:
			currentButton.setImage(gameImages.closedDoor);
			break;
		case 9:
			currentButton.setImage(gameImages.key);
			break;
		case 10:
			currentButton.setImage(gameImages.ogre);
			break;
		default:
			currentButton.setImage(gameImages.empty);
		}
		
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
		this.buttonEmpty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 0;
			}
		});
		
		this.buttonWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 1;
			}
		});
		
		this.buttonHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 2;
			}
		});
		
		this.buttonOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 10;
			}
		});
		
		this.buttonKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 9;
			}
		});
		
		this.buttonDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.currentIdentifier = 6;
			}
		});
		
		buttonSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						if(validateMap())
						{
							FileOutputStream file = new FileOutputStream(".\\src\\dkeep\\saves\\customKeep.ser");
					        ObjectOutputStream out = new ObjectOutputStream(file);
					        out.writeObject(PaneCreateGame.this.map);
					        out.close();
					        file.close();
					        PaneCreateGame.this.labelGameState.setText("Custom keep made!");
						}
						else
						{
							PaneCreateGame.this.labelGameState.setText("Invalid custom keep");
						}

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
			}
		});

		
		
		
		this.buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaneCreateGame.this.dispose();
			}
		});
		
	}
	
	private boolean validateMap()
	{
		int heroCounter = 0, ogreCounter = 0, keyCounter = 0, doorCounter = 0;
		for(int i = 0; i<this.map.length; i++)
		{
			for ( int j = 0; j<this.map[i].length; j++)
			{
				if(i == 0 || j == 0 || i== map.length-1 || j==map[0].length-1)
				{
					if(map[i][j] != 1 && map[i][j] != 6)
						return false;
				}
					switch (map[i][j])
					{
					case 2:
						heroCounter++;
						break;
					case 6:
						doorCounter++;
						break;
					case 9:
						keyCounter++;
						break;
					case 10:
						ogreCounter++;
						break;
					}
			}
		}
		
		if(heroCounter == 1 && ogreCounter > 0 && keyCounter >0 && doorCounter > 0)
			return true;
		
		return false;
	}
	
	public boolean validatePosition(int k, int l)
	{
		switch(this.currentIdentifier)
		{
		case 6:
			if(k != 0 && l != 0 && k != map.length-1 && l!=map[0].length-1)
				return false;
			break;
		}
		return true;
	}
	
}
