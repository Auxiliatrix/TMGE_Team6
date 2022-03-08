package tmge.game.bejeweled;

import java.awt.event.KeyEvent;

import tmge.ui.ColorInterface;
import util.tokens.Coordinate;

/**
 * Implementation of Grid used for the Bejeweled game.
 */
public class BejeweledGrid extends ColorInterface {

	/**
	 * Contains a BejeweledEngine to track.
	 */
	protected BejeweledEngine engine;
	
	/**
	 * Selected Tile on the grid.
	 */
	protected Coordinate selection;
	
	public BejeweledGrid(int height, int width, BejeweledEngine engine) {
		super(height, width);
		this.engine = engine;
		selection = null;
	}
	
	@Override
	public void onSelect(Coordinate coordinate) {
		if( selection == null ) {
			selection = coordinate;
		} else if( coordinate.equals(selection) ) {
			selection = null;
		} else {
			engine.trySwap(selection, coordinate);
			select(selection);
			select(coordinate);
			selection = null;
		}
	}

	@Override
	public void onPress(KeyEvent key) {
		return;
	}

}
