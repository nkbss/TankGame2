package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Stage {
	/*public static String[] STAGE = new String[]{
		"#####################",
		"#...................#",
		"#......bbbbbbb......#",
		"#...bb.........bb...#",
		"#...bb.........bb...#",
		"#...bb..b.b.b..bb...#",
		"#...bb..bSSSb..bb...#",
		"#...bb..bSSSb..bb...#",
		"#....bb...b...bb....#",
		"#.........b.........#",
		"#SS....bb...bb....SS#",
		"#.....bbbbbbbbb.....#",
		"#...................#",
		"#..bbb..bbbbb..bbb..#",
		"#........bbb........#",
		"#.....bb.....bb.....#",
		"#........bbb........#",
		"#........b$b........#",
		"#####################",
	};*/
	
	public static StringBuilder STAGE[];
	
	private int height;
	private int width;
	private static ArrayList<Bullet> bullets;
	private static Tank tank;
	public static boolean clash ;
	public Stage(){
        STAGE = new StringBuilder[19];
        STAGE[0] = new StringBuilder("#####################");
        STAGE[1] = new StringBuilder("#...................#");
        STAGE[2] = new StringBuilder("#......bbbbbbb......#");
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
        STAGE[15] = new StringBuilder("#.....bb.....bb.....#");
        STAGE[16] = new StringBuilder("#........bbb........#");
        STAGE[17] = new StringBuilder("#........b$b........#");
        STAGE[18] = new StringBuilder("#####################");
        height = STAGE.length;
        width = STAGE[0].length();	
        clash = false;
        
        bullets = new ArrayList<Bullet>();
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
   
    public boolean hasBaseAt(int r, int c) {
        return STAGE[r].charAt(c) == '$';
    }

    public boolean hasExploreAt(int r, int c) {
        return STAGE[r].charAt(c) == 'e';
    }
    
//    public static void clashedWall(int r, int c){
//    		clash = false;
//    	if(c<STAGE.length && r<STAGE[0].length() && c>0 && r>0){
//    		if(STAGE[c-1].charAt(r) == 'b'){
//    			clash = true;
//    		}
//    		if(clash){
//    			bullets = tank.getBulletList();
//    			STAGE[c-1].setCharAt(r, 'e');
//    			STAGE[c-1].setCharAt(r,'.');
//    		}
//    	}
//    }
}
