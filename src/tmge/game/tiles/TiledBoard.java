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
		initialize();
	}

	protected void initialize() {
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
	public boolean update() {
		return true;
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
	public boolean shift(Coordinate location, Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return true;
		}
		Coordinate target = location.plus(vector);
		if( inBounds(target) ) {
			tiles[target.y][target.x] = tiles[location.y][location.x];
			tiles[location.y][location.x] = NullTile.getInstance();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean swap(Coordinate location1, Coordinate location2) {
		if( inBounds(location1) && inBounds(location2) ) {
			Tile temp = get(location1);
			tiles[location1.y][location1.x] = tiles[location2.y][location2.x];
			tiles[location2.y][location2.x] = temp;
			return true;
		}
		return false;
	}

	@Override
	public boolean shiftSelected(Coordinate vector) {
		if( vector.y == 0 && vector.x == 0 ) {
			return true;
		}
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
		List<Coordinate> targets = new ArrayList<Coordinate>();
		List<Tile> temps = new ArrayList<Tile>();
		for( Coordinate coord : origins ) {
			temps.add(tiles[coord.y][coord.x]);
			Coordinate target = coord.plus(vector);
			if( inBounds(target) ) {
				targets.add(target);
			} else {
				return false;
			}
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
		
		return true;
	}
	
	@Override
	public boolean swapSelected(Coordinate vector) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
		List<Coordinate> targets = new ArrayList<Coordinate>();
		for( Coordinate coord : origins ) {
			Coordinate target = coord.plus(vector);
			if( inBounds(target) && !selected.contains(target) ) {
				targets.add(target);
			} else {
				return false;
			}
		}
		
		for( int f=0; f<targets.size(); f++ ) {
			swap(origins.get(f), targets.get(f));
		}
		
		return true;
	}
	
	@Override
	public boolean rotate(Coordinate location, Coordinate center, boolean clockwise) {
		// TODO
		
		return false;
	}
	
	@Override
	public boolean rotateSelected(Coordinate center, boolean clockwise) {
		List<Coordinate> origins = new ArrayList<Coordinate>(selected);
		List<Coordinate> targets = new ArrayList<Coordinate>();
		for( Coordinate coords : origins ) {
			
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
}
