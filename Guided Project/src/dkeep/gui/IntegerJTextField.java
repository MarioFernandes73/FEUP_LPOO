package dkeep.gui;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IntegerJTextField extends JTextField implements FocusListener {

	 public IntegerJTextField(){
		 addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char ch = e.getKeyChar();
	                if (ch<'1' || ch>'9') {
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
		if(number > 5)
		{
			this.setText("5");
		}
		else if(number < 1)
		{
			this.setText("1");
		}
		
	}
	
}
