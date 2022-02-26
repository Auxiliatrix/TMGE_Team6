package tmge.objects.game;

public class StandardBoard {

	public int height;
	public int width;
	
	public Tile[][] tiles;
	
	public StandardBoard(int height, int width) {
		this.tiles = new Tile[height][width];
	}
	
}
