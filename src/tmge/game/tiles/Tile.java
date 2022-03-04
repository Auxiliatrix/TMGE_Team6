package tmge.game.tiles;

/**
 * Generic Tile object to fill spaces on Boards.
 */
public class Tile<E> {
	protected E type;
	
	protected Tile(E type) {
		this.type = type;
	}
	
	public E getType() {
		return type;
	}
	
	public void setType(E type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o instanceof Tile<?> ) {
			return type.equals(((Tile<E>) o).type);
		}
		return false;
	}
}
