package data.objectives.rabbitlevel;

import org.lwjgl.util.Rectangle;

import text.Text;
import data.objectives.Objective;
import data.objectives.ObjectiveHandler;
import entities.Player;

public class FindRocket extends Objective{
	
	private float rocketX, rocketY, rocketW, rocketH;
	private Text objtext;
	private Player player;
	private ObjectiveHandler missions;
	private String textpath = "textures/text/testtext";
	
	public FindRocket(Player player, float x, float y, float width, float height){
		this.rocketX = x;
		this.rocketY = y;
		this.rocketW = width;
		this.rocketH = height;
		this.player = player;
	}
	
	protected void init(ObjectiveHandler missions){
		objtext = new Text(textpath);
		this.missions = missions;
		
	}
	
	protected void check(){
		Rectangle r = new Rectangle((int)rocketX - 40, (int)rocketY - 40, (int)rocketW, (int)rocketH);
		Rectangle p = new Rectangle((int)player.getX() - 25, (int)player.getY() - 25, (int)player.getWidth() + 50, (int)player.getHeight() + 50);
		if(r.intersects(p) || p.intersects(r) || p.contains(r)){
			finished = true;
			missions.setNewCurrent(new RocketFound(player));
		}
	}
	
	public void update(){
		check();
	}
	
	public void draw(){
		if(!finished){

			objtext.draw(player);
		}
	}
	
	

}
