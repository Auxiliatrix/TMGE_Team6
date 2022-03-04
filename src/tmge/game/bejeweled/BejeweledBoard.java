package tmge.game.bejeweled;

import java.awt.Color;

import tmge.game.tiles.TiledBoard;

/**
 * 
 */
public class BejeweledBoard extends TiledBoard<Color> {

	public BejeweledBoard(int height, int width) {
		super(height, width, null);
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
