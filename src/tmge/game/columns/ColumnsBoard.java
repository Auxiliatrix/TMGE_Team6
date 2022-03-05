package tmge.game.columns;

import java.awt.Color;
import java.util.List;

import tmge.game.base.TiledBoard;
import util.tokens.Coordinate;

public class ColumnsBoard extends TiledBoard<Color> {

	public ColumnsBoard(int height, int width) {
		super(height, width, Color.GRAY);
	}

	@Override
	public void loadNew() {
		tiles = new Color[height][width];
		for( int f=0; f<height; f++ ) {
			for( int g=0; g<width; g++ ) {
				tiles[f][g] = defaultTile;
			}
		}
	}
	
	@Override
	public synchronized boolean canShift(Coordinate location, Coordinate vector) {
		return super.canShift(location, vector) && get(location.plus(vector)) == defaultTile;
	}
	
	public synchronized void cycle(List<Coordinate> group, boolean direction) {
		if( direction ) {
			Color first = get(group.get(0));
			for( int f=0; f<group.size()-1; f++ ) {
				put(group.get(f), get(group.get(f+1)));
			}
			put(group.get(group.size()-1), first);
		} else {
			Color last = get(group.get(group.size()-1));
			for( int f=group.size(); f>=1; f-- ) {
				put(group.get(f), get(group.get(f-1)));
			}
			put(group.get(0), last);
		}
	}
	
}
