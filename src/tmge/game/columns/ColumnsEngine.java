package tmge.game.columns;

import java.awt.Color;

import tmge.game.base.FallingEngine;
import tmge.game.base.Player;
import tmge.game.tiles.TiledBoard;
import util.tokens.CoordinateGroup;

public class ColumnsEngine extends FallingEngine<Color> {

	public static final int GROUP_MIN = 4;
	public static final Color[] COLORSET = new Color[] {
		Color.RED,
		Color.ORANGE,
		Color.YELLOW,
		Color.GREEN,
		Color.BLUE,
		Color.MAGENTA,
		Color.WHITE
	};
	
	protected Player player;
	
	protected CoordinateGroup piece;
	
	public ColumnsEngine(TiledBoard<Color> initialState, Player player) {
		super(initialState);
		this.player = player;
		piece = new CoordinateGroup();
	}

	public static Color getRandomValidColor() {
		int selection = (int) (Math.random() * COLORSET.length);
		return COLORSET[selection];
	}

	protected boolean spawn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected CoordinateGroup getFalling() {
		return state.getAll();
	}

	@Override
	protected boolean match() {
		
		spawn();
		return false;
	}

}
