package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tile {
	
	public static BufferedImage TileFloor = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TileWall = Game.spritesheet.getSprite(16,0,16,16);

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x, y, null);
	}

}
