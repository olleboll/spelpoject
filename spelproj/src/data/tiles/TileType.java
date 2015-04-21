package data.tiles;

public enum TileType {

	Grass("grass", true, 4,0), Dirt("dirt", true, 5,0), Dirt_D1("dirt_grass_ldr",true, 5, 0),
	Dirt_D2("dirt_grass_ldr", true, 5, 1), Dirt_D3("dirt_grass_ldr", true,5 , 2),
	Dirt_D4("dirt_grass_ldr", true,5 , 3);

	String textureName;
	boolean buildable;
	float speed;
	int flip;

	TileType(String texturename, boolean buildable, float speed, int flip) {
		this.textureName = texturename;
		this.buildable = buildable;
		this.speed = speed;
		this.flip = flip;
	}

}
