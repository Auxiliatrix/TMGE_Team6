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

/**
 * Implementation of FallingEngine that uses Color Tiles.
 * Used for the Color game.
 */
public class ColumnsEngine extends FallingEngine<Color> {

	public static final String GAME_NAME = "COLUMNS";
	
	/**
	 * Height of generated columns.
	 */
	public int columnHeight = 3;
	
	/**
	 * Minimum group size to cause matching.
	 */
	public int groupMin = 3;
	
	/**
	 * Valid Colors to choose from.
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
	 * Player playing the game.
	 */
	protected Player player;
	
	/**
	 * Coordinates of Tiles that are in the current "piece" being controlled.
	 */
	protected CoordinateGroup piece;
	
	public ColumnsEngine(TiledBoard<Color> initialState, Player player) {
		super(initialState);
		this.player = player;
		piece = new CoordinateGroup();
	}
	
	/**
	 * Get a random valid color from the pool.
	 * @return A color
	 */
	public Color getRandomValidColor() {
		int selection = (int) (Math.random() * colorSet.length);
		return colorSet[selection];
	}

	/**
	 * Spawn a new column if possible.
	 * @return Whether the column was able to spawn
	 */
	protected boolean spawn() {
		int middleX = state.width / 2;
		for( int f=0; f<columnHeight; f++ ) {
			if( state.occupied(new Coordinate(f, middleX)) ) {
				return false;
			}
		}
		piece.clear();
		for( int f=0; f<columnHeight; f++ ) {
			Coordinate spawnLocation = new Coordinate(f, middleX);
			state.put(spawnLocation, getRandomValidColor());
			piece.add(spawnLocation);
		}
		return true;
	}

	/**
	 * Cycle the Tiles in the piece being controlled.
	 * @param direction Whether to cycle the pieces upwards
	 */
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
	
	/**
	 * Move the piece being controlled by the given vector.
	 * @param vector Coordinate vector to shift by
	 * @return Whether the move was executed
	 */
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
	public boolean update() {
		return (gravity() || match() || spawn());
	}
	
	@Override
	public void close() {
		player.trackPlay(GAME_NAME, score);
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

	/**
	 * Match all applicable Tiles in a row.
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
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(1,1)) ) {
			if( group.size() >= groupMin ) {
				List<Coordinate> groupList = new ArrayList<Coordinate>(group);
				if( state.get(groupList.get(0)) != state.getDefault() ) {
					matched.addAll(group);
				}
			}
		}
		
		for( CoordinateGroup group : state.getGroups(new Coordinate(-1,1)) ) {
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
		
		List<Coordinate> matchList = new ArrayList<Coordinate>(matched);
		for( int f=0; f<matchList.size(); f++ ) {
			Coordinate coord = matchList.get(f);
			state.remove(coord);
			score += f;
		}
		
		return matched.size() > 0;
	}

}
