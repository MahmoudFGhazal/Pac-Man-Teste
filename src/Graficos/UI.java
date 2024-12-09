package Graficos;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;

public class UI {
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Pontos: " + Game.pontos, (Game.Width - 40) * Game.Scale, 20);
		
	}
	
}
