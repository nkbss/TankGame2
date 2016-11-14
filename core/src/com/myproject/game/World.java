package com.myproject.game;

import java.util.ArrayList;

public class World {
	private static Tank tank;
	private static Tank2 tank2;
	private static Stage stage; 
	private static ArrayList<Bullet> bullets;
	private static ArrayList<Bullet2> bullets2;
	private static Bullet bullet;
	
	World(TankGame TankGame) {
		tank = new Tank(425,875,stage);
		tank2 = new Tank2(75,225,stage);
		stage = new Stage();
		bullets = new ArrayList<Bullet>();
		bullets2 =new ArrayList<Bullet2>();
	}
	
    public void clashedWallBullet1(int r, int c, int i){
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
    
    public void clashedWallBullet2(int r, int c, int i){
  		stage = getStage();
  			if(c <= TankGame.HEIGHT/50 && r <= TankGame.WIDTH/50 && c>=0 && r>=0) {
  				//clashWall
  				if(stage.STAGE[c-1].charAt(r) == 'b'){
  					stage.STAGE[c-1].setCharAt(r,'.');
  					bullets2.remove(i);
  					System.out.println("clash Wall");
  				}
  				// clashEdge
  				if (stage.STAGE[c-1].charAt(r) == '#'){
  					bullets2.remove(i);
  				}
  				// clashMetal
  				if (stage.STAGE[c-1].charAt(r) == 'S'){
  					bullets2.remove(i);
  				}
  				//clashBase
  				if (stage.STAGE[c-1].charAt(r) == '$'){

  					stage.STAGE[c-1].setCharAt(r,'e');
  					bullets2.remove(i);
  				}
  	
  				
  			}
  	}
   
    public void checkClashBullet1(ArrayList<Bullet> bullets){
    	for (int i = 0; i < bullets.size(); i++) {
    		Bullet b = bullets.get(i);
    		clashedWallBullet1((int)b.getX()/WorldRenderer.BLOCK_SIZE,
   				
   				TankGame.HEIGHT/WorldRenderer.BLOCK_SIZE
   				-(int)b.getY()/WorldRenderer.BLOCK_SIZE,
   				
    				i);
    		}
    }
    
    public void checkClashBullet2(ArrayList<Bullet2> bullets2){
    	for (int i = 0; i < bullets2.size(); i++) {
    		Bullet2 b = bullets2.get(i);
    		clashedWallBullet2((int)b.getX()/WorldRenderer.BLOCK_SIZE,
   				
   				TankGame.HEIGHT/WorldRenderer.BLOCK_SIZE
   				-(int)b.getY()/WorldRenderer.BLOCK_SIZE,
   				
    				i);
    		}
    }
    
    public void update() {
    	bullets = tank.getBulletList();
    	bullets2 = tank2.getBullet2List();
    	checkClashBullet1(bullets);
    	checkClashBullet2(bullets2);
    	tank.update();
    }
	 
	Tank getTank() {
	       return tank;
	    }
	 
	 
	 Stage getStage(){
		 return stage;
	 }
	 
	 Tank2 getTank2(){
		 return tank2;
	 }
	 
	 
}
