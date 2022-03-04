package tmge.game.tiles;

/**
 * Singleton Null instance of Tile object.
 */
public class NullTile extends Tile {

	private NullTile() {}
	
	private static NullTile single_instance = null;
		
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
