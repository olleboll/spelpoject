package gameobjects;

import static data.Helpers.Graphics.*;

import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.RenderHelper;
import data.Helpers.renderableObj;


public class GameObject extends renderableObj{
	
	private float  y, width, height;
	private Texture texture;
	private ObjectType type;
	private boolean solid = true;
	public float renderz = 0;

	public GameObject(float x, float y, float z,  float width, float height, ObjectType type) {
		this.x = x;
		this.y = y;
		this.z = -Main.HEIGHT + y + height + z;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = QuickLoadObjectTex(type.textureName);
	}
	

	public void draw() {
		drawQuadObjectTex(texture, x, y, z, width, height);
		//System.out.println(z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}
	
	public float getSpeed(){
		return type.speed;
	}
	public float getZ() {
		// TODO Auto-generated method stub
		return z + renderz;
	}


}
