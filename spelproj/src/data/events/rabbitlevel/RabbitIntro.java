package data.events.rabbitlevel;

import data.events.Event;
import entities.Rabbit;

public class RabbitIntro extends Event{
	
	Rabbit rabbit;
	float goalX, goalY;

	public RabbitIntro(Rabbit rabbit){
		this.rabbit = rabbit;
		rabbit.setEvent(this);
	}
	
	public void update(){
		
	}
	
	public void check(){
		
	}
	
	public void init(){
		
	}
	
}
