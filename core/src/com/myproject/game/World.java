package com.myproject.game;

import java.util.ArrayList;

public class World {
	private static Tank tank;
	private static Stage stage; 
	private static ArrayList<Bullet> bullets;
	
	World(TankGame TankGame) {
		tank = new Tank(425,875,stage);
		stage = new Stage();
		bullets = new ArrayList<Bullet>();
	}
	
    public void clashedWall(int r, int c, int i){
		stage = getStage();
			if(c <= TankGame.HEIGHT/50 && r <= TankGame.WIDTH/50 && c>=0 && r>=0) {
				//clashWall
				if(stage.STAGE[c-1].charAt(r) == 'b'){
					stage.STAGE[c-1].setCharAt(r,'.');
					bullets.remove(i);
				}
				// clashEdge
				if (stage.STAGE[c-1].charAt(r) == '#'){
					bullets.remove(i);
				}
				// clashMetal
				if (stage.STAGE[c-1].charAt(r) == 'S'){
					bullets.remove(i);
				}
				//clashBase
				if (stage.STAGE[c-1].charAt(r) == '$'){

					stage.STAGE[c-1].setCharAt(r,'e');
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
	 
	 
	 Stage getStage(){
		 return stage;
	 }
	 
}
