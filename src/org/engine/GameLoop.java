package org.engine;

import org.graphics.EventListener;
import org.graphics.Renderer;
import org.states.StateManager;

public class GameLoop 
{
	private static final byte TARGET_FPS = 40;
	private static final int TARGET_TIME = 1_000_000_000 / TARGET_FPS; //Time each update should take in nanosecond
	private static final byte MAX_UPDATES = 5; //Max update that can happen in one loop (if the game lag or get behind)
	private static final boolean FPS_COUNTER = false; //Show the FPS counter in the console
	
	private static boolean running = false;
	private static long lastUpdateTime = 0;
	private static short updates = 0;
	
	private StateManager stateManager;
	
	public GameLoop(StateManager stateManager)
	{
		this.stateManager = stateManager;
	}
	
	public void start()
	{
		Thread thread = new Thread()
		{
			public void run()
			{
				running = true;
				lastUpdateTime = System.nanoTime();
				
				//Initializing the fps counter
				short fps = 0;
				long lastFpsCheck = System.nanoTime();
				
				stateManager = new StateManager(); //Creating the state manager
				
				while(running)
				{
					long currentTime = System.nanoTime();
					
					//Restart the update counter
					updates = 0;
					
					//While an update takes more time than the TARGET_TIME
					while(currentTime - lastUpdateTime >= TARGET_TIME && updates < MAX_UPDATES)
					{
						stateManager.update(updateDelta());
						lastUpdateTime += TARGET_TIME;
						updates++;
					}
					
					//Here to show the number of update for debug
					//System.out.println(updates);
					
					//Render
					Renderer.render();
					
					//Fps count for debug
					if(FPS_COUNTER)
					{
						fps++;
						if(System.nanoTime() > lastFpsCheck + 1_000_000_000)
						{
							System.out.println(fps);
							fps = 0;
							lastFpsCheck = System.nanoTime();
						}
					}
					
					
					long timeTaken = System.nanoTime() - currentTime;
					//If the time taken is less than the TARGET_TIME sleep the thread (to keep a constant fps and update count)
					if(timeTaken < TARGET_TIME)
					{
						try 
						{
							//Converting nanosecond in milisecond (/1_000_000)
							Thread.sleep((TARGET_TIME - timeTaken) / 1_000_000);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}
		
		};
		
		//Start game loop thread
		thread.setName("GameLoop");
		thread.start();
	}
	
	public static float updateDelta()
	{
		return 1.0f / 1_000_000_000 * TARGET_TIME;
	}
}
