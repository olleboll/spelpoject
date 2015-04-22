package text;

import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import data.Main;
import data.Helpers.Graphics;


public class Text {
	
	private TrueTypeFont font;
	private boolean antiAlias = false;
	
	public Text(){
		init();
	}
	
	public void init() {
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, antiAlias);
         
        // load font from file
        /*
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("myfont.ttf");
             
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font2 = new TrueTypeFont(awtFont2, antiAlias);
             
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
	
	public void draw(){
		Graphics.drawText(font);
	}

}
