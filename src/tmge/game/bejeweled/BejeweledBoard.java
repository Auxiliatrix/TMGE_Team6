package tmge.game.bejeweled;

import java.awt.Color;

import tmge.game.tiles.TiledBoard;
import util.tokens.Coordinate;

/**
 * Implementation of TiledBoard that uses Color as tiles.
 * 
 */
public class BejeweledBoard extends TiledBoard<Color> {

	public BejeweledBoard(int height, int width) {
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
	public synchronized boolean canSwap(Coordinate location1, Coordinate location2) {
		return inBounds(location1) && inBounds(location2) && (Math.abs(location1.minus(location2).y) + Math.abs(location1.minus(location2).x)) == 1;
	}
	
}
