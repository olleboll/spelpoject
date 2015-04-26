package entities;

import static data.Helpers.Graphics.*;

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
	protected boolean moved = false;
	
	public Entity(float x, float y, float z, float width, float height, Texture texture, Level level){
		this.x = x;
		this.y = y;
		this.z = -WorldSizeY + y + height + 0.5f;
		this.textures = loadTexture();
		this.width = this.texture.getImageWidth();
		this.height = this.texture.getImageHeight();
		this.level = level;
		System.out.println(width);
		System.out.println(height);
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
		moved = false;
		if( !level.getTile(xa + width/2,ya + height ).solid()&& !outOfBounds(xa, ya)){
			x = xa;
			y = ya;
			z =  height + 0.5f + ya;
			moving = true;
			moved = true;
			setSpeed(level.getTile(xa + width/2,ya + height ).getSpeed(speed));
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
