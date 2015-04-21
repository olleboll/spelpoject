package entities;

import static data.Helpers.Graphics.drawQuadTex;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import data.Helpers.Graphics;
import data.level.Level;
import static data.Helpers.Graphics.*;


public class Player extends Entity {


	public boolean solid = true;
	private int anim = 0;

	public Player(float x, float y, float z, float width, float height, Texture texture, Level level) {
		super(x, y, z, width, height, texture, level);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		
		if (anim < 7500) {
			anim++;
		}
		else
			anim = 0;
		
		boolean up, down, left, right;
		up = Keyboard.isKeyDown(Keyboard.KEY_W);
		down = Keyboard.isKeyDown(Keyboard.KEY_S);
		left= Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);
		
		if(!collision()){
			move(up, down, left, right);
		}
		
		
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
	}
	
	public Texture[] loadTexture(){
		textures = new Texture[12];
		textures[0] = QuickLoadPlayerTex("spel_fram");
		textures[1] = QuickLoadPlayerTex("spel_fram_1");
		textures[2] = QuickLoadPlayerTex("spel_fram_2");
		textures[3] = QuickLoadPlayerTex("spel_bakifran");
		textures[4] = QuickLoadPlayerTex("spel_bakifran_1");
		textures[5] = QuickLoadPlayerTex("spel_bakifran_2");
		textures[6] = QuickLoadPlayerTex("spel_hoger");
		textures[7] = QuickLoadPlayerTex("spel_hoger_1");
		textures[8] = QuickLoadPlayerTex("spel_hoger_2");
		textures[9] = QuickLoadPlayerTex("spel_vanster");
		textures[10] = QuickLoadPlayerTex("spel_vanster_1");
		textures[11] = QuickLoadPlayerTex("spel_vanster_2");
		return textures;
	}
	
	public float getY(){
		return y;
	}

	

}
