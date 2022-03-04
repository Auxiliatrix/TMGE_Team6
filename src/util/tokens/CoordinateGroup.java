package util.tokens;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class CoordinateGroup {
	
	/**
	 * Set of Coordinates that can be manipulated as a group using appropriate functions.
	 */
	protected Set<Coordinate> selected;

	public CoordinateGroup() {
		selected = new HashSet<Coordinate>();
	}
	
	public CoordinateGroup(Collection<Coordinate> coordinates) {
		selected = new HashSet<Coordinate>(coordinates);
	}
	
	public CoordinateGroup(CoordinateGroup cg) {
		this(cg.getSelected());
	}
	
	public Set<Coordinate> getSelected() {
		return new HashSet<Coordinate>(selected);
	}
	
	public CoordinateGroup getAll(Function<Coordinate, Boolean> verifier) {
		CoordinateGroup all = new CoordinateGroup();
		for( Coordinate coord : selected ) {
			if( verifier.apply(coord) ) {
				all.select(coord);
			}
		}
		return all;
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
	
	public boolean selectAll(CoordinateGroup location) {
		int prevSize = selected.size();
		selected.addAll(location.getSelected());
		return prevSize != selected.size();
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
