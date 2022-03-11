package tmge.game.base;

import tmge.ui.WindowCloseReactable;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

/**
 * An implementation of the GameEngine that includes game logic for games that involve gravity.
 * @param <E> Type of Tile to be stored in the Board.
 */
public abstract class FallingEngine<E> extends GameEngine implements WindowCloseReactable {
	
	/**
	 * Direction that gravity moves Tiles in.
	 */
	public Coordinate GRAVITY_VECTOR = new Coordinate(1,0);
	
	/**
	 * State of the Board stored by the Engine.
	 */
	protected TiledBoard<E> state;
	
	protected boolean kill;
	
	public FallingEngine(TiledBoard<E> initialState) {
		this.state = initialState;
		kill = false;
	}

	/**
	 * Default logic checks if any Tiles fell. If not, it makes matches with the stabilized pieces.
	 * @return True by default
	 */
	protected boolean update() {
		if( !gravity() ) {
			match();
		}
		return true;
	}
	
	/**
	 * Run final operations before closing the game.
	 */
	protected abstract void close();
	
	@Override
	/**
	 * Run logic to determine whether game should stop. If it should, run closing operations.
	 * @return Whether the game should continue.
	 */
	public final boolean tick() {
		if( kill || !update() ) {
			close();
			return false;
		}
		return true;
	}
	
	/**
	 * Causes tick to return false no matter what.
	 */
	public void signalClosed() {
		kill = true;
	}
	
	/**
	 * Get all Tiles that will fall this tick.
	 * By default, this function returns all Tiles that have a blank space beneath them.
	 * @return CoordinateGroup Group of Coordinates that will fall
	 */
	protected CoordinateGroup unstable() {
		CoordinateGroup selected = new CoordinateGroup();
		for( int f=state.getHeight()-1; f>=0; f-- ) {
			final int y = f;
			selected.addAll(
					getFalling().getAll(c -> c.y == y).getAll(c -> {
						Coordinate target = c.plus(GRAVITY_VECTOR);
						return state.inBounds(target) && (state.get(target) == state.getDefault() || selected.contains(target));
					})
				);
		}
		return selected;
	}
	
	/**
	 * Enact gravity on the board.
	 * @return Whether any tiles were affected
	 */
	protected boolean gravity() {
		CoordinateGroup selected = unstable();
		
		if( selected.size() > 0 ) {
			state.shiftSelected(selected, GRAVITY_VECTOR);
			return true;
		}
		return false;
	}
	
	/**
	 * Get all Tiles that are subject to gravity.
	 * @return CoordinateGroup Group of Coordinates subject to gravity
	 */
	protected abstract CoordinateGroup getFalling();
	
	/**
	 * Remove applicable Tiles.
	 * @return Whether any tiles were removed
	 */
	protected abstract boolean match();
	
	/**
	 * Get all Tiles that are matched.
	 * @return Whether any Tiles were matched
	 */
	protected abstract CoordinateGroup getMatches();
}
