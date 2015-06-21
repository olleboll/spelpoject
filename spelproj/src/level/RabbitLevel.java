package level;

import gameobjects.GameObject;
import gameobjects.ObjectType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import commands.Command;

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
		tiles = new ArrayList<Tile>();
		setUpEntities();
		setUpEnvironment();
		
		
	}


	private void setUpEnvironment() {
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
		
	}

	public void update() {
		
		for(int i = 0 ; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.update();
		}
		for(int i = 0; i < tiles.size(); i++){
			Tile t = tiles.get(i);
			t.update();
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
		player = new Player(7 * SIZE,508*SIZE, 998*SIZE, 64, 64, null, this);
		objects.add(player);
		entities.add(player);
		rabbit = new Rabbit(9 * SIZE,508*SIZE, 998*SIZE, 56, 75, null, this);
		objects.add(rabbit);
		entities.add(rabbit);
		rabbit.setPlayer(player);
		player.setAnimal(rabbit);
		rabbit.setCommand(Command.Ride);
		player.setTopSpeed(10);
		rabbit.setTopSpeed(10);
		player.setSpeed(10);
		rabbit.setSpeed(10);
		camX = player.getX() - Main.WIDTH / 2;
		camY = player.getY() - Main.HEIGHT / 2;	
	}
	
	private int zoff = 0;
	private int zoffH = 1;

	protected void setUpObjects(int x, int y) {

		if (zoffH == 1) {
			zoffH = -1;
		} else {
			zoffH = 1;
		}
		zoff = RenderHelper.renderz();
		if (levelpixels[x + y * SIZEX] == -20791) {
			// BLOMMOR
			zoff = RenderHelper.renderz() * zoffH;
			GameObject flower = new GameObject(x * SIZE - 16, y * SIZE - 32,
					zoff, 54, 64, ObjectType.Flower);
			objects.add(flower);
			map[x][y].setObj(flower);

			zoff = RenderHelper.renderz() * zoffH;
			GameObject flower2 = new GameObject(x * SIZE + 16, y * SIZE - 32,
					zoff, 54, 64, ObjectType.Flower);
			objects.add(flower2);
			map[x][y].setObj(flower2);

			zoff = RenderHelper.renderz() * zoffH;
			GameObject flower3 = new GameObject(x * SIZE - 16, y * SIZE, zoff,
					54, 64, ObjectType.Flower);
			objects.add(flower3);
			map[x][y].setObj(flower3);

			zoff = RenderHelper.renderz() * zoffH;
			GameObject flower4 = new GameObject(x * SIZE + 16, y * SIZE, zoff,
					54, 64, ObjectType.Flower);
			objects.add(flower4);
			map[x][y].setObj(flower4);
		} else if (levelpixels[x + y * SIZEX] == -4621737) {
			// TRÄD
			GameObject tree = new GameObject(x * SIZE - 16, y * SIZE - 84,
					zoff, 88, 128, ObjectType.Tree);
			objects.add(tree);
			map[x][y].setSolid(true);
			map[x][y].setObj(tree);
		} else if (levelpixels[x + y * SIZEX] == -8421505) {
			// TRÄD
			GameObject tree = new GameObject(x * SIZE - 32, y * SIZE - 84,
					zoff, 88, 128, ObjectType.Tree);
			objects.add(tree);
			map[x][y].setObj(tree);
		}

	}

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
	// blå = -12629812 -> Water

	protected void setUpTiles(int x, int y) {

		//System.out.println(levelpixels[x + y * SIZEX]);
		if (levelpixels[x + y * SIZEX] == -20791) {
			// BLOMMOR/GRÄS
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);

		} else if (levelpixels[x + y * SIZEX] == -4856291) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);

		} else if (levelpixels[x + y * SIZEX] == -7864299) {
			// 64

			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt);


		} else if (levelpixels[x + y * SIZEX] == -4621737) {
			// TRÄD
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);

		}else if (levelpixels[x + y * SIZEX] == -16777216) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Dirt);

		} else if (levelpixels[x + y * SIZEX] == -12629812) {
			// 64
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Water);
			tiles.add(map[x][y]);
			map[x][y].setSolid(true);
			map[x][y].setJumpAbleP(true);

		} else {
			// 64
			System.out.println(levelpixels[x + y * SIZEX]);
			map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.Grass);
		}
	}

	private void generateDirt(int x, int y) {

		// mörkbrun = -7864299 -> dirt
		// spygul = -14066 -> dirtD1
		// ljusgrå = -3947581 -> dirtD2
		// mörkgrå = -8421505 -> dirtD3
		// grädvit = -1055568 -> dirtD4
		if((x <= 0 || y <= 0) || (x >= SIZEX || y >= SIZEY)){
			return;
		}
		if (map[x - 1][y].getType() == TileType.Dirt
				&& map[x + 1][y].getType() != TileType.Dirt) {
			if (map[x][y - 1].getType() == TileType.Dirt
					&& map[x][y + 1].getType() != TileType.Dirt ) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D4);
				map[x][y].addTexture(TileType.Grass_D4);
			} else if (map[x][y + 1].getType() == TileType.Dirt
					&& map[x - 1][y].getType() != TileType.Grass_D1
					&& map[x][y - 1].getType() != TileType.Dirt
					&& map[x][y - 1].getType() != TileType.Grass_D1) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D1);
				map[x][y].addTexture(TileType.Grass_D1);
			}
		} else if (map[x - 1][y].getType() != TileType.Dirt
				&& map[x + 1][y].getType() == TileType.Dirt) {
			if (map[x][y - 1].getType() != TileType.Dirt
					&& map[x - 1][y].getType() != TileType.Dirt
					&& map[x][y + 1].getType() == TileType.Dirt
					&& map[x - 1][y].getType() != TileType.Grass_D2
					&& map[x - 1][y].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() != TileType.Grass_D2) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D2);
				map[x][y].addTexture(TileType.Grass_D2);
			} else if (map[x][y + 1].getType() != TileType.Dirt
					&& map[x - 1][y].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() == TileType.Dirt) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D3);
				map[x][y].addTexture(TileType.Grass_D3);
			}
		}
	}
	
	private void generateWater(int x, int y) {
		if((x <= 0 || y <= 0) || (x >= SIZEX || y >= SIZEY)){
			return;
		}
		
		if (map[x - 1][y].getType() == TileType.Water
				&& map[x + 1][y].getType() != TileType.Water) {
			if (map[x][y - 1].getType() == TileType.Water
					&& map[x][y + 1].getType() != TileType.Water ) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D4);
				map[x][y].addTexture(TileType.Grass_D4);
			} else if (map[x][y + 1].getType() == TileType.Water
					&& map[x - 1][y].getType() != TileType.Grass_D1
					&& map[x][y - 1].getType() != TileType.Water
					&& map[x][y - 1].getType() != TileType.Grass_D1) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D1);
				map[x][y].addTexture(TileType.Grass_D1);
			}
		} else if (map[x - 1][y].getType() != TileType.Water
				&& map[x + 1][y].getType() == TileType.Water) {
			if (map[x][y - 1].getType() != TileType.Water
					&& map[x - 1][y].getType() != TileType.Water
					&& map[x][y + 1].getType() == TileType.Water
					&& map[x - 1][y].getType() != TileType.Grass_D2
					&& map[x - 1][y].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() != TileType.Grass_D2) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D2);
				map[x][y].addTexture(TileType.Grass_D2);
			} else if (map[x][y + 1].getType() != TileType.Water
					&& map[x - 1][y].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() != TileType.Grass_D3
					&& map[x][y - 1].getType() == TileType.Water) {
				//map[x][y] = new Tile(x * 64, y * 64, 64+1, 64, TileType.Grass_D3);
				map[x][y].addTexture(TileType.Grass_D3);
			}
		}
		
	}

}
