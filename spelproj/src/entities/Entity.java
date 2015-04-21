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
	protected float speed;
	protected int dir = 0;
	protected boolean moving = false;
	
	public Entity(float x, float y, float z, float width, float height, Texture texture, Level level){
		this.x = x;
		this.y = y;
		this.z = -Main.HEIGHT + y + height;
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
	
	
	protected void move(boolean up, boolean down, boolean left, boolean right){
		float ya, xa;
		ya = speed;
		xa = speed;
		
		/*if((up || down) && (left || right)){
			ya = speed / 2f;
			xa = speed / 2f;
		}*/
		boolean moved = false;
		if(left && !level.getTile(x + width/2- xa,y + height/2).solid()&& !outOfBounds(x - xa, y)){
			x = x - xa;
			dir = 3;
			moved= true;
			setSpeed(level.getTile(x + width/2- xa,y + height/2).getSpeed());
				
			
		}
		if(right && !level.getTile(x + width/2+ xa,y + height/2).solid()&& !outOfBounds(x + xa, y)){
			x = x + xa;
			dir = 2;
			moved= true;
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed());
		}
		if(up && !level.getTile(x + width/2, y + height/2 - ya).solid() && !outOfBounds(x, y - ya)){
			y = y - ya;
			z -= ya;
			dir = 1;
			moved= true;
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed());
				
			
		}
		if(down && !level.getTile(x + width/2, y + height/2 + ya).solid()&& !outOfBounds(x, y + ya)){
			y = y + ya;
			z += ya;
			dir = 0;
			moved= true;
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed());
				
			
		}
		moving = moved;
		updateTex();
		
	}
	
	protected void updateTex() {
		
	}
	
	protected void setSpeed(float s){
		speed = s;
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
