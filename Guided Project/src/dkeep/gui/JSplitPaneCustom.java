package dkeep.gui;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class JSplitPaneCustom extends JSplitPane {
	
	private static final long serialVersionUID = 1L;

	    public JSplitPaneCustom(int split, JComponent comp1, JComponent comp2) 
	    {
	    	super(split, comp1,comp2);
	    	this.setDividerSize(0);  
	    	this.setResizeWeight(.3);
	    }



}
