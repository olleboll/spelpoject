package entities;

import static data.Helpers.Graphics.QuickLoadPlayerTex;
import static data.Helpers.Graphics.drawQuadTex;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import data.level.Level;
import static data.Helpers.Graphics.*;

public class Rabbit extends Entity {
	
	public boolean solid = true;
	private int anim = 0;
	private Player player;
	
	public Rabbit(float x, float y, float z, float width, float height,
			Texture texture, Level level) {
		super(x, y, z, width, height, texture, level);
		// TODO Auto-generated constructor stub
	}

	public void setPlayer(Player player){
		this.player = player;
		z = z - 1;
	}

	public void update() {
		
		if (anim < 7500) {
			anim++;
		}
		else
			anim = 0;
		
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		float xa = speed, ya = speed;	
		float xs = getXDir();

		float ys = getYDir();
		
		if(xs < -speed){
			left = true;
		}else if (xs > speed){
			right = true;
		}
		if(ys < -5){
			up = true;
		}else if(ys > 5){
			down = true;
		}
		if(closeto()){
			up = false;
			down = false;
			right = false;
			left= false;
		}
		boolean moved = false;
		if(left && !level.getTile(x + width/2- xa,y + height/2).solid()&& !outOfBounds(x - xa, y)){
			x = x - xa;
			dir = 3;
			setSpeed(level.getTile(x + width/2- xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		if(right && !level.getTile(x + width/2+ xa,y + height/2).solid()&& !outOfBounds(x + xa, y)){
			x = x + xa;
			dir = 2;
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		if(up && !level.getTile(x + width/2, y + height/2 - ya).solid() && !outOfBounds(x, y - ya)){
			y = y - ya;
			z -= ya;
			dir = 1;
			setSpeed(level.getTile(x + width/2,y + height/2 - ya).getSpeed(speed));
			moved = true;
		}
		if(down && !level.getTile(x + width/2, y + height/2 + ya).solid()&& !outOfBounds(x, y + ya)){
			y = y + ya;
			z += ya;
			dir = 0;
			System.out.println(level.getTile(x + width/2,y + height/2 + ya).getType());
			setSpeed(level.getTile(x + width/2,y + height/2 + ya).getSpeed(speed));		
			moved = true;
		}
		moving = moved;
		updateTex();
	}
	
	private boolean closeto(){
		Rectangle r = new Rectangle((int)x, (int)y, (int)width, (int)height);
		Rectangle p = new Rectangle((int)player.getX() - 25, (int)player.getY() - 25, (int)player.getWidth() + 50, (int)player.getHeight() + 50);
		if(r.intersects(p) || p.intersects(r) || p.contains(r)){
			return true;
		}
		return false;
	}
	
	private float getXDir() {	
		return player.getX() - x;
		
	}
	
	private float getYDir(){
		return player.getY() - y;
	}



	private boolean collision() {
		
		return false;
	}
	
	public void setSpeed(float s){
		this.speed = s;
	}

	public void draw() {
		
		drawQuadTex(texture, x, y, z, width, height);
		//System.out.println(z);
	}
	
	public void updateTex(){
		
		if (dir == 0) {
			texture = textures[0];
			if (moving) {
				if (anim % 20 > 10) {
					texture = textures[1];
				} else {
					texture = textures[2];
				}

			}
		}
		if (dir == 1) {
			texture = textures[3];
			if (moving) {
				if (anim % 20 > 10) {
					texture = textures[4];
				} else {
					texture = textures[5];
				}
			}
		}
		if (dir == 2) {
			texture = textures[6];
			if (moving) {
				if (anim % 20 < 10) {
					texture = textures[7];
				} else {
					texture = textures[8];
				}
			}
		}

		if (dir == 3) {
			texture = textures[9];
			if (moving) {
				if (anim % 20 > 10) {
					texture = textures[10];
				} else {
					texture = textures[11];
				}
			}
		}
		//texture = textures[0];
	}
	
	public Texture[] loadTexture(){
		textures = new Texture[12];
		textures[0] = QuickLoadEntityTex("kanin_fram");
		textures[1] = QuickLoadEntityTex("kanin_fram");
		textures[2] = QuickLoadEntityTex("kanin_fram");
		textures[3] = QuickLoadEntityTex("kanin_bak");
		textures[4] = QuickLoadEntityTex("kanin_bak");
		textures[5] = QuickLoadEntityTex("kanin_bak");
		textures[6] = QuickLoadEntityTex("kanin_hoger");
		textures[7] = QuickLoadEntityTex("kanin_hoger");
		textures[8] = QuickLoadEntityTex("kanin_hoger");
		textures[9] = QuickLoadEntityTex("kanin_vanster");
		textures[10] = QuickLoadEntityTex("kanin_vanster");
		textures[11] = QuickLoadEntityTex("kanin_vanster");
		texture = textures[0];
		return textures;
	}
	
	public float getY(){
		return y;
	}

	


}
