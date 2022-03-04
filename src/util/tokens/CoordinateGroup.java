package util.tokens;

import java.util.HashSet;
import java.util.function.Function;

public class CoordinateGroup extends HashSet<Coordinate> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6148146180010795998L;
	
	public CoordinateGroup getAll(Function<Coordinate, Boolean> verifier) {
		CoordinateGroup all = new CoordinateGroup();
		for( Coordinate coord : this ) {
			if( verifier.apply(coord) ) {
				all.add(coord);
			}
		}
		return all;
	}

}