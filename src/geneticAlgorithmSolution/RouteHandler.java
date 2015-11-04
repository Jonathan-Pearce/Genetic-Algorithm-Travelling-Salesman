package geneticAlgorithmSolution;

import java.util.ArrayList;

public class RouteHandler {

	// Array that holds all cities in the data
	private static ArrayList<Location> destinationCities = new ArrayList<Location>();

	// Gets the number of cities
	public static int numberOfCities() {
		return destinationCities.size();
	}

	// Adds a city to the arrayList
	public static void setCity(Location city) {
		destinationCities.add(city);
	}

	// Gets a city from the arrayList at a specified index
	public static Location getCity(int index) {
		return (Location) destinationCities.get(index);
	}
}