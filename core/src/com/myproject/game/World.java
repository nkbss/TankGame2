package com.myproject.game;

import java.util.ArrayList;

public class World {
	private static Tank tank;
	private static Stage stage;
	private Bullet bullet;
	private static Boolean clash;
	private static ArrayList<Bullet> bullets;
	
	World(TankGame TankGame) {
		tank = new Tank(425,875,stage);
		stage = new Stage();
		//bullet = new Bullet(tank);
		bullets = new ArrayList<Bullet>();
	}
	
    public void clashedWall(int r, int c, int i){
		clash = false;
		stage = getStage();
			if(c < Stage.STAGE.length && r < stage.STAGE.length && c>0 && r>0) {
				if(stage.STAGE[c-1].charAt(r) == 'b'){
					clash = true;
				}
				if(clash){
					stage.STAGE[c-1].setCharAt(r, 'e');
					stage.STAGE[c-1].setCharAt(r,'.');
					bullets.remove(i);
				}
			}
	}
    
    public void update() {
    	bullets = tank.getBulletList();
    	for (int i = 0; i < bullets.size(); i++) {
    		Bullet b = bullets.get(i);
    		clashedWall((int)b.getX()/WorldRenderer.BLOCK_SIZE,
    				
    				TankGame.HEIGHT/WorldRenderer.BLOCK_SIZE
    				-(int)b.getY()/WorldRenderer.BLOCK_SIZE,
    				
    				i);
    	}
    }
	 
	Tank getTank() {
	       return tank;
	    }
	 
	 static Stage getStage(){
		 return stage;
	 }
	 
//	 Bullet getBullet(){
//		 return bullet;
//	 }
	 
	 
}
