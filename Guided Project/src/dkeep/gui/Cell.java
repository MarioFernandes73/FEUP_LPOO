package dkeep.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

	private int row;
	private int column;
	
	/**
	 * Create the panel.
	 */
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		
		this.setBorder(new LineBorder(Color.orange, 1));
		this.setBackground(Color.gray);
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}

}
