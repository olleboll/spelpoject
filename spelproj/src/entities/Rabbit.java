package entities;

import static data.Helpers.Graphics.*;

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
		z = z - 2.5f;
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
		if(ys < -speed){
			up = true;
		}else if(ys > speed){
			down = true;
		}
		if(closeto()){
			up = false;
			down = false;
			right = false;
			left= false;
		}
		moving = false;
		if(left){
			move(x - xa, y);
			if(moved){
				dir = 3;
			}
			
		}
		if(right){
			move(x + xa, y);
			if(moved){
				dir = 2;
			}
		
		}
		if(up){
			move(x,y - ya);
			if(moved){
				dir = 1;
			}
			
		}
		if(down){
			move(x,y + ya);
			if(moved){
				dir = 0;
			}
			
		}
		if(z == player.getZ()){
			z+=1;
		}
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
		
		drawQuadEntityTex(texture, x, y, z, width, height);
		//System.out.println(z);
	}
	
	public void updateTex(){
		
		if (dir == 0) {
			texture = textures[0];
			if (moving) {
				if (anim % 20 > 5) {
					texture = textures[1];
				} else {
					texture = textures[2];
				}

			}
		}
		if (dir == 1) {
			texture = textures[3];
			if (moving) {
				if (anim % 20 > 5) {
					texture = textures[4];
				} else {
					texture = textures[5];
				}
			}
		}
		if (dir == 2) {
			texture = textures[6];
			if (moving) {
				if (anim % 20 > 5) {
					texture = textures[7];
				} else {
					texture = textures[8];
				}
			}
		}

		if (dir == 3) {
			texture = textures[9];
			if (moving) {
				if (anim % 20 > 5) {
					texture = textures[10];
				} else {
					texture = textures[11];
				}
			}
		}
		
		width = texture.getImageWidth();
		height = texture.getImageHeight();
	}
	
	public Texture[] loadTexture(){
		textures = new Texture[12];
		textures[0] = QuickLoadEntityTex("kanin/kanin_fram");
		textures[1] = QuickLoadEntityTex("kanin/skutt_fram");
		textures[2] = QuickLoadEntityTex("kanin/kanin_fram");
		textures[3] = QuickLoadEntityTex("kanin/kanin_bak");
		textures[4] = QuickLoadEntityTex("kanin/skutt_bak");
		textures[5] = QuickLoadEntityTex("kanin/skutt_bak_2");
		textures[6] = QuickLoadEntityTex("kanin/kanin_hoger");
		textures[7] = QuickLoadEntityTex("kanin/skutt_hoger");
		textures[8] = QuickLoadEntityTex("kanin/kanin_hoger");
		textures[9] = QuickLoadEntityTex("kanin/kanin_vanster");
		textures[10] = QuickLoadEntityTex("kanin/skutt_vanster");
		textures[11] = QuickLoadEntityTex("kanin/kanin_vanster");
		texture = textures[0];
		return textures;
	}
	
	public float getY(){
		return y;
	}

	


}
