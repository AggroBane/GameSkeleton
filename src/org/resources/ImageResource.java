package org.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.graphics.Renderer;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class ImageResource 
{
	//OpenGL texture
	private Texture texture = null;
	
	//Java.awt texture
	private BufferedImage image = null;
	
	public ImageResource(String path)
	{
		URL url = ImageResource.class.getResource(path);
		
		//Loading that image
		try
		{
			image = ImageIO.read(url);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		if(image != null)
		{
			image.flush();
		}
	}
	
	
	public Texture getTexture()
	{
		//If there is no set image return null (error in constructor)
		if(image == null)
		{
			return null;
		}
		
		//If the texture is null, it wasn't converted in an openGL texture
		if(texture == null)
		{
			//Convert it
			texture = AWTTextureIO.newTexture(Renderer.getProfile(), image, true);
		}
		
		return texture;
	}

}
