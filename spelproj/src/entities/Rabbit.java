package entities;

import static data.Helpers.Graphics.*;
import level.Level;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import commands.Command;
import data.Main;
import events.Event;
import static data.Helpers.Graphics.*;

public class Rabbit extends Entity {
	
	public boolean solid = true;
	private Player player;
	private boolean mounted;
	
	
	public Rabbit(float x, float y, float z, float width, float height,
			Texture texture, Level level) {
		super(x, y, z, width, height, texture, level);
		// TODO Auto-generated constructor stub
	}

	public void setPlayer(Player player){
		this.player = player;
		z = z - 2.5f;
		
	}
	
	protected void setSpeeds(){
		this.mountspeed = 6;
		this.dismountspeed = 3;
	}
	
	public void setEvent(Event event){
		this.event = event;
	}

	public void update() {
		if(command == Command.Mounted){
			mounted(player);
		}
		else if(command == Command.FollowPlayer){
			followPlayer(player);
		}else if(command == Command.Ride){
			//mount(true);
			player.riding(true);
			Ride(player);
		}
		
	}
	/*boolean mountflag = false;
	private void mount(boolean mount) {
		if(mount){
			int Mx = -(int)getCamX() + Mouse.getX();
			int My = -(int)getCamY() + (Main.HEIGHT - Mouse.getY());
			Rectangle r = new Rectangle((int)x, (int)y, (int)width, (int)height);
			if(r.contains(Mx, My) && !mountflag){
				if(!mounted){
					mounted = true;
					player.setMounted(this, mounted, mountspeed);
					mountflag = true;
				}else{
					mounted = false;
					player.setMounted(this, mounted, dismountspeed);
					mountflag = true;
				}
			}
		}else{
			mountflag = false;
		}
		
	}*/

	private boolean collision() {
		 
		return false;
	}
	
	public void setSpeed(float s){
		this.speed = s;
	}

	public void draw() {
		if(!mounted){
			drawQuadEntityTex(texture, x, y, z, width, height);
		}
		
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
	
	

	


}
