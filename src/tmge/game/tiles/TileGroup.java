package tmge.game.tiles;

import java.util.HashSet;
import java.util.Set;

import util.tokens.Coordinate;

public class TileGroup {
	
	/**
	 * Set of Coordinates that can be manipulated as a group using appropriate functions.
	 */
	protected Set<Coordinate> selected;

	public TileGroup() {
		selected = new HashSet<Coordinate>();
	}
	
	public Set<Coordinate> getSelected() {
		return new HashSet<Coordinate>(selected);
	}
	
	/**
	 * Check if a given Coordinate is in the selection.
	 * @param location Coordinate to check
	 * @return Whether the given Coordinate is in the selection
	 */
	public boolean isSelected(Coordinate location) {
		return selected.contains(location);
	}
	
	/**
	 * Select the given Coordinate.
	 * @param location Coordinate to add to selection
	 * @return Whether the selection of Coordinates was modified
	 */
	public boolean select(Coordinate location) {
		if( selected.contains(location) ) {
			return false;
		} else {
			selected.add(location);
			return true;
		}
	}
	
	/**
	 * Deselect the given Coordinate.
	 * @param location Coordinate to remove from selection
	 * @return Whether the selection of Coordinates was modified
	 */
	public boolean deselect(Coordinate location) {
		if( selected.contains(location) ) {
			return false;
		} else {
			selected.remove(location);
			return true;
		}
	}
	
	/**
	 * Deselect the given Coordinate.
	 * @param location Coordinate to remove from selection
	 * @return Whether the selection of Coordinates was modified
	 */
	public void deselectAll() {
		selected.clear();
	}
	
}
