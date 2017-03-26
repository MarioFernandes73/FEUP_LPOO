package dkeep.gui;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IntegerJTextField extends JTextField implements FocusListener {

	private static final long serialVersionUID = 1L;

	private int lowerBound;
	private int upperBound;
	
	public IntegerJTextField(int lowerBound, int upperBound){
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		 addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char ch = e.getKeyChar();
	                if (ch < '1' || ch > '9') {
	                    e.consume();
	                }
	            }
	        });	
		 addFocusListener(this);
	    }

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		int number = Integer.parseInt(this.getText());
		if(number > upperBound)
		{
			this.setText(Integer.toString(upperBound));
		}
		else if(number < lowerBound)
		{
			this.setText(Integer.toString(lowerBound));
		}
		
	}
	
}
