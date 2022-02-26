package tmge.game.tiles;

public class NullTile extends Tile {

	private static NullTile single_instance = null;
	
	private NullTile() {}
	
	public static NullTile getInstance() {
		if( single_instance == null ) {
			single_instance = new NullTile();
		}
		
		return single_instance;
	}
	
}
