package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Tank2 {
	
	private Vector2 position;
	private int nextDirection;
	private int currentDirection;
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 5;
	private static final int [][] DIR_OFFSETS = new int [][] {
	        {0,0},
	        {0,-1},
	        {1,0},
	        {0,1},
	        {-1,0}
	    };
	    
	
	
	Tank2(int x, int y, Stage stage){
		position = new Vector2(x,y);
	}
	
	Vector2 getPosition(){
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
	        
//	    if(Gdx.input.isKeyJustPressed(Keys.NUM_0)){
//			shoot();
//	    }
//	    for (Bullet bullet : bullets) {
//	    	bullet.render();
//	    }
	 }
}
