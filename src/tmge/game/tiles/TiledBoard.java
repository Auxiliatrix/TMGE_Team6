package tmge.game.tiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import tmge.game.base.Board;
import util.tokens.Coordinate;

public class TiledBoard extends Board {

	public int height;
	public int width;
	
	public Tile[][] tiles;
	
	public TiledBoard(int height, int width) {
		selected = new HashSet<Coordinate>();
		this.tiles = new Tile[height][width];
		loadNew();
	}

	public void loadNew() {
		for( int f=0; f<height; f++ ) {
			for( int g=0; g<width; g++ ) {
				tiles[f][g] = NullTile.getInstance();
			}
		}
	}
	
	public Tile get(Coordinate location) {
		return tiles[location.y][location.x];
	}
	
	public boolean inBounds(Coordinate location) {
		return location.y >= 0 && location.y < height && location.x >= 0 && location.x < width;
	}
	
	public boolean occupied(Coordinate location) {
		return tiles[location.y][location.x] != NullTile.getInstance();
	}

	@Override
	public boolean select(Coordinate location) {
		if( inBounds(location) ) {
			return super.select(location);
		} else {
			return false;
		}
	}
	
	@Override
	public void selectAll() {
		for( int f=0; f<height; f++ ) {
			for( int g=0; g<width; g++ ) {
				select(new Coordinate(f, g));
			}
		}
	}

	@Override
	public void selectAll(Function<Coordinate, Boolean> verifier) {
		for( int f=0; f<height; f++ ) {
			for( int g=0; g<width; g++ ) {
				Coordinate coord = new Coordinate(f, g);
				if( verifier.apply(coord) ) {
					selected.add(coord);
				}
			}
		}
	}
	
	@Override
	public boolean put(Coordinate location, Tile tile) {
		if( inBounds(location) ) {
			tiles[location.y][location.x] = tile;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean remove(Coordinate location) {
		if( occupied(location) ) {
			tiles[location.y][location.x] = NullTile.getInstance();
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean canShift(Coordinate location, Coordinate vector) {
		return inBounds(location.plus(vector));
	}
	
	@Override
	public void shift(Coordinate location, Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return;
		}
		Coordinate target = location.plus(vector);
		tiles[target.y][target.x] = tiles[location.y][location.x];
		tiles[location.y][location.x] = NullTile.getInstance();
	}

	@Override
	public boolean canSwap(Coordinate location1, Coordinate location2) {
		return inBounds(location1) && inBounds(location2);
	}

	@Override
	public void swap(Coordinate location1, Coordinate location2) {
		Tile temp = get(location1);
		tiles[location1.y][location1.x] = tiles[location2.y][location2.x];
		tiles[location2.y][location2.x] = temp;
	}

	@Override
	public boolean canRotate(Coordinate location, Coordinate center, boolean clockwise) {
		if( clockwise ) {
			Coordinate target = new Coordinate(center.y+location.x-center.x, center.x+center.y-location.y);
			return inBounds(target);
		} else {
			Coordinate target = new Coordinate(center.y+center.x-location.x,center.x+location.y-center.y);
			return inBounds(target);
		}
	}
	
	@Override
	public void rotate(Coordinate location, Coordinate center, boolean clockwise) {
		if( clockwise ) {
			Coordinate target = new Coordinate(center.y+location.x-center.x, center.x+center.y-location.y);
			tiles[target.y][target.x] = tiles[location.y][location.x];
			tiles[location.y][location.x] = NullTile.getInstance();
		} else {
			Coordinate target = new Coordinate(center.y+center.x-location.x,center.x+location.y-center.y);
			tiles[target.y][target.x] = tiles[location.y][location.x];
			tiles[location.y][location.x] = NullTile.getInstance();
		}
	}
	
	@Override
	public boolean canShiftSelected(Coordinate vector) {
		for( Coordinate coord : selected ) {
			if( !canShift(coord, vector) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void shiftSelected(Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return;
		}
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
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
	public boolean canSwapSelected(Coordinate vector) {
		for( Coordinate coord : selected ) {
			Coordinate target = coord.plus(vector);
			if( !canSwap(coord, target) || selected.contains(target) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void swapSelected(Coordinate vector) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
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
	public boolean canRotateSelected(Coordinate center, boolean clockwise) {
		for( Coordinate coord : selected ) {
			if( !canRotate(coord, center, clockwise) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void rotateSelected(Coordinate center, boolean clockwise) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
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
