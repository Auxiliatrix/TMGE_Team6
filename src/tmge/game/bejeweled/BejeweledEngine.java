package tmge.game.bejeweled;

import java.util.HashSet;
import java.util.Set;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;

public class BejeweledEngine extends GameEngine {

	protected static final Coordinate GRAVITY_VECTOR = new Coordinate(1,0);
	
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
	
	protected boolean gravity() {
		Set<Coordinate> selected = new HashSet<Coordinate>();
		for( int f=state.getHeight()-1; f>=0; f-- ) {
			selected.addAll(state.getAll(c -> {
				Coordinate target = c.plus(GRAVITY_VECTOR);
				return state.inBounds(target) && state.get(target) == state.getDefault();
			}));
		}
		
		if( selected.size() > 0 ) {
			state.shiftSelected(selected, GRAVITY_VECTOR);
		} else {
			
		}
	}

}
