package org.pouria.resources;

import java.awt.Image;
import java.awt.Toolkit;

public class Resources 
{
	public static Image getImage(String name)
	{
		return Toolkit.getDefaultToolkit().getImage(Resources.class.getResource(name));
	}
}
