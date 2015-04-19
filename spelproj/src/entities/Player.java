package entities;

import static data.Helpers.Graphics.drawQuadTex;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static data.Helpers.Graphics.*;


public class Player extends Entity {

	//private Keyboard input;
	private float speed = 2f;
	public boolean solid = true;

	public Player(float x, float y, float z, float width, float height, Texture texture) {
		super(x, y, z, width, height, texture);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		
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

	private void move(boolean up, boolean down, boolean left, boolean right){
		float ya, xa;
		ya = speed;
		xa = speed;
		if((up || down) && (left || right)){
			ya = speed / 1.2f;
			xa = speed / 1.2f;
		}
		if(up){
			y = y - ya;
			z -= ya;
		}
		if(down){
			y = y + ya;
			z += ya;
		}
		if(left){
			x = x - xa;
		}
		if(right){
			x = x + xa;
		}	
		
	}
	

	public void draw() {
		drawQuadTex(texture, x, y, z, width, height);
		//System.out.println(z);
	}
	
	public Texture loadTexture(){
		return QuickLoadPlayerTex("hjalte_fram");
	}

}
