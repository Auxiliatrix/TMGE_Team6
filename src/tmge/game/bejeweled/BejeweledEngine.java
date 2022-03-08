package tmge.game.bejeweled;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tmge.game.base.FallingEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

/**
 * Implementation of FallingEngine that uses Color Tiles.
 * Used for the Bejeweled game.
 */
public class BejeweledEngine extends FallingEngine<Color> {

	/**
	 * Minimum number of Tiles required for a group.
	 */
	public int groupMin = 3;
	
	/**
	 * Valid colors for this Board.
	 */
	public Color[] colorSet = new Color[] {
		new Color(255,102,102),
		new Color(255,204,51),
		new Color(255,255,153),
		new Color(0,204,0),
		new Color(51,153,255),
		new Color(102,0,153),
		new Color(204,204,204),
	};
	
	/**
	 * Player playing the current game
	 */
	protected Player player;
	
	public BejeweledEngine(BejeweledBoard initialState, Player player) {
		super(initialState);
		this.player = player;
	}
	
	/**
	 * If the swap is possible, execute the swap.
	 * @param a Coordinate to swap
	 * @param b Coordinate to swap with
	 */
	public void trySwap(Coordinate a, Coordinate b) {
		if( state.canSwap(a, b) ) {
			state.swap(a, b);
			if( getMatches().size() == 0 ) {
				state.swap(a, b);
			}
		}
	}
	
	/**
	 * Get a random color from the valid color set.
	 * @return A color
	 */
	public Color getRandomValidColor() {
		int selection = (int) (Math.random() * colorSet.length);
		return colorSet[selection];
	}
	
	/**
	 * Spawn Tiles at the top if there is unoccupied space.
	 * @return Whether any Tiles were spawned
	 */
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
	
	/**
	 * Match all applicable Tiles in a cardinal row.
	 */
	@Override
	protected CoordinateGroup getMatches() {
		CoordinateGroup matched = new CoordinateGroup();
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(0,1)) ) {
			if( group.size() >= groupMin ) {
				List<Coordinate> groupList = new ArrayList<Coordinate>(group);
				if( state.get(groupList.get(0)) != state.getDefault() ) {
					matched.addAll(group);
				}
			}
		}
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(1,0)) ) {
			if( group.size() >= groupMin ) {
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
		boolean bugged = false;
		List<Coordinate> matchList = new ArrayList<Coordinate>(matched);
		for( int f=0; f<matchList.size(); f++ ) {
			Coordinate coord = matchList.get(f);
			if( coord.y == 0 ) {
				bugged = true;
			}
			state.remove(coord);
			score += f;
		}
		
		if( bugged ) {
			spawn();
		}
		
		return matched.size() > 0;
	}
	
}
