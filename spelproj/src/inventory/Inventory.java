package inventory;

import java.util.ArrayList;

public class Inventory {
	
	private ArrayList<Item> items;
	
	public Inventory(){
		this.items = new ArrayList<Item>();
	}
	
	public void addItem(Item item){
		items.add(item);
	}

}
