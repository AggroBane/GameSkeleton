package org.input;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyInput implements KeyListener
{
	private static final int MAX_KEY = 256;
	
	private static boolean[] isKeyDown = new boolean[MAX_KEY];

	public KeyInput()
	{
		for(int i = 0; i < MAX_KEY; i++)
		{
			isKeyDown[i] = false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		isKeyDown[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
        if( !e.isPrintableKey() || e.isAutoRepeat() ) return;
		isKeyDown[e.getKeyCode()] = false;
	}
	
	public static boolean isKeyDown(int keyCode)
	{
		return isKeyDown[keyCode];
	}

}
