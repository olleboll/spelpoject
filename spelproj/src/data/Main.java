package data;

import level.Level;
import level.RabbitLevel;
import level.TestLevel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import text.Text;
import data.tiles.Tile;
import data.tiles.TileType;
import static data.Helpers.Graphics.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public Level level;
	public int updates;
	public Text text;
	private static String Spawn = "/textures/Level/stortest.png";
	private static String Rabbit = "/textures/Level/RabbitLevel.png";
	
	public static final int WIDTH = 1200, HEIGHT = WIDTH * 9 / 16;
	
	public Main(){
		updates = 0;
		beginSession();
		
		
		level = new TestLevel(Spawn, 64,64);
		setWorld(level.SIZEX,level.SIZEY);
		//level.setPlayerInput(key);
		
		
		/*long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
*/		
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			/*
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				//level.update();
				delta--;
			}*/
			level.update();
			level.draw();
			
			//text.draw();
			
			Display.update();
			Display.sync(60);
			//updates++;
			
			
			
		}
		
	}

	public static void main(String[] args) {
		new Main();

	}

}
