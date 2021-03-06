package data.tiles;

public enum TileType {

	Grass("gras", true, 2,0), Grass_D1("gras_diagonal", true, 2,0),
	Grass_D2("gras_diagonal", true, 2,1), Grass_D3("gras_diagonal", true, 2,2),
	Grass_D4("gras_diagonal", true, 2,3), Dirt("dirt", true, 3,0), 
	Dirt_D1("dirt_grass_ldr",true, 3, 0), Dirt_D2("dirt_grass_ldr", true, 3, 1), 
	Dirt_D3("dirt_grass_ldr", true,3 , 2), Dirt_D4("dirt_grass_ldr", true,3 , 3), 
	Water("vatten", true,1, 0);

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
