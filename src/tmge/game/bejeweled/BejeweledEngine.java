package tmge.game.bejeweled;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tmge.game.base.FallingEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

public class BejeweledEngine extends FallingEngine<Color> {

	public static final int GROUP_MIN = 3;
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
	
	public BejeweledEngine(BejeweledBoard initialState, Player player) {
		super(initialState);
		this.player = player;
	}
	
	public void trySwap(Coordinate a, Coordinate b) {
		if( state.canSwap(a, b) ) {
			state.swap(a, b);
			if( getMatches().size() == 0 ) {
				state.swap(a, b);
			}
		}
	}
	
	public static Color getRandomValidColor() {
		int selection = (int) (Math.random() * COLORSET.length);
		return COLORSET[selection];
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
	
	@Override
	protected CoordinateGroup getFalling() {
		return state.getAll();
	}
	
	@Override
	protected boolean gravity() {
		boolean result = super.gravity();
		if( result ) {
			spawn();
		}
		return result;
	}
	
	@Override
	protected CoordinateGroup getMatches() {
		CoordinateGroup matched = new CoordinateGroup();
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(0,1)) ) {
			if( group.size() >= 3 ) {
				List<Coordinate> groupList = new ArrayList<Coordinate>(group);
				if( state.get(groupList.get(0)) != state.getDefault() ) {
					matched.addAll(group);
				}
			}
		}
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(1,0)) ) {
			if( group.size() >= 3 ) {
				List<Coordinate> groupList = new ArrayList<Coordinate>(group);
				if( state.get(groupList.get(0)) != state.getDefault() ) {
					matched.addAll(group);
				}
			}
		}
		
		for( CoordinateGroup group : state.getGroups() ) {
			if( group.size() >= 3 ) {
				List<Coordinate> groupList = new ArrayList<Coordinate>(group);
				if( state.get(groupList.get(0)) != state.getDefault() ) {
					matched.addAll(group);
				}
			}
		}
		
		return matched;
	}
	
	@Override
	protected boolean match() {
		CoordinateGroup matched = getMatches();
		
		List<Coordinate> matchList = new ArrayList<Coordinate>(matched);
		for( int f=0; f<matchList.size(); f++ ) {
			Coordinate coord = matchList.get(f);
			state.remove(coord);
			score += f;
		}
		
		return matched.size() > 0;
	}
	
}
