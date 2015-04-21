package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import data.level.Level;
import data.tiles.Tile;
import data.tiles.TileType;
import static data.Helpers.Graphics.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public Level level;
	private String Spawn = "/textures/Level/level.png";
	
	public static final int WIDTH = 1080, HEIGHT = WIDTH * 9 / 16;
	
	public Main(){
		
		beginSession();
		
		
		level = new Level(Spawn);
		//level.setPlayerInput(key);

		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			level.update();
			level.draw();

			Display.update();
			Display.sync(60);
			
		}
		
	}

	public static void main(String[] args) {
		new Main();

	}

}
