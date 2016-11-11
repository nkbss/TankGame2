package com.myproject.game;

public class World {
	private Tank tank;
	private Stage stage;
	private Bullet bullet;
	
	World(TankGame TankGame) {
		tank = new Tank(425,875,stage);
		stage = new Stage();
		bullet = new Bullet(tank);
	}
	 
	Tank getTank() {
	       return tank;
	    }
	 
	 Stage getStage(){
		 return stage;
	 }
	 
	 Bullet getBullet(){
		 return bullet;
	 }
	 
	 public void update(float delta) {
		 
	 }
	 
}
