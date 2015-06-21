package entities;

import static data.Helpers.Graphics.*;
import inventory.Inventory;
import level.Level;
import gameobjects.GameObject;
import gameobjects.ObjectType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import commands.Command;

import data.Main;
import data.Helpers.Graphics;
import data.tiles.Tile;
import static data.Helpers.Graphics.*;


public class Player extends Entity {


	public boolean solid = true;
	private int anim = 0;
	private GameObject rocket;
	private Entity animal;
	private Texture[] texturesM;
	private Inventory inventory;
	
	boolean jumping = false;
	int jumpingCounter;
	int jumpFlag = 0;
	private boolean mounted;
	private boolean riding = false;

	public Player(float x, float y, float z, float width, float height, Texture texture, Level level) {
		super(x, y, z, width, height, texture, level);
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		this.inventory = new Inventory();
	}

	public void update() {
		
		// Det är jävligt mycket fel och skit här nu... måste strukturera om
		// planen är att update i player ska ta hand om allt. djuren ska bara ha commands... commands bestäms här!
		// vet inte om det är så efektivt men det är nog enklast. 
		
		// Det är knas här, och i rabbit och i entity. Spåra skiten via update.. det är typ där allt händer
		// bara att gå igenom sevensvis så har du det!
		
		if (anim < 7500) {
			anim++;
		}
		else
			anim = 0;
		
		boolean up, down, left, right, jump, mount;
		float ya, xa;
		ya = speed;
		xa = speed;
		up = Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP);
		down = Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		left= Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		right = Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		jump = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		mount = Mouse.isButtonDown(0);
		
		
		moving = false;
		if(riding){
			up = true;
			down = false;
			mount = false;
			setMounted(animal, true, 10);
			moving = true;
		}
		
		if(mount){
			mount(mount);
		}else{
			mountflag = false;
		}
		
		
		if(jump){
			jump();
		}else{
			jumpFlag = 0;
			jumping = false;
		}
		if(left){
			dir = 3;
			move(x - xa, y, jumping);
			
		}
		if(right){
			dir = 2;
			move(x + xa, y, jumping);
			
		}
		if(up){
			dir = 1;
			move(x,y - ya, jumping);
			
		}
		if(down){
			dir = 0;
			move(x,y + ya, jumping);
			
		}
		
