package events;

public class EventsHandler {
	
	private Event current;
	
	
	public EventsHandler(){
		
	}
	
	public void setCurrent(Event e){
		this.current = e;
	}
	
	public void update(){
		current.update();
	}
	
	public void draw(){}

}
