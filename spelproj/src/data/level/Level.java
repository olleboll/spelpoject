package data.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.Main;
import data.events.EventsHandler;
import data.objectives.Objective;
import data.objectives.ObjectiveHandler;
import data.tiles.Tile;
import data.tiles.TileType;
import entities.Entity;
import entities.Player;
import entities.Rabbit;
import gameobjects.GameObject;
import gameobjects.ObjectType;
import data.Helpers.*;

public class Level {

	public Tile[][] map;
	protected int[] levelpixels;
	public int SIZEX, SIZEY;
	protected final int SIZE = 64;
	protected ArrayList<renderableObj> objects;
	protected ArrayList<Entity> entities;
	protected ArrayList<Tile> tiles;
	protected float camX, camY;
	protected ObjectiveHandler missions;
	protected Objective start;
	protected EventsHandler events;

	public Level(String path, int sizeX, int sizeY) {
		loadLevel(path);
		setUpLevel();
		
	}
	
	protected void loadLevel(String path){}
	
	protected void setSize(int x, int y){
		SIZEX = x;
		SIZEY = y;
	}

	protected void setUpLevel(){
		
	}
	
	public void update() {

	}

	protected void updateCamera() {

	}

	public void draw() {

	}

	protected void drawObjects() {

	}
	
	protected ArrayList<renderableObj> getEntites(ArrayList<renderableObj> obj) {		
		for(Entity e : entities){
			obj.add(e);
		}
		return obj;
	}

	protected ArrayList<renderableObj> getVisibleObj(){
		ArrayList<renderableObj> obj = new ArrayList<renderableObj>();
		for(int x = (int) camX / SIZE - 4; x < camX/SIZE + Main.WIDTH/SIZE + 4; x++){
			for(int y = (int) camY / SIZE - 4; y < camY/SIZE + Main.HEIGHT/SIZE + 4; y++){
				if(x < 0 || x > SIZEX - 1){
					break;
				}
				if(y >= 0 && y < SIZEY){
					if(map[x][y].obj != null){
						for(int i = 0; i<map[x][y].obj.size();i++ ){
							obj.add(map[x][y].obj.get(i));
						}						
					}
				}
			}
		}
		return obj;
	}

	protected ArrayList<renderableObj> sortObjects(ArrayList<renderableObj> objects2) {
		ArrayList<renderableObj> sorted = new ArrayList<renderableObj>();
		for (int i = 0; i < objects2.size(); i++) {
			sorted.add(objects2.get(i));
		}

		for (int i = 0; i < sorted.size(); i++) {
			renderableObj obj = sorted.get(i);
			for (int j = 0; j < i; j++) {
				renderableObj Sobj = sorted.get(j);
				if (Sobj.getZ() > obj.getZ()) {
					sorted.remove(i);
					sorted.add(j, obj);
					break;
				}
			}
		}
		return sorted;
	}
	
	protected Tile[][] getVisibleTiles(){
		Tile[][] VT = new Tile[Main.WIDTH / SIZE + 5][Main.HEIGHT / SIZE + 5 ];
		int xc = 0;
		int yc = 0;
		for(int x = (int) camX / SIZE; x < (int)(camX + Main.WIDTH) / SIZE + 3; x++){
			
			for(int y = (int) camY / SIZE; y < (int)(camY + Main.HEIGHT) / SIZE + 3; y++){
				if(x < 0 || x > SIZEX - 1){
					xc--;
					break;
				}
				if(y >= 0 && y < SIZEY){
					VT[xc][yc] = map[x][y];
					yc++;
				}
				
			}
			yc = 0;
			xc++;
		}
		
		return VT;
	}

	protected void drawTiles() {
		Tile[][] VT = new Tile[Main.WIDTH / SIZE +5][Main.HEIGHT / SIZE +5];
		VT = getVisibleTiles();
		for (int x = 0; x < Main.WIDTH / SIZE +5; x++) {
			for (int y = 0; y < Main.HEIGHT / SIZE +5; y++) {
				Tile t = VT[x][y];
				if(t != null){
					t.draw();
				}
			}
		}

	}
	
	public Tile getTile(float x, float y){
		int xi = (int)x / SIZE;
		int yi = (int)y / SIZE;
		return map[xi][yi];
	}
	
	protected void setUpEntities() {

	}	

	protected void setUpObjects(int x, int y) {

	}

	protected void setUpTiles(int x, int y) {

	}

		


		

}
