package dkeep.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.logic.Game;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class SaveLoad extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel labelFileName;
	private JLabel labelState;
	private JTextField textFileName;
	private JButton buttonSaveGame;
	private JButton buttonLoadGame;
	
	private JPanel components;
	
	private Game game;
	
	/**
	 * Create the panel.
	 */
	public SaveLoad(Game game) {
		
		this.game = game;
		this.setSize(300, 300);
		this.setResizable(false);
		
		this.components = new JPanel();
		this.components.setLayout(null);
		createComponents();
		
		this.components.add(labelFileName);
		this.components.add(textFileName);
		this.components.add(buttonLoadGame);
		this.components.add(buttonSaveGame);
		this.components.add(labelState);
		
		
		getContentPane().add(components);
	}
	

	private void createComponents()
	{
		//Labels
		this.labelFileName = new JLabel();
		this.labelFileName.setText("Insert the save state name");
		this.labelFileName.setBounds(70, 50, 210, 25);
		this.labelFileName.setVisible(true);
		
		this.labelState = new JLabel();
		this.labelState.setBounds(50,210,210,25);
		this.labelState.setVisible(false);
		
		//TextField
		this.textFileName = new JTextField();
		this.textFileName.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFileName.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.textFileName.setColumns(20);
		this.textFileName.setBounds(40,100,210,25);
		this.textFileName.setVisible(true);
		
		//Buttons
		this.buttonLoadGame = new JButton("Load");
		buttonLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = SaveLoad.this.textFileName.getText();
				if(!(fileName.equals("")))
				{
					FileInputStream file;
					try {
					file = new FileInputStream(".\\src\\dkeep\\saves\\"+fileName+".ser");
			        ObjectInputStream in = new ObjectInputStream(file);
			        game = (Game) in.readObject();
			        in.close();
			        file.close();
					} catch (FileNotFoundException e1) {
						SaveLoad.this.labelState.setText("Game save state has not been found");
						SaveLoad.this.labelState.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					SaveLoad.this.dispose();
				}
				else
				{
					SaveLoad.this.labelState.setText("Please specify a name for your file");
					SaveLoad.this.labelState.setVisible(true);
				}
			}
		});
		this.buttonLoadGame.setBounds(40,160,100,50);
		this.buttonLoadGame.setVisible(true);
		
		this.buttonSaveGame = new JButton("Save");
		buttonSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = SaveLoad.this.textFileName.getText();
				if(!(fileName.equals("")))
				{
					try {
						FileOutputStream file = new FileOutputStream(".\\src\\dkeep\\saves\\"+fileName+".ser");
				        ObjectOutputStream out = new ObjectOutputStream(file);
				        out.writeObject(SaveLoad.this.game);
				        out.close();
				        file.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					SaveLoad.this.dispose();
				}
				else
				{
					SaveLoad.this.labelState.setText("Please specify a name for your file");
					SaveLoad.this.labelState.setVisible(true);
				}
				
			}
		});
		this.buttonSaveGame.setBounds(150, 160, 100, 50);
		this.buttonSaveGame.setVisible(true);
	}
	
	public void saveGame(Game game)
	{
		this.game = game;
	}
	
	public Game loadGame()
	{
		return this.game;
	}
}
