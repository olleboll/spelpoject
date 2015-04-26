package gameobjects;

public enum ObjectType {

	Tree("trad", true, true, 2), Flower("blomma", true, false, 1), Rocket(
			"raket", true, true, 2);

	String textureName;
	boolean buildable;
	boolean solid;
	float speed;

	ObjectType(String texturename, boolean buildable, boolean solid, float speed) {
		this.textureName = texturename;
		this.buildable = buildable;
		this.solid = solid;
		this.speed = speed;
	}

}
