package tmge.game.bejeweled;

import java.util.HashSet;
import java.util.Set;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;

public class BejeweledEngine extends GameEngine {

	protected BejeweledBoard state;
	protected Player player;
	
	public BejeweledEngine(BejeweledBoard initialState, Player player) {
		super();
		this.state = initialState;
		this.player = player;
	}

	@Override
	public boolean tick() {
		Coordinate gravityVector = new Coordinate(1,0);
		Set<Coordinate> selected = new HashSet<Coordinate>(state.getAll(c -> {
			Coordinate target = c.plus(gravityVector);
			return !state.inBounds(target) && state.get(target) == null;
		}));
		return false;
	}

}
