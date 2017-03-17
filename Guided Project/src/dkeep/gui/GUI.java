package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Menu panelMenu;
	private Game panelGame;

	/**
	 * Create the frame.
	 */
	public GUI(String title) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1080, 720);
		panelMenu = new Menu();
		panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMenu.setLayout(new BorderLayout(0, 0));
		
		//loads the menu components
		loadMenuActions();

		
		
		this.setContentPane(panelMenu);
	}
	
	private void loadMenuActions()
	{
		panelMenu.getButtonNewGame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanelComponents(panelMenu);
				panelMenu.setVisible(false);
			}
		});
	}
	
	private void disablePanelComponents(JPanel panel)
	{
		Component[] components = panelMenu.getComponents();
		for(int i = 0; i < components.length; i++)
		{
			components[i].setEnabled(false);
		}
	}
	
}
