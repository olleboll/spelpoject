package data.Helpers;

import java.util.Random;

public class RenderHelper {
	
	private static Random rand = new Random();
	private static int last = 0;
	
	public static int renderz(){
		int f = rand.nextInt(5);
		while(last == f || f == 0){
			f = rand.nextInt(5);
		}
		last = f;
		return last;
	}

}
