package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
    public int currentDirection;
    public int nextDirection;
    ArrayList <Bullet> bullets;
    World world;
    private Sound shoot;
    private Sprite tankSprite;
	private Texture tankTexture;
	private SpriteBatch batch;
	private Rectangle tankRect;
    private int Max_Bullet = 10;
    private int countBullet = 0;
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
        tankImg = "myTank.png";
        tankTexture = new Texture(tankImg);
        tankSprite = new Sprite(tankTexture);
        tankSprite.setPosition(position.x - WorldRenderer.BLOCK_SIZE / 2,
        		TankGame.HEIGHT - position.y - WorldRenderer.BLOCK_SIZE / 2);
        
        tankRect = new Rectangle(tankSprite.getX(), tankSprite.getY(), tankSprite.getHeight(), tankSprite.getWidth());
        tankRect.setPosition(tankSprite.getX(), tankSprite.getY());
        shoot = Gdx.audio.newSound(Gdx.files.internal("shoot.mp3"));
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
		if(countBullet >= Max_Bullet)return;
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
	        
	    if(Gdx.input.isKeyJustPressed(Keys.L)){
			shoot();
			countBullet++;
			shoot.play();
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
	
	public Rectangle getRect(){
		return tankRect;
	}
	
	public void render() {
		tankTexture = new Texture(tankImg);
		tankSprite = new Sprite(tankTexture);
        tankSprite.setPosition(position.x - WorldRenderer.BLOCK_SIZE / 2,
        		TankGame.HEIGHT - position.y - WorldRenderer.BLOCK_SIZE / 2);
        tankSprite.draw(batch);
        tankRect.setPosition(tankSprite.getX(), tankSprite.getY());
	}
}