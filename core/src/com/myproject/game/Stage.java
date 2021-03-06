package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Stage {

	public static StringBuilder STAGE[];
	private int height;
	private int width;
	public Stage(){
        STAGE = new StringBuilder[19];
        STAGE[0] = new StringBuilder("#####################");
        STAGE[1] = new StringBuilder("#........b2b........#");
        STAGE[2] = new StringBuilder("#........bbb........#");
        STAGE[3] = new StringBuilder("#...bb.........bb...#");
        STAGE[4] = new StringBuilder("#...bb.........bb...#");
        STAGE[5] = new StringBuilder("#...bb..b.b.b..bb...#");
        STAGE[6] = new StringBuilder("#...bb..bSSSb..bb...#");
        STAGE[7] = new StringBuilder("#...bb..bSSSb..bb...#");
        STAGE[8] = new StringBuilder("#....bb...b...bb....#");
        STAGE[9] = new StringBuilder("#.........b.........#");
        STAGE[10] = new StringBuilder("#SS....bb...bb....SS#");
        STAGE[11] = new StringBuilder("#.....bbbbbbbbb.....#");
        STAGE[12] = new StringBuilder("#...................#");
        STAGE[13] = new StringBuilder("#..bbb..bbbbb..bbb..#");
        STAGE[14] = new StringBuilder("#........bbb........#");
        STAGE[15] = new StringBuilder("#.....bb..S..bb.....#");
        STAGE[16] = new StringBuilder("#........bbb........#");
        STAGE[17] = new StringBuilder("#........b1b........#");
        STAGE[18] = new StringBuilder("#####################");
        height = STAGE.length;
        width = STAGE[0].length();	
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
   
	public boolean hasWallAt(int r, int c) {
        return STAGE[r].charAt(c) == '#';
    }
 
    public boolean hasBrickAt(int r, int c) {
        return STAGE[r].charAt(c) == 'b';
    }
    
    public boolean hasMetalAt(int r, int c) {
        return STAGE[r].charAt(c) == 'S';
    }
   
    public boolean hasBaseAt1(int r, int c) {
        return STAGE[r].charAt(c) == '1';
    }

    public boolean hasBaseAt2(int r, int c) {
        return STAGE[r].charAt(c) == '2';
    }
    
    public boolean hasExploreAt(int r, int c) {
        return STAGE[r].charAt(c) == 'e';
    }
    
}
