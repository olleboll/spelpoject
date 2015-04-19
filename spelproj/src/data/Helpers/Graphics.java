package data.Helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import data.Main;

public class Graphics {
	
	public static final int WIDTH = Main.WIDTH, HEIGHT = Main.HEIGHT;
	
	public static void beginSession(){
		Display.setTitle("Sara");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		glEnable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		// här bör du fixa djujp i skiten
		glOrtho(0, WIDTH, HEIGHT, 0, -HEIGHT -1 , HEIGHT + 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
	}
	
	public static void drawQuads(float x, float y, float width, float height){
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x + width,y);
		glVertex2f(x + width,y+height);
		glVertex2f(x,y + height);
		glEnd();
	}
	
	public static void drawQuadTex(Texture tex, float x, float y, float z, float width, float height){
		// här också när vi vill ha djup
		tex.bind();
		glTranslatef(x,y,z );
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
		
	}
	
	public static Texture loadTexture(String path, String filetype){
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(filetype, in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture QuickLoad(String name){
		Texture tex = null;
		tex = loadTexture("res/" + name +".png", "PNG");
		return tex;
	}
	
	public static Texture QuickLoadPlayerTex(String name){
		Texture tex = null;
		tex = loadTexture("res\\textures\\Player\\" + name +".png", "PNG");
		return tex;
	}
	
	public static Texture QuickLoadObjectTex(String name){
		Texture tex = null;
		tex = loadTexture("res\\textures\\objects\\" + name +".png", "PNG");
		return tex;
	}
	
	public static Texture QuickLoadTileTex(String name){
		Texture tex = null;
		tex = loadTexture("res\\textures\\tiles\\" + name +".png", "PNG");
		return tex;
	}

}
