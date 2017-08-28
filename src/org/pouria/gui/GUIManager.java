package org.pouria.gui;

public class GUIManager 
{
	private static MainFrame mainFrame;
	
	private GUIManager(){}
	
	public static MainFrame getMainFrame() {
		if (mainFrame == null)
			mainFrame = new MainFrame();

		return mainFrame;
	}
}
