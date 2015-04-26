package data.level;

import gameobjects.GameObject;
import gameobjects.ObjectType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.Main;
import data.Helpers.Graphics;
import data.Helpers.RenderHelper;
import data.Helpers.renderableObj;
import data.tiles.Tile;
import data.tiles.TileType;
import entities.Entity;
import entities.Player;
import entities.Rabbit;

public class RabbitLevel extends Level{
	private Player player;
	private Rabbit rabbit;

	public RabbitLevel(String path, int sizex, int sizey) {
		super(path, sizex, sizey);
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
			setSize(w,h);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! could not load level file.");
		}
	}

	protected void setUpLevel() {
		map = new Tile[SIZEX][SIZEY];
		objects = new ArrayList<renderableObj>();
		entities = new ArrayList<Entity>();
		
		for (int y = 0; y < SIZEY; y++) {
			for (int x = 0; x < SIZEX; x++) {
				// System.out.println(levelpixels[x + y * SIZE]);
				setUpTiles(x, y);
			}
		}
		for (int y = 0; y < SIZEY; y++) {
			for (int x = 0; x < SIZEX; x++) {
				// System.out.println(levelpixels[x + y * SIZE]);
				setUpObjects(x, y);
			}
		}
		setUpEntities();
	}


	public void update() {
		
		for(int i = 0 ; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.update();
		}
		updateCamera();
	}

	protected void updateCamera() {
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
		Graphics.setCamera(-camX, -camY);
	}

	

	

	public void draw() {
		drawTiles();
		drawObjects();

	}

	protected void drawObjects() {
		
		ArrayList<renderableObj> obj = new ArrayList<renderableObj>();
		obj = getVisibleObj();
		obj = getEntites(obj);
		obj = sortObjects(obj);
		for (int i = 0; i < obj.size(); i++) {
			obj.get(i).draw();
		}

	}

	
	protected void setUpEntities() {
		player = new Player(7 * SIZE,998*SIZE, 998*SIZE, 64, 64, null, this);
		objects.add(player);
		entities.add(player);
		rabbit = new Rabbit(9 * SIZE,998*SIZE, 998*SIZE, 56, 75, null, this);
		objects.add(rabbit);
		entities.add(rabbit);
		rabbit.setPlayer(player);
		player.setTopSpeed(10);
		rabbit.setTopSpeed(10);
		player.setSpeed(10);
		rabbit.setSpeed(10);
		camX = player.getX() - Main.WIDTH / 2;
		camY = player.getY() - Main.HEIGHT / 2;	
	}
	
	
	// FÄRGER och TILES

		// rosa = -20791 -> flower
		// svart = -16777216 -> rock
		// brun = -4621737 -> tree

		private int zoff = 0;
		private int zoffH = 1;
		protected void setUpObjects(int x, int y) {

			if (zoffH == 1) {
				zoffH = -1;
			} else {
				zoffH = 1;
			}
			zoff = RenderHelper.renderz() * 2;
			if (levelpixels[x + y * SIZEX] == -20791) {
				zoff = RenderHelper.renderz()*zoffH;
				GameObject flower = new GameObject(x * SIZE - 16, y * SIZE  -32, zoff, 54, 64,ObjectType.Flower);
				objects.add(flower);
				map[x][y].setObj(flower);			
				
				zoff = RenderHelper.renderz()* zoffH;
				GameObject flower2 = new GameObject(x * SIZE + 16 , y * SIZE - 32, zoff, 54, 64,
						ObjectType.Flower);
				objects.add(flower2);
				map[x][y].setObj(flower2);
				
				zoff = RenderHelper.renderz()* zoffH;
				GameObject flower3 = new GameObject(x * SIZE - 16, y * SIZE , zoff, 54, 64,
						ObjectType.Flower);
				objects.add(flower3);
				map[x][y].setObj(flower3);

				zoff = RenderHelper.renderz()* zoffH;
				GameObject flower4 = new GameObject(x * SIZE + 16, y * SIZE , zoff, 54, 64,
						ObjectType.Flower);
				objects.add(flower4);
				map[x][y].setObj(flower4);

			} else if (levelpixels[x + y * SIZEX] == -1621737) {
				// tree = new GameObject(64, 384, 128, 128, ObjectType.Tree);
				// objects.add(tree);
			} else if (levelpixels[x + y * SIZEX] == -4621737) {
				// TRÄD
				GameObject tree = new GameObject(x * SIZE - 16, y * SIZE - 64, zoff, 88, 128,
						ObjectType.Tree);
				objects.add(tree);
				map[x][y].setSolid(true);
				map[x][y].setObj(tree);
				// map[x + 1][y + 2].setSolid(true);
			}else if (levelpixels[x + y * SIZEX] == -16777216) {
				// 64
				GameObject rocket = new GameObject(x * SIZE - 16, y * SIZE - 128, zoff, 134, 256,
						ObjectType.Rocket);
				map[x][y].setObj(rocket);
				map[x][y].setSolid(true);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			}

		}

		// här kan du läsa in på samma sätt som förut tror jag. Kolla din array med
		// färger och
		// lägg ut de tiles som motsvarar, busenkelt?

		// FÄRGER och TILES
		// ljusgrön = -4856291 -> grass
		// rosa = -20791 -> flower
		// svart = -16777216 -> rock
		// brun = -4621737 -> tree
		// mörkbrun = -7864299 -> dirt
		// spygul = -14066 -> dirtD0
		// ljusgrå = -3947581 -> dirtD1
		// mörkgrå = --8421505 -> dirtD2
		// grädvit = -1055568 -> dirtD3 


		protected void setUpTiles(int x, int y) {

			/*
			 * for (int i = 0; i < map.length; i++) { for (int j = 0; j <
			 * map.length; j++) { map[i][j] = new Tile(i * 64, j * 64, 64, 64,
			 * TileType.Grass); //map[x][y] = levelpixels[x + y * SIZE]; } }
			 */
			System.out.println(levelpixels[x + y * SIZEX]);
			if (levelpixels[x + y * SIZEX] == -20791) {
				// Tiles.add(x + y * width, Tile.flower);
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);

			} else if (levelpixels[x + y * SIZEX] == -4856291) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);

			} else if (levelpixels[x + y * SIZEX] == -7864299) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt);

			} else if (levelpixels[x + y * SIZEX] == -4621737) {
				// TRÄD
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);
			} else if (levelpixels[x + y * SIZEX] == -14066) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D1);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			}else if (levelpixels[x + y * SIZEX] == -3947581) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D2);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			}else if (levelpixels[x + y * SIZEX] == -8421505) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D3);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			}else if (levelpixels[x + y * SIZEX] == -1055568) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt_D4);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			}else if (levelpixels[x + y * SIZEX] == -16777216) {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Dirt_ldr);
			} else {
				// 64
				map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
				// 32
				//map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass);
			}
		}

		



}
