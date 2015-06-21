package inventory;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import data.Helpers.renderableObj;
import entities.Player;

public class Item extends renderableObj{
	
	protected float x, y, z;
	protected Texture tex;
	protected Player player;
	protected Rectangle box, pbox;
	protected Inventory inventory;
	protected boolean taken = false;

}
