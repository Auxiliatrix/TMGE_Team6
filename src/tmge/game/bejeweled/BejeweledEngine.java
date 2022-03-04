package tmge.game.bejeweled;

import java.awt.Color;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

public class BejeweledEngine extends GameEngine {

	public static final Coordinate GRAVITY_VECTOR = new Coordinate(1,0);
	public static final Color[] COLORSET = new Color[] {
		Color.RED,
		Color.ORANGE,
		Color.YELLOW,
		Color.GREEN,
		Color.BLUE,
		Color.MAGENTA,
		Color.WHITE
	};
	
	protected BejeweledBoard state;
	protected Player player;
	
	public BejeweledEngine(BejeweledBoard initialState, Player player) {
		super();
		this.state = initialState;
		this.player = player;
	}

	@Override
	public boolean tick() {
		if( gravity() ) {
			spawn();
		} else {
			score();
		}
		
		return false;
	}
	
	protected boolean gravity() {
		CoordinateGroup selected = new CoordinateGroup();
		for( int f=state.getHeight()-1; f>=0; f-- ) {
			final int y = f;
			selected.addAll(
					state.getAll().getAll(c -> c.y == y).getAll(c -> {
						Coordinate target = c.plus(GRAVITY_VECTOR);
						return state.inBounds(target) && (state.get(target) == state.getDefault() || selected.contains(target));
					})
				);
		}
		
		if( selected.size() > 0 ) {
			state.shiftSelected(selected, GRAVITY_VECTOR);
			return true;
		}
		return false;
	}
	
	protected boolean spawn() {
		CoordinateGroup selected = state.getAll().getAll(c -> c.y == 0 && state.get(c) == state.getDefault());
		if( selected.size() > 0 ) {
			for( Coordinate coord : selected ) {
				state.put(coord, getRandomValidColor());
			}
			return true;
		}
		return false;
	}
	
	protected boolean score() {
		// TODO: implement scoring
		return false;
	}
	
	public static Color getRandomValidColor() {
		int selection = (int) (Math.random() * COLORSET.length);
		return COLORSET[selection];
	}

}
