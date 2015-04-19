package entities;

import static data.Helpers.Graphics.QuickLoadTileTex;

import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.renderableObj;

public class Entity extends renderableObj{
	
	protected float x, y, width, height;
	protected Texture texture;
	protected boolean solid = false;
	
	public Entity(float x, float y, float z, float width, float height, Texture texture){
		this.x = x;
		this.y = y;
		this.z = -Main.HEIGHT + y + height;
		this.width = width;
		this.height = height;
		this.texture = loadTexture();
	}
	
	

	protected Texture loadTexture(){
		return texture;
		}
	
	public void update(){}
	
	public void draw(){}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
