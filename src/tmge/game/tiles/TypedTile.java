package tmge.game.tiles;

/**
 * Generic TypedTile object to fill spaces on Boards.
 */
public abstract class TypedTile<E> extends Tile {
	protected E type;
	
	public E getType() {
		return type;
	}
	
	public void setType(E type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o instanceof TypedTile<?> ) {
			return type.equals(((TypedTile<E>) o).type);
		}
		return false;
	}
}
