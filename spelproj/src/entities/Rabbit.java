package entities;

import static data.Helpers.Graphics.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import data.commands.Command;
import data.events.Event;
import data.level.Level;
import static data.Helpers.Graphics.*;

public class Rabbit extends Entity {
	
	public boolean solid = true;
	private Player player;
	private Command command;
	private boolean mounted;
	private int mountspeed = 6;
	private int dismountspeed = 3;
	
	public Rabbit(float x, float y, float z, float width, float height,
			Texture texture, Level level) {
		super(x, y, z, width, height, texture, level);
		// TODO Auto-generated constructor stub
	}

	public void setPlayer(Player player){
		this.player = player;
		z = z - 2.5f;
		this.command = Command.FollowPlayer;
	}
	
	public void setEvent(Event event){
		this.event = event;
	}

	public void update() {
		
		boolean mount = Mouse.isButtonDown(0);
		
		mount(mount);
		
		if(mounted){
			x = player.x;
			y = player.y;
			z = player.z;
			return;
		}
		
		if(command == Command.FollowPlayer){
			followPlayer(player);
		}
		
	}
	boolean mountflag = false;
	private void mount(boolean mount) {
		if(mount){
			int x = Mouse.getX();
			int y = Mouse.getY();
			Rectangle r = new Rectangle((int)x, (int)y, (int)width, (int)height);
			if(r.contains(x, y) && !mountflag){
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
