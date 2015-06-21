package data.objectives.rabbitlevel;

import level.Level;
import objectives.Objective;
import objectives.ObjectiveHandler;

import org.lwjgl.util.Rectangle;

import text.Text;
import entities.Player;

public class RocketFound extends Objective {
	
	private float rocketX, rocketY, rocketW, rocketH;
	private Text objtext;
	private Player player;
	private ObjectiveHandler missions;
	private String textpath = "textures/text/testtext2";
	
	public RocketFound(Player player){
		this.player = player;
	}
	
	protected void init(ObjectiveHandler missions){
		objtext = new Text(textpath);
		this.missions = missions;
		
	}
	
	protected void check(){
		
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
