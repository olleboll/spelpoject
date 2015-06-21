package inventory.items;

import static data.Helpers.Graphics.*;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import entities.Player;
import inventory.Inventory;
import inventory.Item;

public class Helmet extends Item{
	
		int sizex, sizey;

		public Helmet(float x, float y, Player player){
			this.x = x ;
			this.y = y;
			
			this.tex = loadTex();
			this.sizex = tex.getImageWidth();
			this.sizey = tex.getImageHeight();
			this.z = -WorldSizeY + y + sizey;
			this.player = player;
			this.inventory = player.getInventory();
			this.box = new Rectangle((int)x,(int)y,sizex, sizey);
			this.pbox = new Rectangle((int)player.getX(),(int)player.getY(),(int)player.getWidth(), (int)player.getHeight());
		}
		
		private Texture loadTex(){
			
			return QuickLoadEntityTex("kanin/kanin_fram");
			
		}
		
		public void update(){
			pbox = new Rectangle((int)player.getX(),(int)player.getY(),(int)player.getWidth(), (int)player.getHeight());
			if(pbox.intersects(box)){
				inventory.addItem(this);
				this.taken = true;
			}
		}
		
		public void draw(){
			if(!taken){
				drawQuadObjectTex(tex, x, y, z, sizex, sizey);
			}
		}
}
