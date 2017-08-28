package org.pouria.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.pouria.main.Main;
import org.pouria.resources.Resources;

public class MainFrame extends JFrame {

	private static final int WIDTH = 320;
	private static final int HEIGHT = 175;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	//
	private JButton startButton;
	private JButton stopButton;
	//
	private static final long serialVersionUID = 1L;
	private JTextField addressField;
	private JCheckBox useDefaultPath;
	private JLabel path;
	private JButton browseButton;
	//--------------------------------------------------------------------
	public MainFrame()
	{
		setTitle("No Sleep HDD v1.9");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setLocation((int)dim.getWidth()-315, (int)dim.getHeight()-210);
		setIconImage(Resources.getImage("NoSleepHDD.jpg"));
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		//
		JPanel pathPanel = new JPanel();
		pathPanel.setBounds(5, 5, 293, 70);
		pathPanel.setLayout(null);
		pathPanel.setBorder(BorderFactory.createTitledBorder("Path"));
		add(pathPanel);
		//
		useDefaultPath = new JCheckBox("Use default path");
		useDefaultPath.setBounds(6, 17, 200, 14);
		useDefaultPath.setSelected(true);
		useDefaultPath.addActionListener(new getActions());
		pathPanel.add(useDefaultPath);
		//
		path = new JLabel("Path: ");
		path.setBounds(10, 42, 30, 15);
		path.setEnabled(false);
		pathPanel.add(path);
		//
		addressField = new JTextField();
		addressField.setBounds(40, 37, 210, 22);
		addressField.setText(Main.path);
		addressField.setEnabled(false);
		pathPanel.add(addressField);
		//
		browseButton = new JButton("...");
		browseButton.setBounds(253, 36, 32, 24);
		browseButton.setEnabled(false);
		browseButton.addActionListener(new getActions());
		pathPanel.add(browseButton);
		//
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(5, 75, 293, 55);
		actionPanel.setLayout(new FlowLayout());
		actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		this.add(actionPanel);
		//
		startButton = new JButton("Start");
		startButton.addActionListener(new getActions());
		actionPanel.add(startButton);
		//
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new getActions());
		stopButton.setEnabled(false);
		actionPanel.add(stopButton);
	}
	//--------------------------------------------------------------------
	public void actionsOnStopButton()
	{
		Main.action = false;
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		useDefaultPath.setEnabled(true);
		useDefaultPath.setSelected(true);
		addressField.setText("G://Sycu.pmn");
	}
	//--------------------------------------------------------------------
	public void actionsOnStartButton()
	{
		Main.path = addressField.getText();
		Main.action = true;
		startButton.setEnabled(false);
		stopButton.setEnabled(true);
		useDefaultPath.setEnabled(false);
		path.setEnabled(false);
		addressField.setEnabled(false);
		browseButton.setEnabled(false);
	}
	//--------------------------------------------------------------------
	private class getActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(arg0.getSource().equals(startButton))
			{
				actionsOnStartButton();
			}
			else if(arg0.getSource().equals(stopButton))
			{
				actionsOnStopButton();
			}
			else if(arg0.getSource().equals(useDefaultPath))
			{
				boolean selected = useDefaultPath.isSelected();
				path.setEnabled(!selected);
				addressField.setEnabled(!selected);
				browseButton.setEnabled(!selected);
				if(!selected)
					addressField.setText(null);

				else
				{
					Main.path = "G://Sycu.pmn";
					addressField.setText(Main.path);
				}
			}
			else if(arg0.getSource().equals(browseButton))
			{
				JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse For Folder");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);
	            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	            {
	            	String path =  chooser.getSelectedFile().getAbsolutePath();
	            	path= path.replaceAll("\\\\", "//");
	            	path = path.concat("//Sycu.pmn");
	            	addressField.setText(path);
	            	Main.path = path;
	            }
			}
		}				
	}
}
