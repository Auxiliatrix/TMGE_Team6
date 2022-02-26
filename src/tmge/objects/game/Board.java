package tmge.objects.game;

import java.util.Set;

import tmge.objects.tokens.Coordinate;

public abstract class Board {

	public Set<Coordinate> selected;
	
	public abstract boolean update();
	
	public abstract boolean select(Coordinate location);
	public abstract boolean deselect(Coordinate location);
	public abstract void selectAll();
	public abstract void deselectAll();
	
	public abstract boolean shift(Coordinate location, Coordinate vector);
	public abstract boolean rotate(Coordinate location, boolean direction);
	public abstract boolean remove(Coordinate location);
	public abstract boolean put(Coordinate location, Tile tile);
	public abstract boolean swap(Coordinate location1, Coordinate location2);
	
	public abstract boolean shiftSelected(Coordinate vector);
	public abstract boolean rotateSelected(boolean direction);
	
}
