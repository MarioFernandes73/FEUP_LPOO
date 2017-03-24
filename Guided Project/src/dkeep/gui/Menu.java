package dkeep.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage newGame;
	private BufferedImage confirm;
	
	private JButton buttonNewGame;
	private JButton buttonConfirm;
	private JComboBox<String> comboBoxGuardPersonality;
	private IntegerJTextField textFieldOgreQuantity;
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		try
		{
			//load images
			this.newGame = ImageIO.read(getClass().getResource("resources/menu/newGame.jpg"));				
			this.confirm = ImageIO.read(getClass().getResource("resources/menu/confirm.jpg"));
			//create components
			createComponents();
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void createComponents()
	{
		//Combo box
		this.comboBoxGuardPersonality = new JComboBox<>();
		this.comboBoxGuardPersonality.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.comboBoxGuardPersonality.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		this.comboBoxGuardPersonality.setBounds(180, 85, 100, 20);
		this.comboBoxGuardPersonality.setVisible(false);
		this.add(this.comboBoxGuardPersonality);
		
		//Text field
		this.textFieldOgreQuantity = new IntegerJTextField();
		this.textFieldOgreQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFieldOgreQuantity.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.textFieldOgreQuantity.setText("3");
		this.textFieldOgreQuantity.setBounds(180, 50, 30, 20);
		this.textFieldOgreQuantity.setColumns(10);
		this.textFieldOgreQuantity.setVisible(false);
		this.add(this.textFieldOgreQuantity);
		
		//Buttons
		this.buttonConfirm = new JButton(new ImageIcon(confirm));
		this.buttonConfirm.setBounds(100,100,100,100);
		this.add(this.buttonConfirm);
		
		this.buttonNewGame = new JButton(new ImageIcon(newGame));
		this.buttonNewGame.setBounds(300,500,400,100);
		this.buttonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxGuardPersonality.setVisible(true);
				textFieldOgreQuantity.setVisible(true);
				buttonConfirm.setVisible(true);
			}
		});	
		this.add(this.buttonNewGame);
		
	}
	
	public JButton getButtonConfirm()
	{
		return this.buttonConfirm;
	}
	
	@Override
	public Component[] getComponents()
	{
		Component[] components = {buttonNewGame};
		return components;
	}
}
