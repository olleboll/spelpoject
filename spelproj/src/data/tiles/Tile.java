package data.tiles;

import java.util.ArrayList;

import gameobjects.GameObject;

import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.renderableObj;
import static data.Helpers.Graphics.*;

public class Tile {

	private float x, y, z, width, height;
	private Texture texture;
	private TileType type;
	private boolean solid = false;
	public ArrayList<GameObject> obj;

	public Tile(float x, float y, float width, float height, TileType type) {
		this.x = x;
		this.y = y;
		this.z = -WorldSizeY;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = QuickLoadTileTex(type.textureName);
	}

	public void draw() {
		if (type.flip != 0) {
			if (type.flip == 1) {
				drawQuadTexFlip90(texture, x, y, z, width, height);
			}
			if (type.flip == 2) {
				drawQuadTexFlip180(texture, x, y, z, width, height);
			}
			if (type.flip == 3) {
				drawQuadTexFlip270(texture, x, y, z, width, height);
			}
		}else{
			drawQuadObjectTex(texture, x, y, z, width, height);
		}

	}

	public void setSolid(boolean s) {
		solid = s;
	}

	public boolean solid() {
		return solid;
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

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public float getSpeed(float s) {
		
		if(obj == null) {
			if(s <=type.speed){
				return type.speed;
			}
			return s;
		}
		return obj.get(0).getSpeed();
	}

	public void setObj(GameObject o) {
		if(obj == null){
			obj = new ArrayList<GameObject>();
		}
		obj.add(o);
		
	}

}
