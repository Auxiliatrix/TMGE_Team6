package util.tokens;

/**
 * Tuple equivalent for y,x coordinate pairs.
 * Override Object's hashCode() method to allow for custom equivalency checking in Collections.
 */
public class Coordinate {

	public int y;
	public int x;
	
	public Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public Coordinate plus(Coordinate addend) {
		return new Coordinate(y + addend.y, x + addend.x);
	}
	
	public Coordinate minus(Coordinate subtrahend) {
		return new Coordinate(y - subtrahend.y, x - subtrahend.x);
	}
	
	public Coordinate times(Coordinate factor) {
		return new Coordinate(y * factor.y, x * factor.x);
	}
	
	@Override
	public boolean equals(Object c) {
		if( c instanceof Coordinate ) {
			return ((Coordinate) c).y == y && ((Coordinate) c).x == x;
		} else {
			return false;
		}
	}
	
	@Override
	/**
	 * Two objects have the same hashcode if they have the same y and x coordinate values.
	 */
	public int hashCode() {
		return toString().hashCode();
	}
	
	public String toString() {
		return String.join(String.join(",", y+"", x+""), "(", ")");
	}
	
}
