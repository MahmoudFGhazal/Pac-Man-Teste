package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public BufferedImage sprite_left;
	
	
	public Player(double x, double y, int width, int height, int depth, double spd, BufferedImage sprite) {
		super(x, y, width, height, depth, spd, sprite);
		sprite_left = Game.spritesheet.getSprite(48,0,16,16);
	}
	
	public void tick(){
		if(livre(lastDir)) {
			if(right) {
				x+=spd;
			}else if(left) {
				x-=spd;
			}else if(up){
				y-=spd;
			}else if(down){
				y+=spd;
			}
		}
		
		Virar();
		PegarPonto();
		
		
		if(Game.acabou.isEmpty()) {
			World.restartGame("/map.png");
			
		}
	}
	
	
	public boolean livre(int Dir) {	
		boolean free = false;
		
		if(Dir == 1) {
			free = World.isFree((int)(x+spd),this.getY());
		}else if(Dir == -1) {
			free = World.isFree((int)(x-spd),this.getY());
		}else if(Dir == 2) {
			free = World.isFree(this.getX(),(int)(y-spd));
		}else if(Dir == -2) {
			free = World.isFree(this.getX(),(int)(y+spd));
		}else {
			return false;
		}
		
		if(Dir != lastDir) nextDir = Dir;
		
		return free;
	}
	
	public void Virar() {
		if(nextDir == 1) {
			if(World.isFree((int)(x+spd),this.getY())) {
				down = false;	
				up = false;
				left = false;
				right = true;
				lastDir = 1;
			}
		}else if(nextDir == -1) {
			if(World.isFree((int)(x-spd),this.getY())) {
				down = false;	
				up = false;
				left = true;
				right = false;
				lastDir = -1;
			}
		}else if(nextDir == 2) {
			if(World.isFree(this.getX(),(int)(y-spd))) {
				down = false;	
				up = true;
				left = false;
				right = false;
				lastDir = 2;
			}
		}else if(nextDir == -2) {
			if(World.isFree(this.getX(),(int)(y+spd))) {
				down = true;	
				up = false;
				left = false;
				right = false;
				lastDir = -2;
			}
		}
	}

	
	public void PegarPonto() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Ponto) {
				if(isColidding(this, e)) {
					Game.entities.remove(e);
					Game.acabou.remove(e);
					Game.pontos+=1000;
					return;
				}
			}
		}
	}
	
	
	public void render(Graphics g) {
		if(lastDir == 1) {
			super.render(g);
		}else {
			g.drawImage(sprite_left,this.getX(),this.getY(),null);
		}
	}
	

}
