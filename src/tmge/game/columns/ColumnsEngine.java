package tmge.game.columns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
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
		piece.clear();
		for( int f=0; f<COLUMN_HEIGHT; f++ ) {
			Coordinate spawnLocation = new Coordinate(f, middleX);
			state.put(spawnLocation, getRandomValidColor());
			piece.add(spawnLocation);
		}
		return true;
	}

	public void cycle(boolean direction) {
		List<Coordinate> group = new ArrayList<Coordinate>(piece);
		group.sort(new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				return o1.y - o2.y;
			}
		});
		if( direction ) {
			Color first = state.get(group.get(0));
			for( int f=0; f<group.size()-1; f++ ) {
				state.put(group.get(f), state.get(group.get(f+1)));
			}
			state.put(group.get(group.size()-1), first);
		} else {
			Color last = state.get(group.get(group.size()-1));
			for( int f=group.size(); f>=1; f-- ) {
				state.put(group.get(f), state.get(group.get(f-1)));
			}
			state.put(group.get(0), last);
		}
	}
	
	public boolean movePiece(Coordinate vector) {
		if( state.canShiftSelected(piece, vector) ) {
			state.shiftSelected(piece, vector);
			CoordinateGroup newPiece = new CoordinateGroup();
			for( Coordinate piecePiece : piece ) {
				newPiece.add(piecePiece.plus(vector));
			}
			piece = newPiece;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean tick() {
		return gravity() || match() || spawn();
	}
	
	@Override
	protected boolean gravity() {
		CoordinateGroup unstable = unstable();
		boolean pieceFalling = true;
		for( Coordinate piecePiece : piece ) {
			pieceFalling &= unstable.contains(piecePiece);
		}
		if( pieceFalling ) {
			CoordinateGroup newPiece = new CoordinateGroup();
			for( Coordinate coord : piece ) {
				newPiece.add(coord.plus(GRAVITY_VECTOR));
			}
			piece = newPiece;
		}
		return super.gravity();
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
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(-1,1)) ) {
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
