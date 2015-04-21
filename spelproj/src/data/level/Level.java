package data.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.Main;
import data.tiles.Tile;
import data.tiles.TileType;
import entities.Player;
import gameobjects.GameObject;
import gameobjects.ObjectType;
import data.Helpers.*;

public class Level {

	public Tile[][] map;
	private int[] levelpixels;
	private final int SIZE = 64;
	private Player player;
	private ArrayList<renderableObj> objects;
	private float camX, camY;

	public Level(String path) {
		loadLevel(path);
		setUpLevel();
	}

	protected void loadLevel(String path) {
		try {
			System.out.println(path);
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			levelpixels = new int[w * h];
			System.out.println("laddar in...");
			image.getRGB(0, 0, w, h, levelpixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! could not load level file.");
		}
	}

	private void setUpLevel() {
		map = new Tile[SIZE][SIZE];
		objects = new ArrayList<renderableObj>();
		this.player = new Player(650,400, -400, 64, 64, null, this);
		objects.add(player);
		player.setSpeed(5);
		camX = player.getX() - Main.WIDTH / 2;
		camY = player.getY() - Main.HEIGHT / 2;
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// System.out.println(levelpixels[x + y * SIZE]);
				setUpTiles(x, y);

			}
		}
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// System.out.println(levelpixels[x + y * SIZE]);

				setUpObjects(x, y);
			}
		}

	}

	// F�RGER och TILES

	// rosa = -20791 -> flower
	// svart = -16777216 -> rock
	// brun = -4621737 -> tree

	private void setUpObjects(int x, int y) {

		if (zoff == 1) {
			zoff = -1;
		} else {
			zoff = 1;
		}
		if (levelpixels[x + y * SIZE] == -20791) {
			GameObject flower = new GameObject(x * SIZE, y * SIZE, zoff, 32, 32,
					ObjectType.Flower);
			objects.add(flower);
			GameObject flower2 = new GameObject(x * SIZE + 32, y * SIZE + 32, zoff, 32, 32,
					ObjectType.Flower);
			objects.add(flower2);
			GameObject flower3 = new GameObject(x * SIZE, y * SIZE + 32, zoff, 32, 32,
					ObjectType.Flower);
			objects.add(flower3);
			GameObject flower4 = new GameObject(x * SIZE + 32, y * SIZE, zoff, 32, 32,
					ObjectType.Flower);
			objects.add(flower4);

		} else if (levelpixels[x + y * SIZE] == -1621737) {
			// tree = new GameObject(64, 384, 128, 128, ObjectType.Tree);
			// objects.add(tree);
		} else if (levelpixels[x + y * SIZE] == -4621737) {
			// TR�D
			GameObject tree = new GameObject(x * SIZE - 16, y * SIZE - 64, zoff, 88, 128,
					ObjectType.Tree);
			objects.add(tree);
			map[x][y].setSolid(true);
			// map[x + 1][y + 2].setSolid(true);
		}

	}

	// h�r kan du l�sa in p� samma s�tt som f�rut tror jag. Kolla din array med
	// f�rger och
	// l�gg ut de tiles som motsvarar, busenkelt?

	// F�RGER och TILES
	// ljusgr�n = -4856291 -> grass
	// rosa = -20791 -> flower
	// svart = -16777216 -> rock
	// brun = -4621737 -> tree
	// m�rkbrun = -7864299 -> dirt
	// spygul = -14066 -> dirtD0
	// ljusgr� = -3947581 -> dirtD1
	// m�rkgr� = --8421505 -> dirtD2
	// gr�dvit = -1055568 -> dirtD3 
	int zoff = 1;
	int last;

	private void setUpTiles(int x, int y) {

		/*
		 * for (int i = 0; i < map.length; i++) { for (int j = 0; j <
		 * map.length; j++) { map[i][j] = new Tile(i * 64, j * 64, 64, 64,
		 * TileType.Grass); //map[x][y] = levelpixels[x + y * SIZE]; } }
		 */
		System.out.println(levelpixels[x + y * SIZE]);
		if (levelpixels[x + y * SIZE] == -20791) {
			// Tiles.add(x + y * width, Tile.flower);
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);

		} else if (levelpixels[x + y * SIZE] == -4856291) {
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);

		} else if (levelpixels[x + y * SIZE] == -7864299) {
			// Tiles.add(x + y * width, Tile.dirt);
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt);
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt);

		} else if (levelpixels[x + y * SIZE] == -4621737) {
			// TR�D
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);
		} else if (levelpixels[x + y * SIZE] == -14066) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D1);
			// 32
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
		}else if (levelpixels[x + y * SIZE] == -3947581) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D2);
			// 32
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
		}else if (levelpixels[x + y * SIZE] == -8421505) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D3);
			// 32
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
		}else if (levelpixels[x + y * SIZE] == -1055568) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D4);
			// 32
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
		} else {
			// Tiles.add(x + y * width, Tile.voidTile);
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
			// 32
			//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);
		}
	}

	public void update() {
		
		player.update();
		updateCamera();
	}

	private void updateCamera() {
		camX = player.getX() + (player.getWidth() / 2) - Main.WIDTH / 2;
		camY = player.getY() + (player.getHeight() / 2) - Main.HEIGHT / 2;
		if (camX > Graphics.offsetMaxX) {
			camX = Graphics.offsetMaxX;
		} else if (camX < Graphics.offsetMinX) {
			camX = Graphics.offsetMinX;
		}
		if (camY > Graphics.offsetMaxY) {
			camY = Graphics.offsetMaxY;
		} else if (camY < Graphics.offsetMinY) {
			camY = Graphics.offsetMinY;
		}
	}

	public void draw() {

		Graphics.setCamera(-camX, -camY);
		drawTiles();
		drawObjects();

	}

	private void drawObjects() {

		objects = sortObjects();

		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw();
		}

	}

	private ArrayList<renderableObj> sortObjects() {
		ArrayList<renderableObj> sorted = new ArrayList<renderableObj>();
		for (int i = 0; i < objects.size(); i++) {
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
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				Tile t = map[i][j];
				t.draw();
			}
		}

	}
	
	public Tile getTile(float x, float y){
		int xi = (int)x / 64;
		int yi = (int)y / 64;
		return map[xi][yi];
	}

}
