package tmge.game.bejeweled;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;

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
		return false;
	}

}
