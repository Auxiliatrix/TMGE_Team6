package tmge.game.tiles;

public class NullTile extends Tile {

	private static NullTile single_instance = null;
	
	private NullTile() {
		this.type = null;
	}
	
	public static NullTile getInstance() {
		if( single_instance == null ) {
			single_instance = new NullTile();
		}
		
		return single_instance;
	}
	
	@Override
	public boolean cycle(boolean direction) {
		return false;
	}
	
	
}
