package org.graphics;


import org.GameContainer;
import org.input.KeyInput;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

public class Renderer 
{
	private static GLWindow window = null;
	private static GLProfile profile = null;
	
	public static void init()
	{
		//Window initializing stuff
		GLProfile.initSingleton();
		profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);
		window = GLWindow.create(caps);
		
		//Window parameters
		window.setTitle(GameContainer.NOM+" - "+GameContainer.VERSION);
		window.setSize(GameContainer.BASE_WIDTH, GameContainer.BASE_HEIGHT);
		window.setResizable(true);
		
		//Listeners
		window.addGLEventListener(new EventListener());
		window.addKeyListener(new KeyInput());
		
		window.setVisible(true);
	}
	
	public static void render()
	{
		if(window == null) return;
		
		window.display();
	}
	
	public static GLProfile getProfile()
	{
		return profile;
	}
}
