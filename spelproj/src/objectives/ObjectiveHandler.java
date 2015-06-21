package objectives;

public class ObjectiveHandler {
	
	private Objective current;
	
	public ObjectiveHandler(Objective start){
		this.current = start;
		startCurrent();
	}
	
	public void setNewCurrent(Objective o){
		current = o;
		current.init(this);
	}
	
	
	public void startCurrent(){
		current.init(this);
	}
	
	public void update(){
		current.update();
	}
	
	public void draw(){
		current.draw();
	}

}
