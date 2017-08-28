package org.pouria.util;

import java.io.File;
import java.io.FileWriter; 
import javax.swing.JOptionPane;

import org.pouria.gui.GUIManager;
import org.pouria.main.Main;

public class IOFile extends File 
{
	private static final long serialVersionUID = 1L;
	//
	private FileWriter fileWriter;

	public IOFile(String fileAddress)
	{
		super(fileAddress);
//		JOptionPane.showMessageDialog(GUIManager.getMainFrame(),"You choose this path:\n" + Main.path);
		try{
			if(!this.exists())
				this.createNewFile();

			while(Main.action)
			{
				fileWriter = new FileWriter(this, false);
				fileWriter.write("p");
				Thread.sleep(500);
			}
			fileWriter.flush();
			fileWriter.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(GUIManager.getMainFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			Main.action = false;
			GUIManager.getMainFrame().actionsOnStopButton();
		}
	}
	public static void deleteFile(String fileAddress)
	{
		File file = new File(fileAddress);
		boolean bool = file.delete();
		System.out.println(bool);
	}
}
