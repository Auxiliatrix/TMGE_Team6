package tmge.game.tiles;

/**
 * Singleton Null instance of Tile object.
 */
public class NullTile extends Tile {

	private static NullTile single_instance = null;
	
	private NullTile() {}
	
	/**
	 * Get the static singleton instance of a NullTile object.
	 * @return NullTile singleton
	 */
	public static NullTile getInstance() {
		if( single_instance == null ) {
			single_instance = new NullTile();
		}
		
		return single_instance;
	}
	
}
