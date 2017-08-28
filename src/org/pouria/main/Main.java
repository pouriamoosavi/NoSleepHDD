package org.pouria.main;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.pouria.gui.GUIManager;
import org.pouria.util.IOFile;

public class Main {
	public static boolean action = false;
	public static String path = "G://Sycu.pmn";
	public static void main(String[] args)
	{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//
			GUIManager.getMainFrame().setVisible(true);
			//
			while(true)
			{
				if(action)
					new IOFile(path);

				Thread.sleep(100);
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(GUIManager.getMainFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			action= false;
			GUIManager.getMainFrame().actionsOnStopButton();
		}
	}

}
