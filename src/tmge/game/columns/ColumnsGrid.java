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
		int keyCode = key.getKeyCode();
		switch(keyCode) {
			case KeyEvent.VK_UP:
				engine.cycle(true);
				break;
			case KeyEvent.VK_LEFT:
				engine.movePiece(new Coordinate(0,-1));
				break;
			case KeyEvent.VK_RIGHT:
				engine.movePiece(new Coordinate(0,1));
				break;
			case KeyEvent.VK_DOWN:
				engine.movePiece(new Coordinate(1,0));
				break;
			default:
				break;
		}
	}
	
}
