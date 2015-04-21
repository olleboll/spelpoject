package data.Helpers;

import java.util.Random;

public class RenderHelper {
	
	private static Random rand = new Random();
	private static float last = 0;
	
	public static float renderz(){
		float f = rand.nextFloat();
		while(last == f * 10){
			f = rand.nextFloat();
		}
		last = f * 10;
		return last;
	}

}
