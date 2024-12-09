package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.AStar;
import World.Vector2i;

public class Enemy extends Entity{
	
	int cont = 0;

	
	public Enemy(int x, int y, int width, int height, int depth, double spd, BufferedImage sprite) {
		super(x, y, width, height,depth, spd, sprite);
	}

	public void tick(){
		
			cont++;
		
			if(path == null || path.size() == 0 || cont>=60*3) {
				Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
				path = AStar.findPath(Game.world, start, end);
				cont = 0;
			}
			
			
			followPath(path);
			
		
			if(x % 16 == 0 && y % 16 == 0) {
				if(Entity.rand.nextInt(100) < 10) {
					Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
					Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
					path = AStar.findPath(Game.world, start, end);
				}
			}
			
		
	}
	
	
	public void render(Graphics g) {
		super.render(g);
	}
	
}
