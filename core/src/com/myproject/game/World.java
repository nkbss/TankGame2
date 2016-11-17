package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class World {
	private static Tank tank;
	private static Tank2 tank2;
	private static Stage stage; 
	private static ArrayList<Bullet> bullets;
	private static ArrayList<Bullet2> bullets2;
	private static Bullet bullet;
	public static boolean checkGameOver = false;
	public static boolean checkGameOver2 = false;
	private Sound explore;
	World(TankGame TankGame) {
		tank = new Tank(425,875,stage);
		tank2 = new Tank2(625,75,stage);
		stage = new Stage();
		bullets = new ArrayList<Bullet>();
		bullets2 =new ArrayList<Bullet2>();
		explore = Gdx.audio.newSound(Gdx.files.internal("explore.mp3"));
	}
	
    public void clashedWallBullet1(int r, int c, int i){
		stage = getStage();
			if(c <= TankGame.HEIGHT/50 && r <= TankGame.WIDTH/50 && c>=0 && r>=0) {
				//clashWall
				
				if(stage.STAGE[c-1].charAt(r) == 'b'){
					stage.STAGE[c-1].setCharAt(r,'.');
					bullets.remove(i);
					explore.play();
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
				
				if (stage.STAGE[c-1].charAt(r) == '2'){
					stage.STAGE[c-1].setCharAt(r,'e');
					bullets.remove(i);
					TankGameOver2();
					explore.play();
				}
			
				if (stage.STAGE[c-1].charAt(r) == '1'){
					stage.STAGE[c-1].setCharAt(r,'e');
					bullets.remove(i);
					TankGameOver();
					explore.play();
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
  					explore.play();
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
  				
  				if (stage.STAGE[c-1].charAt(r) == '1'){
  					stage.STAGE[c-1].setCharAt(r,'e');
  					bullets2.remove(i);
  					TankGameOver();
  					explore.play();
  				}
  			
  				if (stage.STAGE[c-1].charAt(r) == '2'){
  					stage.STAGE[c-1].setCharAt(r,'e');
  					bullets2.remove(i);
  					TankGameOver2();
  					explore.play();
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
    	bullets = tank.getBulletList();
    	bullets2 = tank2.getBullet2List();	
    	removeOnCollision();
    }
	 

    public void removeOnCollision(){
    	
    	//bullet vs bullet 
    	for(int i = 0; i < bullets.size() ; i++){
    		Bullet bullet1 = bullets.get(i);
    		for(int j = 0 ; j < bullets2.size() ; j++){
    			Bullet2 bullet2 = bullets2.get(j);
    			
    			if(bullet1.getRect().overlaps(bullet2.getRect())){
    				bullets.remove(i);
    				bullets2.remove(j);
    			}
    		}
    	}
    		for(int i = 0; i < bullets.size() ; i++){
        		Bullet bullet1 = bullets.get(i);
        			 
        			if(bullet1.getRect().overlaps(tank2.getRect())){
         				bullets.remove(i);
         				tank2.setNextImg("explore.png");
         				TankGameOver2();
        			}
    	
        		
        	}
        
    		for(int i = 0; i < bullets2.size() ; i++){
        		Bullet2 bullet2 = bullets2.get(i);
        			 
        			if(bullet2.getRect().overlaps(tank.getRect())){
         				bullets2.remove(i);
         				tank.setNextImg("explore.png");
         				TankGameOver();
        			}
        	}
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
	 
	 public ArrayList<Bullet> getBulletAfterCollision() {
			return bullets;
		}
	 
	 public ArrayList<Bullet2> getBullet2AfterCollision() {
			return bullets2;
		}

	 public void TankGameOver(){
		 checkGameOver = true;
	 }

	 public void TankGameOver2(){
		 checkGameOver2 = true;
	 }
}
