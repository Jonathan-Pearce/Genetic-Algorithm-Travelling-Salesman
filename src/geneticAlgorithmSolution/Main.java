package geneticAlgorithmSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// Two constants that can be changed depending on time constraints, size of
	// data or accuracy required
	private static final int SIZE_OF_POPULATION = 100;
	private static final int NUMBER_OF_EVOLUTIONS = 5000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// Setup input
		// Locate specified text file from data package
		// Create new BufferedReader for reading each line of the text file
		InputStream io = Main.class.getResourceAsStream("/data/TSPsample.txt");
		BufferedReader rd = new BufferedReader(new InputStreamReader(io));

		// First integer in text files is the number of cities that the
		// "Salesman" needs to visit
		// int size = Integer.parseInt(rd.readLine());

		// Basic input variables
		// The String will be broken down and converted to the x and y position
		// of each city
		String s = "";
		double x = 0;
		double y = 0;
		int counter = 0;

		// For loop to create location objects
		// Runs the number of times equal to the number of cities that were
		// inputed
		// Reads lines, break into 2 parts, parses both parts and converts to
		// doubles
		// Creates new Location object and adds it to route manager
		while (!s.equals("EOF")) {
			if (counter > 7) {
				StringTokenizer st = new StringTokenizer(s);
				double index = Double.parseDouble(st.nextToken());
				x = Double.parseDouble(st.nextToken());
				y = Double.parseDouble(st.nextToken());
				Location newLocation = new Location(x, y);
				RouteHandler.setCity(newLocation);
			}
			counter++;
			s = rd.readLine();
		}
		System.out.println(RouteHandler.numberOfCities());

		// Creates new population of routes with a defined size
		Population routePopulation = new Population(SIZE_OF_POPULATION, true);

		// variable to store the best route that was created in this first
		// generation
		double initialDistance = routePopulation.getFittest().getDistance();

		// Evolves population a defined number of times
		for (int i = 0; i <= NUMBER_OF_EVOLUTIONS; i++) {
			routePopulation = GeneticAlgorithm.evolvePopulation(routePopulation);
			if (i % 50 == 0) {
				System.out.println("The best route in evolved generation " + (i) + " : " + routePopulation.getFittest().getDistance());
			}
		}

		System.out.println();
		System.out.println("Initial distance: " + initialDistance);
		double finalDistance = routePopulation.getFittest().getDistance();
		System.out.println("Final distance: " + finalDistance);
	}
}