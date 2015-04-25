package entities;

import static data.Helpers.Graphics.QuickLoadTileTex;

import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.Graphics;
import data.Helpers.renderableObj;
import data.level.Level;

public class Entity extends renderableObj{
	
	protected float y, width, height;
	protected Texture texture;
	protected Texture[] textures;
	protected boolean solid = false;
	protected Level level;
	protected float speed, topSpeed;
	protected int dir = 0;
	protected boolean moving = false;
	
	public Entity(float x, float y, float z, float width, float height, Texture texture, Level level){
		this.x = x;
		this.y = y;
		this.z = -Main.HEIGHT + y + height + 0.5f;
		this.width = width;
		this.height = height;
		this.textures = loadTexture();
		this.level = level;
	}
	
	

	protected Texture[] loadTexture(){
		return textures;
		}
	
	protected boolean outOfBounds(float x, float y) {
		if((x + width) >= Graphics.WorldSizeX){
			return true;
		}else if((y + height) >= Graphics.WorldSizeY){
			return true;
		}
		if((x) <= 0){
			return true;
		}else if((y) <= 0){
			return true;
		}
		return false;
	}
	
	public void update(){}
	
	public void draw(){}
	
	
	protected void move(float xa, float ya){
		moving = false;
		if( !level.getTile(xa + width/2,ya + height/2).solid()&& !outOfBounds(xa, ya)){
			x = xa;
			y = ya;
			z = -Main.HEIGHT + height + 0.5f + ya;
			moving = true;
		}	
	}
	
	protected void updateTex() {
		
	}
	
	protected void setSpeed(float s){
		speed = s;
	}
	
	public void setTopSpeed(float s){
		topSpeed = s;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}

}
