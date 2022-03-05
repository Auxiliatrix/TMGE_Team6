package tmge.game.bejeweled;

import java.awt.Color;

import tmge.game.base.TiledBoard;
import tmge.ui.UserInterface;

public class BejeweledUI extends UserInterface {

	protected BejeweledEngine engine;
	
	public BejeweledUI(TiledBoard<Color> board, BejeweledGrid grid) {
		super(board, grid);
	}

}
