package tmge.game.base;

import java.util.Set;
import java.util.function.Function;

import tmge.game.tiles.Tile;
import util.tokens.Coordinate;

public abstract class Board {

	protected Set<Coordinate> selected;
	protected double scoreEarned;
	
	public abstract void loadNew();
	
	public double update() {
		double addend = scoreEarned;
		scoreEarned = 0;
		return addend;
	}
	
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
	
	// TODO: validate move and also make it not return boolean ?
	
	public abstract boolean canShift(Coordinate location, Coordinate vector);
	public abstract void shift(Coordinate location, Coordinate vector);
	public abstract boolean canSwap(Coordinate location1, Coordinate location2);
	public abstract void swap(Coordinate location1, Coordinate location2);
	public abstract boolean canRotate(Coordinate location, Coordinate center, boolean clockwise);
	public abstract void rotate(Coordinate location, Coordinate center, boolean clockwise);
	
	public abstract boolean canShiftSelected(Coordinate vector);
	public abstract void shiftSelected(Coordinate vector);
	public abstract boolean canSwapSelected(Coordinate vector);
	public abstract void swapSelected(Coordinate vector);
	public abstract boolean canRotateSelected(Coordinate center, boolean clockwise);
	public abstract void rotateSelected(Coordinate center, boolean clockwise);
	
}
