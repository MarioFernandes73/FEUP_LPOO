package dkeep.gui;


import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class JSplitPaneCustom extends JSplitPane {
	
	private static final long serialVersionUID = 1L;
	private final int location = 200;
	 {
		 this.setDividerLocation(location);
	 }
	    public JSplitPaneCustom(int split, JComponent comp1, JComponent comp2) 
	    {
	    	super(split, comp1,comp2);
	    	this.setDividerSize(0);
	    }

		@Override
	    public int getDividerLocation() 
	    {
	        return location ;
	    }
	    
	    @Override
	    public int getLastDividerLocation()
	    {
	        return location ;
	    }

}
