package tmge.game.base;

import java.util.Set;
import java.util.function.Function;

import tmge.game.tiles.Tile;
import util.tokens.Coordinate;

public abstract class Board {

	protected Set<Coordinate> selected;
	
	public abstract boolean update();
	
	public boolean select(Coordinate location) {
		if( selected.contains(location) ) {
			return false;
		} else {
			selected.add(location);
			return true;
		}
	}
	
	public boolean deselect(Coordinate location) {
		if( selected.contains(location) ) {
			return false;
		} else {
			selected.remove(location);
			return true;
		}
	}
	
	public boolean isSelected(Coordinate location) {
		return selected.contains(location);
	}
		
	public void deselectAll() {
		selected.clear();
	}
	
	public abstract void selectAll();
	public abstract void selectAll(Function<Coordinate, Boolean> verifier);
	
	public abstract boolean remove(Coordinate location);
	public abstract boolean put(Coordinate location, Tile tile);
	
	public abstract boolean shift(Coordinate location, Coordinate vector);
	public abstract boolean swap(Coordinate location1, Coordinate location2);
	
	public abstract boolean shiftSelected(Coordinate vector);
	public abstract boolean swapSelected(Coordinate vector);
	
	public abstract boolean rotateSelected(Coordinate center, boolean clockwise);
	
}
