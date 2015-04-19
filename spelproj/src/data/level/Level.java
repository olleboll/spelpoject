package data.level;

import java.util.ArrayList;

import data.Main;
import data.tiles.Tile;
import data.tiles.TileType;
import entities.Player;
import gameobjects.GameObject;
import gameobjects.ObjectType;
import data.Helpers.*;

public class Level {
	
	public Tile[][] map;
	public GameObject tree;
	private final int SIZE = 64;
	private Player player;
	private ArrayList<renderableObj> objects;
	
	public Level(){
		objects = new ArrayList<renderableObj>();
		this.player = new Player(100, 250, -250, 64, 64, null);
		objects.add(player);
		setUpLevel();
	}
	
	
	private void setUpLevel(){
		setUpTiles();
		setUpObjects();
		
		
	}
	
	private void setUpObjects() {
		tree = new GameObject( 64, 64, 128, 128, ObjectType.Tree);
		objects.add(tree);
		tree = new GameObject( 64, 128, 128, 128, ObjectType.Tree);
		objects.add(tree);
		tree = new GameObject( 64, 192, 128, 128, ObjectType.Tree);
		objects.add(tree);
		tree = new GameObject( 64, 256, 128, 128, ObjectType.Tree);
		objects.add(tree);
		tree = new GameObject( 64, 320, 128, 128, ObjectType.Tree);
		objects.add(tree);
		tree = new GameObject( 64, 384, 128, 128, ObjectType.Tree);
		objects.add(tree);
		System.out.println(objects.size());
	}
	// här kan du läsa in på samma sätt som förut tror jag. Kolla din array med färger och
	// lägg ut de tiles som motsvarar, busenkelt?
	private void setUpTiles() {
		map = new Tile[SIZE][SIZE];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){
				map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
			}
		}
	}

	public void update() {
		player.update();
		
	}
	
	public void draw(){
		drawTiles();
		drawObjects();
		
	}

	private void drawObjects() {
		
		objects = sortObjects();
		
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).draw();
		}
		
	}


	private ArrayList<renderableObj> sortObjects() {
		ArrayList<renderableObj> sorted = new ArrayList<renderableObj>();
		for(int i = 0; i < objects.size(); i++){
			sorted.add(objects.get(i));
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


	private void drawTiles() {
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map.length; j++){
				Tile t = map[i][j];
				t.draw();
			}
		}
		
	}

	

}
