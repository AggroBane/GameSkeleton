package org.graphics;

import org.resources.ImageResource;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Graphics 
{
	//Colors
	private float r = 1;
	private float g = 1;
	private float b = 1;
	private float a = 1;
	
	private float rotation = 0;
	
	private GL2 gl;
	
	public Graphics()
	{
		//Access to the graphics instance of the EventListener
		gl = EventListener.gl;
	}
	
	public void fillRect(float x, float y, float width, float height)
	{
		gl.glTranslatef(x + width / 2, y - height / 2, 0); //Translate the graphic context to the center of the rectangle
		gl.glRotatef(rotation, 0, 0, 1); //Rotation of the graphic context
		
		gl.glColor4f(r, g, b, a); //Set the color
		
		//Drawing those verteces
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-width / 2, height / 2);
			gl.glVertex2f(width / 2, height / 2);
			gl.glVertex2f(width / 2, - height / 2);
			gl.glVertex2f(-width / 2, - height / 2);
		gl.glEnd();
		gl.glFlush();
		
		gl.glRotatef(-rotation, 0, 0, 1); //Rotate back to the initial rotation
		gl.glTranslatef(-(x + width / 2), -(y - height / 2), 0); //Translate back to the initial position
	}
	
	public void drawRect(float x, float y, float width, float height)
	{
		gl.glTranslatef(x + width / 2, y - height / 2, 0); //Translate the graphic context to the center of the rectangle
		gl.glRotatef(rotation, 0, 0, 1); //Rotation of the graphic context
		
		//Drawing those verteces
		gl.glColor4f(r, g, b, a); //Set the color
		gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glVertex2f(-width / 2, height / 2);
			gl.glVertex2f(width / 2, height / 2);
			gl.glVertex2f(width / 2, - height / 2);
			gl.glVertex2f(-width / 2, - height / 2);
		gl.glEnd();
		gl.glFlush();
		
		gl.glRotatef(-rotation, 0, 0, 1); //Rotate back to the initial rotation
		gl.glTranslatef(-(x + width / 2), -(y - height / 2), 0); //Translate back to the initial position
	}
	
	public void drawImage(ImageResource image, float x, float y, float width, float height)
	{
		//Gettint the texture and verifying that it exists
		Texture texture = image.getTexture();
		if(texture != null)
		{
			gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
		}
		
		gl.glTranslatef(x + width / 2, y - height / 2, 0); //Translate the graphic context to the center of the rectangle
		gl.glRotatef(rotation, 0, 0, 1); //Rotation of the graphic context
		
		//Drawing those verteces
		gl.glColor4f(1, 1, 1, 1);
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0, 0);
			gl.glVertex2f(-width / 2, height / 2);
			
			gl.glTexCoord2f(1, 0);
			gl.glVertex2f(width / 2, height / 2);
			
			gl.glTexCoord2f(1, 1);
			gl.glVertex2f(width / 2, - height / 2);
			
			gl.glTexCoord2f(0, 1);
			gl.glVertex2f(-width / 2, - height / 2);
		gl.glEnd();
		gl.glFlush();
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, 0); //Make the texture appear
		gl.glRotatef(-rotation, 0, 0, 1); //Rotate back to the initial rotation
		gl.glTranslatef(-(x + width / 2), -(y - height / 2), 0); //Translate back to the initial position
	}
	
	public void drawImage(ImageResource image, float x, float y)
	{
		Texture texture = image.getTexture();
		
		drawImage(image, x, y, texture.getWidth(), texture.getHeight());
	}
	
	
	public void translate(float x, float y)
	{
		gl.glTranslatef(x, y, 0);
	}
	

	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}
	
	//rgb must be between 0 and 1
	public void setColor(float r, float g, float b)
	{
		if(r > 1 || r < 0)
		{
			r = 0;
		}
		else
		{
			this.r = r;
		}
		
		if(g > 1 || g < 0)
		{
			g = 0;
		}
		else
		{
			this.g = g;
		}
		
		if(b > 1 || b < 0)
		{
			b = 0;
		}
		else
		{
			this.b = b;
		}
	}
	
	//rgba must be between 0 and 1
	public void setColor(float r, float g, float b, float a)
	{
		setColor(r, g, b);
		if(a > 1 || a < 0)
		{
			a = 0;
		}
		else
		{
			this.a = a;
		}
	}
}
