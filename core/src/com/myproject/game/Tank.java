package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Tank {
	private Vector2 position;
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 5;
    public Stage stage;
    private String tankImg;
    private String nextImg;
    private Bullet bullet;
    public int currentDirection;
    public int nextDirection;
    ArrayList <Bullet> bullets;
    private int MAX_BULLETS;
    World world;
	//private Sprite tankSprite;
    private static final int [][] DIR_OFFSETS = new int [][] {
        {0,0},
        {0,-1},
        {1,0},
        {0,1},
        {-1,0}
    };
    
    public Tank(int x, int y, Stage stage) {
    	bullets = new ArrayList <Bullet>();
        position = new Vector2(x,y);
        MAX_BULLETS = 6;
        currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        this.stage = stage;
        tankImg = "myTank.png";
      //  tankSprite = new Sprite(tankImg);
    }    
 
    public Vector2 getPosition() {
        return position;    
    }
    
    public void setNextDirection(int dir) {
        nextDirection = dir;
    }
   
    public boolean isAtCenter() {
        int blockSize = WorldRenderer.BLOCK_SIZE;
        return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
                ((((int)position.y - blockSize/2) % blockSize) == 0);
    }
    
	private void shoot(){
//	if(bullets.size() == MAX_BULLETS){
//			return;
//		}
		bullets.add(new Bullet(this));
	}
     
	 public void update() {
		 if(isAtCenter()) {
			 if(canMoveInDirection(nextDirection)) {
				 currentDirection = nextDirection;    
			 } else {
				 currentDirection = DIRECTION_STILL;    
	            }
	        }
	        position.x += SPEED * DIR_OFFSETS[currentDirection][0];
	        position.y += SPEED * DIR_OFFSETS[currentDirection][1];
	        
	    if(Gdx.input.isKeyJustPressed(Keys.Z)){
			shoot();
	    }
	    for (Bullet bullet : bullets) {
	    	bullet.render();
	    }
	 }
	 
	 private int getRow() {
	        return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
	    }
	 
	 private int getColumn() {
	        return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
	    }
	 
	 private boolean canMoveInDirection(int dir) {
	        int newRow = this.getRow() + DIR_OFFSETS[dir][1]; 
	        int newCol = this.getColumn() + DIR_OFFSETS[dir][0]; 
	        if(Stage.STAGE[newRow].charAt(newCol) != '.'){
	        	return false;
	        }
	        return true;
	    }

	public void setNextImg(String nextImg) {
			this.tankImg = nextImg; 
		}
		
	public String getNextImg(){
			return tankImg;
		}
	
	public ArrayList<Bullet> getBulletList() {
		return bullets;
	}
}