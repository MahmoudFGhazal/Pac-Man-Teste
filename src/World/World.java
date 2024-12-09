package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entities.*;
import Main.Game;

public class World {

	public static Tile[] tiles;
	public static int Width,Height;
	public static final int Tilesize = 16;
	public int[] pixels;
	
	
	public World(String path){
		try {
			BufferedImage map;
			map = ImageIO.read(getClass().getResource(path));
			Width = map.getWidth();
			Height = map.getHeight();
			pixels = new int[Width * Height];
			tiles = new Tile[Width * Height];
			map.getRGB(0,0,Width,Height,pixels,0,Width);
			for(int xx = 0; xx < Width; xx++) {
				for(int yy = 0; yy < Height; yy++) {
					int pixelAtual = pixels[xx+yy*Width];
					tiles[xx+yy*Width] = new FloorTile(xx*Tilesize,yy*Tilesize,Tile.TileFloor);
					if(pixelAtual == 0xFF606060) {
						//Floor
						tiles[xx+yy*Width] = new FloorTile(xx*Tilesize,yy*Tilesize,Tile.TileFloor);
					}else if(pixelAtual == 0xFF00137F) {
						//Wall
						tiles[xx+yy*Width] = new WallTile(xx*Tilesize,yy*Tilesize,Tile.TileWall);
					}else if(pixelAtual == 0xFF5DB733) {
						//player
						Game.player.setX(xx*Tilesize);
						Game.player.setY(yy*Tilesize);
					}else if(pixelAtual == 0xFFFF2323) {
						//Enemy
						Enemy e = new Enemy(xx*Tilesize, yy*Tilesize, 16,16,2,0.5,Entity.spriteEnemy1);
						Game.entities.add(e);
					}else if(pixelAtual == 0xFFCF6416) {
						//Enemy
						//Enemy e = new Enemy(xx*Tilesize, yy*Tilesize, 16,16,2,0.8,Entity.spriteEnemy2);
						//Game.entities.add(e);
					}else if(pixelAtual == 0xFFE2E42F) {
						//Pontos
						Ponto ponto = new Ponto(xx*Tilesize, yy*Tilesize, 16, 16, 3, 0, Entity.spriteponto);
						Game.entities.add(ponto);
						Game.acabou.add(ponto);
					}

				
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void render(Graphics g){
		for(int xx = 0; xx <= Width; xx++) {
			for(int yy = 0; yy <= Height; yy++) {
				if(xx < 0 || yy < 0 || xx >= Width || yy >= Height)
					continue;
				Tile tile = tiles[xx + (yy*Width)];
				tile.render(g);
			}
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / Tilesize;
		int y1 = ynext / Tilesize;
		
		int x2 = (xnext+Tilesize-1) / Tilesize;
		int y2 = ynext / Tilesize;
		
		int x3 = xnext / Tilesize;
		int y3 = (ynext+Tilesize-1) / Tilesize;
		
		int x4 = (xnext+Tilesize-1) / Tilesize;
		int y4 = (ynext+Tilesize-1) / Tilesize;

		
		return !((tiles[x1 + (y1*Width)] instanceof WallTile) ||
				(tiles[x2 + (y2*Width)] instanceof WallTile) ||
				(tiles[x3 + (y3*Width)] instanceof WallTile) ||
				(tiles[x4 + (y4*Width)] instanceof WallTile));
	}
	
	public static void restartGame(String level){
		Game.entities.clear();
		Game.acabou.clear();
		Game.player = new Player(0,0,16,16,1,1,Game.spritesheet.getSprite(32, 0,16,16));
		Game.entities.add(Game.player);
		Game.world = new World("/map.png");
		Game.pontos = 0;
		return;
	}
	
	
}
