package tmge.game.columns;

import java.awt.event.KeyEvent;

import tmge.ui.Grid;
import util.tokens.Coordinate;

public class ColumnsGrid extends Grid {

	protected ColumnsEngine engine;
		
	public ColumnsGrid(int height, int width, ColumnsEngine engine) {
		super(height, width);
		this.engine = engine;
	}

	@Override
	public void onSelect(Coordinate coordinate) {
		return;
	}

	@Override
	public void onPress(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
	
}
