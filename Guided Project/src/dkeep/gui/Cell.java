package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private BufferedImage image;
	
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
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(image,row,column, row+row, column+column, null);
	}

}
