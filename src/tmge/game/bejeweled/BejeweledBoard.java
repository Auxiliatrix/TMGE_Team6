package tmge.game.bejeweled;

import java.awt.Color;

import tmge.game.tiles.TiledBoard;

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

}
