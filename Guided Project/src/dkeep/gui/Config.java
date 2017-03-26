package dkeep.gui;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Config extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel labelOgreQuantity;
	private IntegerJTextField inputOgreQuantity;
	private JLabel labelGuardPersonality;
	private JComboBox<String> inputGuardPersonality;
	private JLabel labelEnemiesMoving;
	private JCheckBox inputEnemiesMoving;
	private JLabel labelEnemiesAttacking;
	private JCheckBox inputEnemiesAttacking;
	
	public Config() {

		this.setSize(500, 500);
		this.setLayout(null);
		this.setResizable(false);

		createComponents();
		
		//structure
		this.add(this.labelOgreQuantity);
		this.add(this.inputOgreQuantity);
		this.add(this.labelGuardPersonality);
		this.add(this.inputGuardPersonality);
		this.add(this.labelEnemiesMoving);
		this.add(this.inputEnemiesMoving);
		this.add(this.labelEnemiesAttacking);
		this.add(this.inputEnemiesAttacking);
	}
	
	public void createComponents()
	{
		//Text field
		this.inputOgreQuantity = new IntegerJTextField(1,5);
		this.inputOgreQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		this.inputOgreQuantity.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.inputOgreQuantity.setText("3");
		this.inputOgreQuantity.setColumns(1);
		this.inputOgreQuantity.setBounds(300,50,100,50);
		this.inputOgreQuantity.setVisible(true);
		
		
		//Combo box
		this.inputGuardPersonality = new JComboBox<String>();
		this.inputGuardPersonality.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.inputGuardPersonality.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		this.inputGuardPersonality.setBounds(300,150,100,50);
		this.inputGuardPersonality.setVisible(true);
		
		//Check boxes
		this.inputEnemiesMoving = new JCheckBox();
		this.inputEnemiesMoving.setVisible(true);
		this.inputEnemiesMoving.setBounds(300,250,150,50);
		
		this.inputEnemiesAttacking = new JCheckBox();
		this.inputEnemiesAttacking.setVisible(true);
		this.inputEnemiesAttacking.setBounds(300,350,150,50);
		
		//Labels
		this.labelOgreQuantity = new JLabel();
		this.labelOgreQuantity.setText("Ogre quantity");
		this.labelOgreQuantity.setBounds(100,50,150,50);
		this.labelOgreQuantity.setVisible(true);
		
		this.labelGuardPersonality = new JLabel();
		this.labelGuardPersonality.setText("Guard Personality");
		this.labelGuardPersonality.setBounds(100,150,150,50);
		this.labelGuardPersonality.setVisible(true);
		
		this.labelEnemiesMoving = new JLabel();
		this.labelEnemiesMoving.setText("Enemies can move");
		this.labelEnemiesMoving.setVisible(true);
		this.labelEnemiesMoving.setBounds(100,250,150,50);
		
		this.labelEnemiesAttacking = new JLabel();
		this.labelEnemiesAttacking.setText("Enemies can attack");
		this.labelEnemiesAttacking.setVisible(true);
		this.labelEnemiesAttacking.setBounds(100,350,150,50);
		
	}

	public int getOgreQuantity()
	{
		return Integer.parseInt(this.inputOgreQuantity.getText());
	}
	
	public int getGuardPersonality()
	{
		return this.inputGuardPersonality.getSelectedIndex();
	}
	
	public boolean getMovingEnemies()
	{
		return this.inputEnemiesMoving.isSelected();
	}
	
	public boolean getAttackingEnemies()
	{
		return this.inputEnemiesAttacking.isSelected();
	}
	
}
