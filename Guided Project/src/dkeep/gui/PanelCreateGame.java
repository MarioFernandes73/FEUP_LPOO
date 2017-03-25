package dkeep.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCreateGame extends JPanel {

	private static final long serialVersionUID = 1L;

	private Assets gameImages;
	private JLabel labelGameState;
	
	public PanelCreateGame(Assets gameImages, JLabel labelGameState) {

		this.gameImages = gameImages;
		this.labelGameState = labelGameState;
		
	}

}
