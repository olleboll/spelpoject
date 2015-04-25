package gameobjects;

public enum ObjectType {
	
	Tree( "trad", true, 2), Flower( "blomma", true, 1), Rocket( "raket", true, 4);
	
	String textureName;
	boolean buildable;
	float speed;
	
	ObjectType(String texturename, boolean buildable, float speed){
		this.textureName = texturename;
		this.buildable = buildable;
		this.speed = speed;
	}

}
