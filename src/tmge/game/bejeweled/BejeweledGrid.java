package tmge.game.bejeweled;

import tmge.ui.Grid;
import util.tokens.Coordinate;

public class BejeweledGrid extends Grid {
	
	protected BejeweledEngine engine;
	
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
		} else {
			engine.trySwap(selection, coordinate);
			selection = null;
		}
	}

	@Override
	public void onPress(String key) {
		return;
	}

}
