package tmge.game.bejeweled;

import tmge.ui.Grid;
import util.tokens.Coordinate;

public class BejeweledGrid extends Grid {
	
	protected BejeweledEngine engine;
	
	public BejeweledGrid(int height, int width, BejeweledEngine engine) {
		super(height, width);
		this.engine = engine;
	}

	@Override
	public void onSelect(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPress(String key) {
		return;
	}

}
