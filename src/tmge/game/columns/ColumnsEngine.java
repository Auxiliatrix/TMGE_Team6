package tmge.game.columns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tmge.game.base.FallingEngine;
import tmge.game.base.Player;
import tmge.game.base.TiledBoard;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

public class ColumnsEngine extends FallingEngine<Color> {

	public static final int COLUMN_HEIGHT = 3;
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
		System.out.println("spawning");
		int middleX = state.width / 2;
		for( int f=0; f<COLUMN_HEIGHT; f++ ) {
			if( state.occupied(new Coordinate(f, middleX)) ) {
				return false;
			}
		}
		for( int f=0; f<COLUMN_HEIGHT; f++ ) {
			state.put(new Coordinate(f, middleX), getRandomValidColor());
		}
		return true;
	}

	@Override
	public boolean tick() {
		return gravity() || match() || spawn();
	}
	
	@Override
	protected CoordinateGroup getFalling() {
		return state.getAll().getAll(c -> state.occupied(c));
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
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(1,1)) ) {
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
