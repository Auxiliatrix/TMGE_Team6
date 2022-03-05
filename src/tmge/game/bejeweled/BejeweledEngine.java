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
	
	public static Color getRandomValidColor() {
		int selection = (int) (Math.random() * COLORSET.length);
		return COLORSET[selection];
	}
	
	@Override
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
	protected boolean match() {
		CoordinateGroup matched = new CoordinateGroup();
		for( int f=0; f<state.getHeight(); f++ ) {
			Color currentColor = null;
			CoordinateGroup currentGroup = new CoordinateGroup();
			
			for( int g=0; g<state.getWidth(); g++ ) {
				Coordinate coord = new Coordinate(f, g);
				if( state.inBounds(new Coordinate(f, g)) ) {
					Color color = state.get(coord);
					if( color == currentColor ) {
						currentGroup.add(coord);
					} else {
						if( currentColor != null && currentGroup.size() >= 3 ) {
							matched.addAll(currentGroup);
						}
						currentGroup.clear();
						currentGroup.add(coord);
						currentColor = color;
					}
				}
			}
			if( currentColor != null && currentGroup.size() >= 3 ) {
				matched.addAll(currentGroup);
			}
		}
		
		for( int g=0; g<state.getWidth(); g++ ) {
			Color currentColor = null;
			CoordinateGroup currentGroup = new CoordinateGroup();
			
			for( int f=0; f<state.getHeight(); f++ ) {
				Coordinate coord = new Coordinate(f, g);
				if( state.inBounds(new Coordinate(f, g)) ) {
					Color color = state.get(coord);
					if( color == currentColor ) {
						currentGroup.add(coord);
					} else {
						if( currentColor != null && currentGroup.size() >= 3 ) {
							matched.addAll(currentGroup);
						}
						currentGroup.clear();
						currentGroup.add(coord);
						currentColor = color;
					}
				}
			}
			if( currentColor != null && currentGroup.size() >= 3 ) {
				matched.addAll(currentGroup);
			}
		}
		
		List<Coordinate> matchList = new ArrayList<Coordinate>(matched);
		for( int f=0; f<matchList.size(); f++ ) {
			Coordinate coord = matchList.get(f);
			state.remove(coord);
			score += f;
		}
		return false;
	}
	
}
