package tmge.game.boards;

import java.util.HashSet;

import tmge.game.tiles.NullTile;
import tmge.game.tiles.Tile;
import util.tokens.Coordinate;

public class StandardBoard extends Board {

	public int height;
	public int width;
	
	public Tile[][] tiles;
	
	public StandardBoard(int height, int width) {
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
	
	@Override
	public boolean update() {
		return true;
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
	public boolean shift(Coordinate location, Coordinate vector) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rotate(Coordinate location, boolean direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Coordinate location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean put(Coordinate location, Tile tile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean swap(Coordinate location1, Coordinate location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shiftSelected(Coordinate vector) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rotateSelected(boolean direction) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
