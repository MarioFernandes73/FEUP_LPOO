package dkeep.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.logic.Game;

public class DialogSaveLoad extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel labelFileName;
	private JLabel labelState;
	private JTextField textFileName;
	private JButton buttonSaveGame;
	private JButton buttonLoadGame;
	private JButton buttonLoadKeep;
	
	private JPanel components;
	
	private Game game;
	private int[][] keep;
	private int loaded = 0;
	
	/**
	 * Create the panel.
	 */
	public DialogSaveLoad(Game game) {
		
		this.game = game;
		this.setSize(300, 400);
		this.setResizable(false);
		
		this.components = new JPanel();
		this.components.setLayout(null);
		createComponents();
		
		this.components.add(labelFileName);
		this.components.add(textFileName);
		this.components.add(buttonLoadGame);
		this.components.add(buttonSaveGame);
		this.components.add(labelState);
		this.components.add(buttonLoadKeep);
		
		
		getContentPane().add(components);
	}
	

	private void createComponents()
	{
		createLabels();

		createTextFields();
		
		createButtons();
		
	}
	
	private void createLabels()
	{
		//Labels
		this.labelFileName = new JLabel();
		this.labelFileName.setText("Insert the save state name");
		this.labelFileName.setBounds(70, 50, 210, 25);
		this.labelFileName.setVisible(true);
		
		this.labelState = new JLabel();
		this.labelState.setBounds(50,260,210,25);
		this.labelState.setVisible(false);
	}

	private void createTextFields()
	{
		//TextField
		this.textFileName = new JTextField();
		this.textFileName.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFileName.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.textFileName.setColumns(20);
		this.textFileName.setBounds(40,100,210,25);
		this.textFileName.setVisible(true);
	}
	
	private void createButtons()
	{
		//Buttons
	
		createLoadButtons();
		createSaveButtons();
	
	}
	
	private void createSaveButtons() {
		this.buttonSaveGame = new JButton("Save");
		buttonSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = DialogSaveLoad.this.textFileName.getText();
				if(!(fileName.equals(""))){
					saveGameFile(fileName);
					DialogSaveLoad.this.dispose();}
				else{
					DialogSaveLoad.this.labelState.setText("Please specify a name for your file");
					DialogSaveLoad.this.labelState.setVisible(true);}
			}
		});
		this.buttonSaveGame.setBounds(150, 210, 100, 50);
		this.buttonSaveGame.setVisible(true);
		if(this.game == null)
			this.buttonSaveGame.setEnabled(false);
	}

	private void saveGameFile(String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(".\\src\\dkeep\\saves\\"+fileName+".ser");
	        ObjectOutputStream out = new ObjectOutputStream(file);
	        out.writeObject(DialogSaveLoad.this.game);
	        out.close();
	        file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	

	private void createLoadButtons() {
		createLoadGameButton();
		createLoadKeepButton();
	}


	private void createLoadKeepButton() {
		this.buttonLoadKeep = new JButton("Load custom keep");
		buttonLoadKeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadKeep();
					
					DialogSaveLoad.this.dispose();
				}


		});
		this.buttonLoadKeep.setBounds(40,150,210,50);
		this.buttonLoadKeep.setVisible(true);
		
	}
	
	
	private void loadKeep() {
		try {
		FileInputStream file;
		file = new FileInputStream(".\\src\\dkeep\\saves\\customKeep.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        keep = (int[][]) in.readObject();
        in.close();
        file.close();
        loaded = 2;
		} catch (FileNotFoundException e1) {
			DialogSaveLoad.this.labelState.setText("Game save state has not been found");
			DialogSaveLoad.this.labelState.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

	private void createLoadGameButton() {
		this.buttonLoadGame = new JButton("Load");
		buttonLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = DialogSaveLoad.this.textFileName.getText();
				if(!(fileName.equals("")))
				{
					loadGameFile(fileName);
					
					DialogSaveLoad.this.dispose();
				}
				else
				{
					DialogSaveLoad.this.labelState.setText("Please specify a name for your file");
					DialogSaveLoad.this.labelState.setVisible(true);
				}
			}


		});
		this.buttonLoadGame.setBounds(40,210,100,50);
		this.buttonLoadGame.setVisible(true);
	}


	private void loadGameFile(String fileName) {
		FileInputStream file;
		try {
		file = new FileInputStream(".\\src\\dkeep\\saves\\"+fileName+".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        game = (Game) in.readObject();
        in.close();
        file.close();
        loaded = 1;
		} catch (FileNotFoundException e1) {
			DialogSaveLoad.this.labelState.setText("Game save state has not been found");
			DialogSaveLoad.this.labelState.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	public void saveGame(Game game)
	{
		this.game = game;
		this.buttonSaveGame.setEnabled(true);
	}
	
	public Game loadGame()
	{
		return this.game;
	}
	
	public int getLoaded()
	{
		return this.loaded;
	}
	
	public void setLoaded(int state)
	{
		this.loaded = state;
	}
	public int[][] getCustomKeep()
	{
		return this.keep;
	}

}
