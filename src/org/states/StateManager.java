package org.states;

import org.graphics.Graphics;
import org.graphics.Renderer;

/* 
 * I did not put the states management in the game loop
 * so if we want to add a state it is only in this class 
 * that we have to change things
 */
public class StateManager 
{
	private final StateType actualState = StateType.MENU;
	
	//Attribute of all the states
	private MenuState menuState;
	private GameState gameState;
	
	public StateManager()
	{
		//Creating an instance for all the states
		menuState = new MenuState();
		gameState = new GameState();
	}
	
	public void update(float delta)
	{
		switch(actualState)
		{
		case MENU:
			menuState.update(delta);
			break;
		case GAME:
			gameState.update(delta);
			break;
		}
	}
	
	public void render(Graphics g)
	{
		switch(actualState)
		{
		case MENU:
			menuState.render(g);
			break;
		case GAME:
			gameState.render(g);
			break;
		}
	}
	
	public void switchState(StateType type)
	{
		//TODO: switch state (don't forget to unload unused data)
	}
}
