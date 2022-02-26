package util.tokens;

public class Coordinate {

	public int y;
	public int x;
	
	public Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
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
	public int hashCode() {
		return toString().hashCode();
	}
	
	public String toString() {
		return String.join(String.join(",", y+"", x+""), "(", ")");
	}
	
}
