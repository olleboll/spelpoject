package gameobjects;

public enum ObjectType {
	
	Tree( "trad", true), Flower( "blomma", true);
	
	String textureName;
	boolean buildable;
	
	ObjectType(String texturename, boolean buildable){
		this.textureName = texturename;
		this.buildable = buildable;
	}

}
