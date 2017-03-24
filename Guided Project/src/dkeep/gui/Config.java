package dkeep.gui;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	private JPanel panelOgreQuantity;
	private JPanel panelGuardPersonality;
	private JPanel panelEnemiesMoving;
	private JPanel panelEnemiesAttacking;
	
	public Config() {

		this.setSize(1000, 500);
		initializePanels();
		createComponents();
		
		//structure
		this.panelOgreQuantity.add(this.labelOgreQuantity);
		this.panelOgreQuantity.add(this.add(this.inputOgreQuantity));
		this.panelGuardPersonality.add(this.add(this.labelGuardPersonality));
		this.panelGuardPersonality.add(this.add(this.inputGuardPersonality));
		this.panelEnemiesMoving.add(this.add(this.labelEnemiesMoving));
		this.panelEnemiesMoving.add(this.add(this.inputEnemiesMoving));
		this.panelEnemiesAttacking.add(this.add(this.labelEnemiesAttacking));
		this.panelEnemiesAttacking.add(this.add(this.inputEnemiesAttacking));

		this.add(panelOgreQuantity);
		this.add(panelGuardPersonality);
		this.add(panelEnemiesMoving);
		this.add(panelEnemiesAttacking);
	}
	
	public void initializePanels()
	{
		this.panelOgreQuantity = new JPanel();
		this.panelGuardPersonality = new JPanel();
		this.panelEnemiesMoving = new JPanel();
		this.panelEnemiesAttacking = new JPanel();
	}
	
	public void createComponents()
	{
		//Text field
		this.inputOgreQuantity = new IntegerJTextField();
		this.inputOgreQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		this.inputOgreQuantity.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.inputOgreQuantity.setText("3");
		this.inputOgreQuantity.setColumns(1);
		this.inputOgreQuantity.setVisible(true);
		
		//Combo box
		this.inputGuardPersonality = new JComboBox<String>();
		this.inputGuardPersonality.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.inputGuardPersonality.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		this.inputGuardPersonality.setVisible(true);
		
		//Check boxes
		this.inputEnemiesMoving = new JCheckBox();
		this.inputEnemiesMoving.setVisible(true);
		
		this.inputEnemiesAttacking = new JCheckBox();
		this.inputEnemiesAttacking.setVisible(true);
		
		//Labels
		this.labelOgreQuantity = new JLabel();
		this.labelOgreQuantity.setText("Ogre quantity");
		this.labelOgreQuantity.setVisible(true);
		
		this.labelGuardPersonality = new JLabel();
		this.labelGuardPersonality.setText("Guard Personality");
		this.labelGuardPersonality.setVisible(true);
		
		this.labelEnemiesMoving = new JLabel();
		this.labelEnemiesMoving.setText("Enemies can move");
		this.labelEnemiesMoving.setVisible(true);
		
		this.labelEnemiesAttacking = new JLabel();
		this.labelEnemiesAttacking.setText("Enemies can attack");
		this.labelEnemiesAttacking.setVisible(true);
		
	}

}
