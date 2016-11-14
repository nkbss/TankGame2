package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private String tankImgString;
    private Texture tankImg;
    public int currentDirection;
    public int nextDirection;
    private Sprite tankSprite;
    ArrayList <Bullet> bullets;
    ArrayList <Tank> tanks;
    World world;
	private Vector2 posSpriteTank;
	private SpriteBatch batch;
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
        currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        this.stage = stage;
        batch = TankGame.batch;
        tankImgString = "myTank.png";
        tanks = new ArrayList<Tank>();
        tankImg = new Texture(this.getNextImg());
        tankSprite = new Sprite(tankImg);
        tankSprite.setPosition(position.x-WorldRenderer.BLOCK_SIZE/2,
        		TankGame.HEIGHT-position.y-WorldRenderer.BLOCK_SIZE/2);
        posSpriteTank = new Vector2();
        posSpriteTank.x = tankSprite.getX();
        posSpriteTank.y = tankSprite.getY();
    }    
 
    public Vector2 getPosition() {
        return position;    
    }
    
    public void setNextDirection(int dir) {
        nextDirection = dir;
    }
   
    public boolean isAtCenter() {
        int blockSize = WorldRenderer.BLOCK_SIZE;
        return ((((int)posSpriteTank.x - blockSize/2) % blockSize) == 0) &&
                ((((int)posSpriteTank.y - blockSize/2) % blockSize) == 0);
        
    }
    
	private void shoot(){
		bullets.add(new Bullet(this));
	}
     
	 public void update() {
		 if(isAtCenter()) {
			// System.out.println("Hello");
			 if(canMoveInDirection(nextDirection)) {
				 currentDirection = nextDirection;    
			 } else {
				 currentDirection = DIRECTION_STILL;    
	            }
	        }
	        
		 	posSpriteTank.x += SPEED * DIR_OFFSETS[currentDirection][0];
	        posSpriteTank.y += SPEED * DIR_OFFSETS[currentDirection][1];
			System.out.println(posSpriteTank.x);
			System.out.println(posSpriteTank.y);
	    if(Gdx.input.isKeyJustPressed(Keys.L)){
			shoot();
	    }
	    for (Bullet bullet : bullets) {
	    	bullet.render();
	    }
	 }
	 
	 private int getRow() {
	        return ((int)posSpriteTank.y) / WorldRenderer.BLOCK_SIZE; 
	    }
	 
	 private int getColumn() {
	        return ((int)posSpriteTank.x) / WorldRenderer.BLOCK_SIZE; 
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
			this.tankImgString = nextImg; 
		}
		
	public String getNextImg(){
			return tankImgString;
		}
	
	public ArrayList<Bullet> getBulletList() {
		return bullets;
	}
	
	public ArrayList<Tank> getTankList(){
		return tanks;
	}

	public void render(){
//		update();
		tankSprite.setPosition(posSpriteTank.x,posSpriteTank.y);
		tankImg = new Texture(getNextImg());
		batch = TankGame.batch;
		tankSprite.draw(batch);
//		System.out.println(posSpriteTank.x);
//		System.out.println(posSpriteTank.y);
	}
}