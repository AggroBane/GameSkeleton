package org.graphics;


import org.GameContainer;
import org.states.StateManager;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class EventListener implements GLEventListener
{
	public static GL2 gl;
	private static Graphics g;
	private static StateManager stateManager;
	
	public static void setStateManager(StateManager _stateManager)
	{
		stateManager = _stateManager;
	}
	
	@Override
	public void init(GLAutoDrawable drawable) 
	{
		//Initializing the graphic context
		gl = drawable.getGL().getGL2(); 
		g = new Graphics();
		
		gl.glClearColor(0, 0, 0, 1); //Clear the color
		
		gl.glEnable(GL2.GL_TEXTURE_2D); //Activate the use of 2d texture
		
		gl.glEnable (GL2.GL_BLEND); //Activate the use of rgba
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA); //Activate the alpha channel
	}
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		//PUT NOTHING BEFORE THIS LINE
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT); //Clear the color buffer of the last display
		
		stateManager.render(g);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		//When the window is resized switch the matrix mode to a projection
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		//Redo the orthogonal ratio (so everything displayed is resized)
		gl.glOrtho(0, GameContainer.BASE_WIDTH, 0, GameContainer.BASE_HEIGHT, 1, -1);
		
		//Switch back the matrix mode to a modelview
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		System.exit(0);
	}
}
