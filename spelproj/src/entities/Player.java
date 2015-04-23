package entities;

import static data.Helpers.Graphics.drawQuadTex;
import gameobjects.GameObject;
import gameobjects.ObjectType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import data.Helpers.Graphics;
import data.level.Level;
import data.tiles.Tile;
import static data.Helpers.Graphics.*;


public class Player extends Entity {


	public boolean solid = true;
	private int anim = 0;
	private GameObject rocket;

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
		
		float ya, xa;
		ya = speed;
		xa = speed;
		boolean moved = false;
		if(left && !level.getTile(x + width/2- xa,y + height/2).solid()&& !outOfBounds(x - xa, y)){
			x = x - xa;
			dir = 3;
			//move(dir);
			setSpeed(level.getTile(x + width/2- xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		if(right && !level.getTile(x + width/2+ xa,y + height/2).solid()&& !outOfBounds(x + xa, y)){
			x = x + xa;
			dir = 2;
			//move(dir);
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		if(up && !level.getTile(x + width/2, y + height/2 - ya).solid() && !outOfBounds(x, y - ya)){
			y = y - ya;
			z -= ya;
			dir = 1;
			//move(dir);
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		if(down && !level.getTile(x + width/2, y + height/2 + ya).solid()&& !outOfBounds(x, y + ya)){
			y = y + ya;
			z += ya;
			dir = 0;
			setSpeed(level.getTile(x + width/2+ xa,y + height/2).getSpeed(speed));
			moved = true;
		}
		
		checkwin();
		
		moving = moved;
		updateTex();
	}
	
	private void checkwin(){
		Tile t = level.getTile(x + width/2,y + height/2);
		if(t.obj != null){
			if(t.obj.get(0).getType() == ObjectType.Rocket){
				topSpeed = 0;
				speed = 0;
				System.out.println("wogooo!!");
			}
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
		texture = textures[0];
		return textures;
	}
	
	public float getY(){
		return y;
	}

	public void setGoal(GameObject rocket) {
		this.rocket = rocket;
		
	}

	

}
