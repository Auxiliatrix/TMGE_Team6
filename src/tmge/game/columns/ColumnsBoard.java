package tmge.game.columns;

import java.awt.Color;

import tmge.game.tiles.TiledBoard;

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
	
}
