package tmge.game.tiles;

import java.util.ArrayList;
import java.util.List;

import tmge.game.base.Board;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

/**
 * Implementation of Board that associates each coordinate in the dimensions with one tile.
 * Does not permit operations that would move items out of bounds.
 * All tile moves leave behind default instances.
 * Is synchronized in order to allow concurrent updating and manipulating.
 */
public abstract class TiledBoard<E> extends Board {

	public int height;
	public int width;
	
	/**
	 * Static-sized array of Tiles in every possible Coordinate.
	 */
	public E[][] tiles;
	protected E defaultTile;
	
	public TiledBoard(int height, int width, E defaultTile) {
		this.height = height;
		this.width = width;
		this.defaultTile = defaultTile;
		loadNew();
	}

	/**
	 * Initialize tiles array
	 */
	public abstract void loadNew();
	
	public E getDefault() {
		return defaultTile;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public synchronized E get(Coordinate location) {
		return tiles[location.y][location.x];
	}
	
	public synchronized boolean inBounds(Coordinate location) {
		return location.y >= 0 && location.y < height && location.x >= 0 && location.x < width;
	}
	
	public synchronized boolean occupied(Coordinate location) {
		return tiles[location.y][location.x] != NullTile.getInstance();
	}
	
	@Override
	public synchronized CoordinateGroup getAll() {
		CoordinateGroup all = new CoordinateGroup();
		for( int f=0; f<height; f++ ) {
			for( int g=0; g<width; g++ ) {
				all.select(new Coordinate(f, g));
			}
		}
		return all;
	}
	
	public synchronized boolean put(Coordinate location, E object) {
		if( inBounds(location) ) {
			tiles[location.y][location.x] = object;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public synchronized boolean remove(Coordinate location) {
		if( occupied(location) ) {
			tiles[location.y][location.x] = defaultTile;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public synchronized boolean canShift(Coordinate location, Coordinate vector) {
		return inBounds(location.plus(vector));
	}
	
	@Override
	public synchronized void shift(Coordinate location, Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return;
		}
		Coordinate target = location.plus(vector);
		tiles[target.y][target.x] = tiles[location.y][location.x];
		tiles[location.y][location.x] = defaultTile;
	}

	@Override
	public synchronized boolean canSwap(Coordinate location1, Coordinate location2) {
		return inBounds(location1) && inBounds(location2);
	}

	@Override
	public synchronized void swap(Coordinate location1, Coordinate location2) {
		E temp = get(location1);
		tiles[location1.y][location1.x] = tiles[location2.y][location2.x];
		tiles[location2.y][location2.x] = temp;
	}

	@Override
	public synchronized boolean canRotate(Coordinate location, Coordinate center, boolean clockwise) {
		if( clockwise ) {
			Coordinate target = new Coordinate(center.y+location.x-center.x, center.x+center.y-location.y);
			return inBounds(target);
		} else {
			Coordinate target = new Coordinate(center.y+center.x-location.x,center.x+location.y-center.y);
			return inBounds(target);
		}
	}
	
	@Override
	public synchronized void rotate(Coordinate location, Coordinate center, boolean clockwise) {
		if( clockwise ) {
			Coordinate target = new Coordinate(center.y+location.x-center.x, center.x+center.y-location.y);
			tiles[target.y][target.x] = tiles[location.y][location.x];
			tiles[location.y][location.x] = defaultTile;
		} else {
			Coordinate target = new Coordinate(center.y+center.x-location.x,center.x+location.y-center.y);
			tiles[target.y][target.x] = tiles[location.y][location.x];
			tiles[location.y][location.x] = defaultTile;
		}
	}
	
	@Override
	public synchronized boolean canShiftSelected(CoordinateGroup selected, Coordinate vector) {
		for( Coordinate coord : selected.getSelected() ) {
			if( !canShift(coord, vector) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public synchronized void shiftSelected(CoordinateGroup selected, Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return;
		}
		List<Coordinate> origins = new ArrayList<Coordinate>(selected.getSelected());
		List<Coordinate> targets = new ArrayList<Coordinate>();
		for( Coordinate coord : origins ) {
			Coordinate target = coord.plus(vector);
			targets.add(target);
		}
		
		while( !origins.isEmpty() ) {
			Coordinate origin = origins.remove(0);
			Coordinate target = targets.remove(0);
			if( origins.contains(target) ) {
				origins.add(origin);
				targets.add(target);
			} else {
				shift(origin, vector);
			}
		}
	}
	
	@Override
	public synchronized boolean canSwapSelected(CoordinateGroup selected, Coordinate vector) {
		for( Coordinate coord : selected.getSelected() ) {
			Coordinate target = coord.plus(vector);
			if( !canSwap(coord, target) || selected.isSelected(target) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public synchronized void swapSelected(CoordinateGroup selected, Coordinate vector) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected.getSelected());
		List<Coordinate> targets = new ArrayList<Coordinate>();
		for( Coordinate coord : origins ) {
			Coordinate target = coord.plus(vector);
			targets.add(target);
		}
		
		for( int f=0; f<targets.size(); f++ ) {
			swap(origins.get(f), targets.get(f));
		}
	}
	
	@Override
	public synchronized boolean canRotateSelected(CoordinateGroup selected, Coordinate center, boolean clockwise) {
		for( Coordinate coord : selected.getSelected() ) {
			if( !canRotate(coord, center, clockwise) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public synchronized void rotateSelected(CoordinateGroup selected, Coordinate center, boolean clockwise) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected.getSelected());
		List<Coordinate> targets = new ArrayList<Coordinate>();
		for( Coordinate coords : origins ) {
			if( clockwise ) {
				targets.add(new Coordinate(center.y+coords.x-center.x, center.x+center.y-coords.y));
			} else {
				targets.add(new Coordinate(center.y+center.x-coords.x,center.x+coords.y-center.y));
			}
		}
		
		while( !origins.isEmpty() ) {
			Coordinate origin = origins.remove(0);
			Coordinate target = targets.remove(0);
			if( origins.contains(target) ) {
				origins.add(origin);
				targets.add(target);
			} else {
				rotate(origin, center, clockwise);
			}
		}
	}
	
}
