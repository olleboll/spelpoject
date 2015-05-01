package text;

import org.newdawn.slick.opengl.Texture;

import entities.Player;
import static data.Helpers.Graphics.*;


public class Text {
	
	private float x,y,z,width,height;
	private Texture tex;
	private String path;
	
	
	public Text(String path){
		this.path = path;
		init();
		
	}
	
	public void init() {
		
		tex = QuickLoad(path);
		
		
    }
	
	public void draw(Player player){
		x = player.getX() - tex.getImageWidth() / 2;
		y = player.getY() + tex.getTextureHeight() * 2;
		z = WorldSizeY;
		width = tex.getImageWidth();
		height = tex.getImageHeight();
		
		drawQuadEntityTex(tex, x, y, z, width, height);
	}

}
