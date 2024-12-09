package World;

public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost,gCost,hCost;
	public int Dir;
	
	public Node(Vector2i tile, Node parent, int Dir, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = gCost + hCost;
		this.Dir = Dir;
	}
	
	
}
