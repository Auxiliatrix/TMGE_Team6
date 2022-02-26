package tmge.main;

import java.util.HashSet;
import java.util.Set;

import tmge.objects.tokens.Coordinate;

public class Main {

	public static void main(String[] args) {
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		Coordinate a = new Coordinate(0, 0);
		Coordinate a2 = new Coordinate(0, 0);
		Coordinate b = new Coordinate(0, 1);
		Coordinate b2 = new Coordinate(0, 1);
		coordinates.add(a);
		coordinates.add(a2);
		System.out.println(coordinates);
		coordinates.add(b);
		coordinates.remove(a2);
		System.out.println(coordinates);
		System.out.println(coordinates.contains(b2));
	}
	
}
