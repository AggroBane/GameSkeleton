package org;

import org.engine.GameLoop;
import org.graphics.EventListener;
import org.graphics.Renderer;
import org.states.StateManager;

public class GameContainer 
{
	public static final int BASE_WIDTH = 960;
	public static final int BASE_HEIGHT = 540;
	public static final String NOM = "Game";
	public static final String VERSION = "Prototype";
	
	private StateManager stateManager;
	
	public GameContainer()
	{
		stateManager = new StateManager();
	}
	
	public StateManager getStateManager()
	{
		return stateManager;
	}
	
	public static void main(String[] args)
	{	
		GameContainer gc = new GameContainer();
		StateManager stateManager = gc.getStateManager();
		GameLoop loop = new GameLoop(stateManager);
		
		EventListener.setStateManager(stateManager);
		Renderer.init(); //Initialize the window
		
		
		//Starts the game loop
		loop.start();
	}
	
}
