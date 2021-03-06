package tmge.game.base;

import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

/**
 * Abstract class with functions relevant to manipulating pieces on a board.
 * Uses a system of Coordinates which can be selected in order to manipulate as a group (such as rotation).
 */
public abstract class Board {
	
	/**
	 * Resets the board to its initial state.
	 */
	public abstract void loadNew();
	
	/**
	 * Select all available Coordinates.
	 */
	public abstract CoordinateGroup getAll();
	
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
	public abstract boolean canShiftSelected(CoordinateGroup selected, Coordinate vector);

	/**
	 * Shift the selected Tiles by a given vector.
	 * @param vector Coordinate containing vector to move Tile by
	 */
	public abstract void shiftSelected(CoordinateGroup selected, Coordinate vector);
	
	/**
	 * Whether the selected Tiles can be swapped with the relative location given by a vector.
	 * @param vector Coordinate containing relative vector to swap to
	 * @return Whether the Tiles can be swapped
	 */
	public abstract boolean canSwapSelected(CoordinateGroup selected, Coordinate vector);
	
	/**
	 * Swap the selected Tiles with the relative location given by a vector.
	 * @param vector Coordinate containing relative vector to swap to
	 */
	public abstract void swapSelected(CoordinateGroup selected, Coordinate vector);
	
	/**
	 * Whether the selected Tiles can be moved by rotating them 90 degrees in a given direction around a center.
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 * @return Whether the Tiles can be rotated
	 */
	public abstract boolean canRotateSelected(CoordinateGroup selected, Coordinate center, boolean clockwise);
	
	/**
	 * Move the selected Tiles by rotating them 90 degrees in a given direction around a center.
	 * @param center Coordinate location of relative center
	 * @param clockwise Whether to rotate in a clockwise direction
	 */
	public abstract void rotateSelected(CoordinateGroup selected, Coordinate center, boolean clockwise);
	
}
