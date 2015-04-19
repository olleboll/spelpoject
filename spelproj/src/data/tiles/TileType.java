package data.tiles;

public enum TileType {
	
	Grass( "grass", true);
	
	String textureName;
	boolean buildable;
	
	TileType(String texturename, boolean buildable){
		this.textureName = texturename;
		this.buildable = buildable;
	}
	

}
