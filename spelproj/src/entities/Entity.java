package entities;

import static data.Helpers.Graphics.*;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.Graphics;
import data.Helpers.renderableObj;
import data.events.Event;
import data.level.Level;

public class Entity extends renderableObj{
	
	
	public float width, height;
	protected Texture texture;
	protected Texture[] textures;
	protected boolean solid = false;
	protected Level level;
	public float speed;
	protected float topSpeed;
	public int dir = 0;
	public boolean moving = false;
	public boolean moved = false;
	protected Event event;
	public int anim;
	
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
	
	
	public void move(float xa, float ya, boolean jumping){
		moved = false;
		if( !level.getTile(xa + width/2,ya + height ).solid(jumping)&& !outOfBounds(xa, ya)){
			x = xa;
			y = ya;
			z =  height + 0.5f + ya;
			moving = true;
			moved = true;
			setSpeed(level.getTile(xa + width/2,ya + height ).getSpeed(speed));
		}
		
	}
	
	public void followPlayer(Player player){
		if (anim < 7500) {
			anim++;
		}
		else{
			anim = 0;
		}
		
		
		// Kaninen styrs av commands. Nedanför är i princip ett färdigt followplayer command.
		// events är saker som sker utan spelaren kontroll och spelaren är bara en observatör.
		// commands är saker som djuren kan göra.
		
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		float xa = speed, ya = speed;	
		
		float xs = player.getX() - x;
		float ys = player.getY() - y;
		
		
		if(xs < -speed){
			left = true;
		}else if (xs > speed){
			right = true;
		}
		if(ys < -speed){
			up = true;
		}else if(ys > speed){
			down = true;
		}
		if(closeto(player)){
			up = false;
			down = false;
			right = false;
			left= false;
		}
		moving = false;
		if(left){
			move(x - xa, y, false);
			if(moved){
				dir = 3;
			}
			
		}
		if(right){
			move(x + xa, y, false);
			if(moved){
				dir = 2;
			}
		
		}
		if(up){
			move(x,y - ya, false);
			if(moved){
				dir = 1;
			}
			
		}
		if(down){
			move(x,y + ya, false);
			if(moved){
				dir = 0;
			}
			
		}
		if(z == player.getZ()){
			z+=1;
		}
		updateTex();
		
	}
	
	private boolean closeto(Player player){
		Rectangle r = new Rectangle((int)x, (int)y, (int)width, (int)height);
		Rectangle p = new Rectangle((int)player.getX() - 25, (int)player.getY() - 25, (int)player.getWidth() + 50, (int)player.getHeight() + 50);
		if(r.intersects(p) || p.intersects(r) || p.contains(r)){
			return true;
		}
		return false;
	}
	
	public void updateTex() {
		
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
