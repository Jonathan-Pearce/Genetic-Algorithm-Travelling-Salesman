package geneticAlgorithmSolution;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

	// An arraylist to hold order of locations in route
	private ArrayList<Location> route = new ArrayList<Location>();
	// fitness of route
	private double fitness = 0;
	// distance of route
	private int distance = 0;

	// Creates an empty route that will be filled later
	public Route() {
		for (int i = 0; i < RouteHandler.numberOfCities(); i++) {
			route.add(null);
		}
	}

	// Creates a random route for use only in creation of first generation
	public void generateIndividual() {
		// Loop through entire array of route and add a city to each spot
		for (int cityIndex = 0; cityIndex < RouteHandler.numberOfCities(); cityIndex++) {
			setCity(cityIndex, RouteHandler.getCity(cityIndex));
		}
		// Randomly reorder the route
		Collections.shuffle(route);
	}

	// Sets a city in the route array at a specified index
	public void setCity(int tourPosition, Location city) {
		route.set(tourPosition, city);
		// Reset the fitness and distance since the route has been altered in
		// some way
		fitness = 0;
		distance = 0;
	}

	// Gets a city from the route array at a specified index
	public Location getCity(int tourPosition) {
		return (Location) route.get(tourPosition);
	}

	// Calculate fitness of route
	public double getFitness() {
		// Only recalculate fitness if there is no value
		if (fitness == 0) {
			fitness = 1 / (double) getDistance();
		}
		return fitness;
	}

	// Calculate the total distance of the tour
	public int getDistance() {
		// Only calculate distance if there is no value
		if (distance == 0) {
			int routeDistance = 0;
			// For loop to go through every city in route in order
			for (int cityIndex = 0; cityIndex < routeSize(); cityIndex++) {
				// City that we just visited / are traveling from
				Location fromCity = getCity(cityIndex);
				// City that we want to visit / are traveling to
				Location destinationCity;
				// If we are traveling from the last city in our array
				// set the city we want to travel to, to our starting city
				if (cityIndex + 1 < routeSize()) {
					destinationCity = getCity(cityIndex + 1);
				} else {
					destinationCity = getCity(0);
				}
				// add the distance between the two cities to the total route
				// distance
				routeDistance += fromCity.distanceTo(destinationCity);
			}
			distance = routeDistance;
		}
		return distance;
	}

	// Get number of cities in the route
	public int routeSize() {
		return route.size();
	}

	// Check if the route contains a specified city
	public boolean containsCity(Location city) {
		return route.contains(city);
	}
}