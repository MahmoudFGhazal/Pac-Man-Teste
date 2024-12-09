package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import Main.Game;
import World.*;


public class Entity {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double spd;
	private BufferedImage sprite;
	
	public int lastDir, nextDir;
	
	public static Random rand = new Random();;

	public int depth;

	protected List<Node> path;
	
	public static BufferedImage spriteponto = Game.spritesheet.getSprite(64, 0, 16, 16);
	public static BufferedImage spriteEnemy1 = Game.spritesheet.getSprite(80, 0, 16, 16);
	public static BufferedImage spriteEnemy2 = Game.spritesheet.getSprite(96, 0, 16, 16);
	
	public Entity(double x,double y,int width,int height,int depth,double spd, BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spd = spd;
		this.sprite = sprite;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(),null);
		//g.setColor(Color.red);
		//g.fillRect(this.getX(),this.getY(), width, height);
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		@Override
		public int compare(Entity n0,Entity n1) {
			if(n1.depth < n0.depth)
				return +1;
			if(n1.depth > n0.depth)
				return -1;
			return 0;
		}
		
	};
	
	public double calculateDistance(int x1,int y1,int x2,int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public void followPath(List<Node> path) {
		if(path != null && path.size() > 0) {
			Vector2i target = path.get(path.size() - 1).tile;
			lastDir = path.get(path.size() - 1).Dir;
			if(path.size() - 2 >= 0) nextDir = path.get(path.size() - 2).Dir;
			
			if(x < target.x * World.Tilesize) {
				if(x+spd > target.x * World.Tilesize) {
					x=target.x*World.Tilesize;
				}else {
					x+=spd;
				}
			}else if(x > target.x * World.Tilesize) {
				if(x-spd < target.x * World.Tilesize) {
					x=target.x*World.Tilesize;
				}else {
					x-=spd;
				}
			}else if(y < target.y * World.Tilesize) {
				if(y+spd > target.y * World.Tilesize) {
					y=target.y*World.Tilesize;
				}else {
					y+=spd;
				}
			}else if(y > target.y * World.Tilesize) {
				if(y-spd < target.y * World.Tilesize) {
					y=target.y*World.Tilesize;
				}else {
					y-=spd;
				}
			}
				
				
			if(x == target.x * 16 && y == target.y * World.Tilesize) {
				path.remove(path.size() - 1);
			}
				
			
		}
	}
	
	public static boolean isColidding(Entity e1,Entity e2){
		Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.width, e2.height);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}
