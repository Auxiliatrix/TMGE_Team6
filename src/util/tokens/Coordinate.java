package util.tokens;

public class Coordinate {

	public int x;
	public int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object c) {
		if( c instanceof Coordinate ) {
			return ((Coordinate) c).x == x && ((Coordinate) c).y == y;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	public String toString() {
		return String.join(String.join(",", x+"", y+""), "(", ")");
	}
	
}
