package data.Helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import data.Main;

public class Graphics {

	public static final int WIDTH = Main.WIDTH, HEIGHT = Main.HEIGHT;
	public static float offsetMaxX, offsetMaxY, offsetMinX, offsetMinY;
	public static float WorldSizeX, WorldSizeY;
	private static float camX, camY;

	public static void beginSession() {
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
		Display.setVSyncEnabled(true);
		//glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearDepth(1);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void setWorld(int x, int y){
		WorldSizeX = x*64;
		WorldSizeY = y*64;
		offsetMaxX = WorldSizeX - Main.WIDTH;
		offsetMaxY = WorldSizeY - Main.HEIGHT;
		offsetMinX = 0;
		offsetMinY = 0;

		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glOrtho(0, WIDTH, HEIGHT, 0, -WorldSizeY - 1, WorldSizeY + 1);

		glMatrixMode(GL_MODELVIEW);
	}

	public static void setCamera(float x, float y) {
		camX = x;
		camY = y;
	}

	public static void drawQuads(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
	}

	public static void drawQuadTex(Texture tex, float x, float y, float z,
			float width, float height) {
		// här också när vi vill ha djup
		float scalex = 1, scaley = 1;
		if (width != height) {
			if (width < height) {
				scalex = width / height;
			} else {
				scaley = height / width;
			}
		}
		tex.bind();		
		glTranslatef(x, y, z);
		glTranslatef(camX, camY, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1 * scalex, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1 * scalex, 1 * scaley);
		glVertex2f(width, height);
		glTexCoord2f(0, 1 * scaley);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();

	}

	public static void drawQuadTexFlip90(Texture tex, float x, float y,
			float z, float width, float height) {
		// här också när vi vill ha djup
		float scalex = 1, scaley = 1;
		if (width != height) {
			if (width < height) {
				scalex = width / height;
			} else {
				scaley = height / width;
			}
		}

		tex.bind();
		glTranslatef(camX, camY, 0);
		glTranslatef(x, y, z);
		glBegin(GL_QUADS);
		glTexCoord2f(1 * scalex, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1 * scalex, 1 * scaley);
		glVertex2f(width, 0);
		glTexCoord2f(0, 1 * scaley);
		glVertex2f(width, height);
		glTexCoord2f(0, 0);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();

	}

	public static void drawQuadTexFlip180(Texture tex, float x, float y,
			float z, float width, float height) {
		// här också när vi vill ha djup
		float scalex = 1, scaley = 1;
		if (width != height) {
			if (width < height) {
				scalex = width / height;
			} else {
				scaley = height / width;
			}
		}

		tex.bind();
		glTranslatef(camX, camY, 0);
		glTranslatef(x, y, z);
		glBegin(GL_QUADS);
		glTexCoord2f(1 * scalex, 1 * scaley);
		glVertex2f(0, 0);
		glTexCoord2f(0, 1 * scaley);
		glVertex2f(width, 0);
		glTexCoord2f(0, 0);
		glVertex2f(width, height);
		glTexCoord2f(1 * scalex, 0);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();

	}

	public static void drawQuadTexFlip270(Texture tex, float x, float y,
			float z, float width, float height) {
		// här också när vi vill ha djup
		float scalex = 1, scaley = 1;
		if (width != height) {
			if (width < height) {
				scalex = width / height;
			} else {
				scaley = height / width;
			}
		}

		tex.bind();
		glTranslatef(camX, camY, 0);
		glTranslatef(x, y, z);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1 * scaley);
		glVertex2f(0, 0);
		glTexCoord2f(0, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1 * scalex, 0);
		glVertex2f(width, height);
		glTexCoord2f(1 * scalex, 1 * scaley);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();

	}

	public static void drawText(TrueTypeFont font) {
		Color.white.bind();
		glTranslatef(100, 50, WorldSizeY - 1);
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY",
				Color.yellow);
		glLoadIdentity();
	}

	public static Texture loadTexture(String path, String filetype) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(filetype, in,  GL_NEAREST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tex;
	}

	public static Texture QuickLoad(String name) {
		Texture tex = null;
		tex = loadTexture("res/" + name + ".png", "PNG");
		return tex;
	}

	public static Texture QuickLoadPlayerTex(String name) {
		Texture tex = null;
		tex = loadTexture("res\\textures\\Player\\" + name + ".png", "PNG");
		return tex;
	}

	public static Texture QuickLoadEntityTex(String name) {
		Texture tex = null;
		tex = loadTexture("res\\textures\\Entities\\" + name + ".png", "PNG");
		return tex;
	}

	public static Texture QuickLoadObjectTex(String name) {
		Texture tex = null;
		tex = loadTexture("res\\textures\\objects\\" + name + ".png", "PNG");
		return tex;
	}

	public static Texture QuickLoadTileTex(String name) {
		Texture tex = null;
		tex = loadTexture("res\\textures\\tiles\\" + name + ".png", "PNG");
		return tex;
	}

}
