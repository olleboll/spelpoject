package data.tiles;

import java.util.ArrayList;

import gameobjects.GameObject;

import org.newdawn.slick.opengl.Texture;

import data.Main;
import data.Helpers.RenderHelper;
import data.Helpers.renderableObj;
import static data.Helpers.Graphics.*;

public class Tile {

	private float x, y, z, width, height;
	private Texture texture;
	private Texture[] textures;
	private TileType type;
	private boolean solid = false;
	public ArrayList<GameObject> obj;
	
	private int anim = 0;
	public boolean animation = false;

	public Tile(float x, float y, float width, float height, TileType type) {
		this.x = x;
		this.y = y;
		this.z = -WorldSizeY;
		this.width = width;
		this.height = height;
		this.type = type;
		this.textures = LoadTextures();
	}
	
	private Texture[] LoadTextures(){
		if(type == TileType.Water){
			textures = new Texture[4];
			textures[0] = QuickLoadTileTex(type.textureName + "_1");
			textures[1] = QuickLoadTileTex(type.textureName + "_2");
			textures[2] = QuickLoadTileTex(type.textureName + "_3");
			textures[3] = QuickLoadTileTex(type.textureName + "_4");
			animation = true;
		}else if(type == TileType.Grass){
			textures = new Texture[1];
			int g = RenderHelper.GrassHelper();
			if(g == 1){
				textures[0] = QuickLoadTileTex(type.textureName);
			}
			if(g == 2){
				textures[0] = QuickLoadTileTex(type.textureName+"_1");			
			}
			if(g == 3){
				textures[0] = QuickLoadTileTex(type.textureName+"_2");
			}
			if(g == 4){
				textures[0] = QuickLoadTileTex(type.textureName+"_3");
			}
			if(g == 5){
				textures[0] = QuickLoadTileTex(type.textureName+"_4");
			}
			if(g == 0){
				textures[0] = QuickLoadTileTex(type.textureName+"_5");
			}
		}else{
			textures = new Texture[1];
			textures[0] = QuickLoadTileTex(type.textureName);
		}
		texture = textures[0];
		return textures;
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
	
	public void update(){
		if(anim > 50){
			anim = 0;
		}else{
			anim++;
		}
		
		if(anim < 4 * 50 / 4){
			texture = textures[0];
		}
		if(anim < 3* 50 / 4){
			texture = textures[1];
		}

		if(anim < 2 * 50 / 4){
			texture = textures[2];
		}
		if(anim <  50 / 4){
			texture = textures[3];
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
			return type.speed;
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