		if(mounted){
			updateMountTex();
		}else if(jumping){
			updateJumpTex();
		}else{
			setSpeed(level.getTile(x + xa + width/2,y + ya + height ).getSpeed(speed));
			updateTex();
		}
		
	}
	

	boolean mountflag = false;
	private void mount(boolean mount) {
		if(mount){
			Entity a = animal;
			int Mx = -(int)getCamX() + Mouse.getX();
			int My = -(int)getCamY() + (Main.HEIGHT - Mouse.getY());
			Rectangle r = new Rectangle((int)a.getX(), (int)a.getY(), (int)a.getWidth(), (int)a.getHeight());
			if(r.contains(Mx, My) && !mountflag){
				if(!mounted){
					mounted = true;
					setMounted(a, mounted, a.mountspeed);
					mountflag = true;
				}else{
					mounted = false;
					setMounted(a, mounted, a.dismountspeed);
					mountflag = true;
				}
			}
		}
		
	}

	private void jump() {
		if(jumpFlag == 0){
			jumpFlag = 1;
			jumping = true;
		}
		if(jumping && jumpFlag == 1){
			jumpingCounter++;
			if(jumpingCounter > 40){
				jumpingCounter = 0;
				jumping = false;				
			}
		}
		
	}

	
	public void setSpeed(float s){
		this.speed = s;
	}
	
	public float getY(){
		return y;
	}

	public void setMounted(Entity a, boolean mounted, int speed) {
		this.animal = a;
		this.mounted = mounted;
		if(mounted){
			a.setCommand(Command.Mounted);
		}else{
			
			a.setCommand(Command.FollowPlayer);
		}	
		setSpeed(speed);
		
	}

	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	public void riding(boolean b) {
		this.riding = b;
		
		
	}

	public void setAnimal(Entity e) {
		animal = e;
		
	}

	public void draw() {
		
		drawQuadEntityTex(texture, x, y, z, width, height);
		
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
				if (anim % 20 > 10) {
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
		width = texture.getImageWidth();
		height = texture.getImageHeight();
	}
	
	public void updateMountTex(){
		if (dir == 0) {
			texture = texturesM[0];
			if (moving) {
				if (anim % 20 > 10) {
					texture = texturesM[1];
				} else {
					texture = texturesM[2];
				}

			}
		}
		if (dir == 1) {
			texture = texturesM[3];
			if (moving) {
				if (anim % 20 > 10) {
					texture = texturesM[4];
				} else {
					texture = texturesM[5];
				}
			}
		}
		if (dir == 2) {
			texture = texturesM[6];
			if (moving) {
				if (anim % 20 > 10) {
					texture = texturesM[7];
				} else {
					texture = texturesM[8];
				}
			}
		}

		if (dir == 3) {
			texture = texturesM[9];
			if (moving) {
				if (anim % 20 > 10) {
					texture = texturesM[10];
				} else {
					texture = texturesM[11];
				}
			}
		}
		width = texture.getImageWidth();
		height = texture.getImageHeight();
	}
	
	public void updateJumpTex(){
		if (dir == 0) {
			if(jumping){
				if (jumpingCounter % 20 > 10) {
					texture = textures[1];
				} else {
					texture = textures[2];
				}
			}
		}
		if (dir == 1) {
			if(jumping){
				if (jumpingCounter % 20 > 10) {
					texture = textures[12];
				} else {
					texture = textures[13];
				}
			}
		}
		if (dir == 2) {
			if(jumping){
				if (jumpingCounter % 20 > 10) {
					texture = textures[15];
				} else {
					texture = textures[16];
				}
			}
		}

		if (dir == 3) {
			if(jumping){
				if (jumpingCounter % 20 > 10) {
					texture = textures[18];
				} else {
					texture = textures[19];
				}
			}
		}
		width = texture.getImageWidth();
		height = texture.getImageHeight();
	}
	
	public Texture[] loadTexture(){
		textures = new Texture[24];
		
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
		
		textures[12] = QuickLoadPlayerTex("spel_fram_1");
		textures[13] = QuickLoadPlayerTex("spel_fram");
		textures[14] = QuickLoadPlayerTex("spel_fram_2");
		textures[15] = QuickLoadPlayerTex("spel_bakifran_1");
		textures[16] = QuickLoadPlayerTex("spel_bakifran");
		textures[17] = QuickLoadPlayerTex("spel_bakifran_2");
		textures[18] = QuickLoadPlayerTex("spel_hoger");
		textures[19] = QuickLoadPlayerTex("spel_hoger_1");
		textures[20] = QuickLoadPlayerTex("spel_hoger_2");
		textures[21] = QuickLoadPlayerTex("spel_vanster");
		textures[22] = QuickLoadPlayerTex("spel_vanster_1");
		textures[23] = QuickLoadPlayerTex("spel_vanster_2");
		
		
		
		texture = textures[0];
		texturesM = loadMountTex();
		return textures;
	}
	
	private Texture[] loadMountTex(){
		texturesM = new Texture[12];
		texturesM[0] = QuickLoadPlayerTex("saga_kanin_fram");
		texturesM[1] = QuickLoadPlayerTex("saga_kanin_skutt_fram");
		texturesM[2] = QuickLoadPlayerTex("saga_kanin_fram");
		texturesM[3] = QuickLoadPlayerTex("saga_kanin_bak");
		texturesM[4] = QuickLoadPlayerTex("saga_kanin_skutt_bak_1");
		texturesM[5] = QuickLoadPlayerTex("saga_kanin_skutt_bak_2");
		texturesM[6] = QuickLoadPlayerTex("saga_kanin_hoger");
		texturesM[7] = QuickLoadPlayerTex("saga_kanin_skutt_hoger_1");
		texturesM[8] = QuickLoadPlayerTex("saga_kanin_hoger");
		texturesM[9] = QuickLoadPlayerTex("saga_kanin_vanster");
		texturesM[10] = QuickLoadPlayerTex("saga_kanin_skutt_vanster_1");
		texturesM[11] = QuickLoadPlayerTex("saga_kanin_vanster");
		return texturesM;
	}
	
	

	

}
