package tmge.game.base;

import java.util.Set;
import java.util.function.Function;

import tmge.game.tiles.Tile;
import util.tokens.Coordinate;

/**
 * Abstract class with functions relevant to manipulating pieces on a board.
 * Uses a system of Coordinates which can be selected in order to manipulate as a group (such as rotation).
 */
public abstract class Board {

	/**
	 * Set of Coordinates that can be manipulated as a group using appropriate functions.
	 */
	protected Set<Coordinate> selected;
	
	/**
	 * Resets the board to its initial state.
	 */
	public abstract void loadNew();
	
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
	 * Deselect all available Coordinates.
	 */
	public void deselectAll() {
		selected.clear();
	}
	
	/**
	 * Select all available Coordinates.
	 */
	public abstract void selectAll();
	
	/**
	 * Select all available Coordinates that satisfy the criteria.
	 * @param verifier Truth function that represents Coordinate satisfaction criteria
	 */
	public abstract void selectAll(Function<Coordinate, Boolean> verifier);
	
	/**
	 * Put the given Tile in a location on the board.
	 * @param location Coordinate location to place Tile
	 * @param tile Tile to place
	 * @return Whether the operation was successful
	 */
	public abstract boolean put(Coordinate location, Tile tile);
	
	/**
	 * Remove the Tile in a location on the board.
	 * @param location Coordinate location to remove Tile
	 * @return Whether the operation was successful
	 */
	public abstract boolean remove(Coordinate location);
	
	/**
	 * Whether a Tile can be shifted by a given vector.
	 * @param location Coordinate location of Tile to move
	 * @param vector Coordinate containing vector to move Tile by
	 * @return Whether the Tile can be shifted
	 */
	public abstract boolean canShift(Coordinate location, Coordinate vector);
	
	/**
	 * Shift a Tile by a given vector.
	 * @param location Coordinate location of Tile to move
	 * @param vector Coordinate containing vector to move Tile by
	 */
	public abstract void shift(Coordinate location, Coordinate vector);
	
	/**
	 * Whether a Tile can be swapped with another Tile.
	 * @param location1 Coordinate location of one Tile to swap
	 * @param location2 Coordinate location of other Tile to swap with
	 * @return Whether the Tiles can be swapped
	 */
	public abstract boolean canSwap(Coordinate location1, Coordinate location2);
	
	/**
	 * Swap a Tile with another Tile.
	 * @param location1 Coordinate location of one Tile to swap
	 * @param location2 Coordinate location of other Tile to swap with
	 */
	public abstract void swap(Coordinate location1, Coordinate location2);
	
	/**
	 * Whether a Tile can be moved by rotating it 90 degrees in a given direction around a center.
	 * @param location Coordinate location of Tile to move
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 * @return Whether the Tile can be moved
	 */
	public abstract boolean canRotate(Coordinate location, Coordinate center, boolean clockwise);
	
	/**
	 * Move a Tile by rotating it 90 degrees in a given direction around a center.
	 * @param location Coordinate location of Tile to move
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 */
	public abstract void rotate(Coordinate location, Coordinate center, boolean clockwise);
	
	/**
	 * Whether the selected Tiles can be shifted by a given vector.
	 * @param vector Coordinate containing vector to move Tile by
	 * @return Whether the Tiles can be shifted
	 */
	public abstract boolean canShiftSelected(Coordinate vector);

	/**
	 * Shift the selected Tiles by a given vector.
	 * @param vector Coordinate containing vector to move Tile by
	 */
	public abstract void shiftSelected(Coordinate vector);
	
	/**
	 * Whether the selected Tiles can be swapped with the relative location given by a vector.
	 * @param vector Coordinate containing relative vector to swap to
	 * @return Whether the Tiles can be swapped
	 */
	public abstract boolean canSwapSelected(Coordinate vector);
	
	/**
	 * Swap the selected Tiles with the relative location given by a vector.
	 * @param vector Coordinate containing relative vector to swap to
	 */
	public abstract void swapSelected(Coordinate vector);
	
	/**
	 * Whether the selected Tiles can be moved by rotating them 90 degrees in a given direction around a center.
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 * @return Whether the Tiles can be rotated
	 */
	public abstract boolean canRotateSelected(Coordinate center, boolean clockwise);
	
	/**
	 * Move the selected Tiles by rotating them 90 degrees in a given direction around a center.
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 */
	public abstract void rotateSelected(Coordinate center, boolean clockwise);
	
}
