package geneticAlgorithmSolution;

public class GeneticAlgorithm {

	// This constant determines how likely it is that a section of a route will undergo a mutation
	private static final double PROBABILITY_OF_MUTATION = 0.005;
	// This constant determines how many routes will be randomly selected and considered for crossover
	private static final int SAMPLING_SIZE = 40;

	
	// Evolves a population
	// Process:
	// Creates a new population
	// Chooses two parents via selection process
	// Combines the routes of the two parents to create a route for a child
	// Adds child to new population
	public static Population evolvePopulation(Population population) {
		Population newPopulation = new Population(population.populationSize(), false);

		//Adds the fittest tour from the last generation to next generation to help ensure
		//that there will be at least one strong route in the new population
		newPopulation.addRouteToPopulation(0, population.getFittest());
		

		// for loop that fills the remaining n-1 spots with new routes(children)
		for (int i = 1; i < newPopulation.populationSize(); i++) {
			// Calls parentSelection method to choose parents
			Route parent1 = parentSelection(population);
			Route parent2 = parentSelection(population);
			// Called crossover method to mix parent's routes and a create a new hypothetically fitter route
			Route child = crossover(parent1, parent2);
			// Adds newly create child to new population
			newPopulation.addRouteToPopulation(i, child);
		}

		// Traverses population and calls mutate method to create some genetic randomness
		for (int i = 1; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getRoute(i));
		}

		//Returns population
		return newPopulation;
	}

	/* Crossover Method
	 * Process:
	 * Create new route (child)
	 * Get start and end positions
	 * From parent 1 take the sub section of the route from startPos to endPos and add in the same place to child
	 * Fill the remaining locations slots with locations from parent 2 that have not been visited
	 */
	
	public static Route crossover(Route parent1, Route parent2) {
		// Creates new route(child)
		Route child = new Route();

		// Get start and end sub route positions from parent1s route
		int startPos = (int) (Math.random() * parent1.routeSize());
		int endPos = (int) (Math.random() * parent1.routeSize());

		//If startPos is greater than endPos flip the values
		if(startPos > endPos){
			int temp = startPos;
			startPos = endPos;
			endPos = temp;
		}
		
		// For loop to go through parent1 sub route and add it to child in equivalent location slot
		for (int i = startPos ; i < endPos; i++) {
			child.setCity(i, parent1.getCity(i));
		}

		//For loop to go through each location along Parent2's route
		for (int i = 0; i < parent2.routeSize(); i++) {
			// Checks whether child has "visited" that location
			if (!child.containsCity(parent2.getCity(i))) {
				// For loop to find first available place to put location 
				for (int j = 0; j < child.routeSize(); j++) {
					// When a position is found add this location to child's route
					if (child.getCity(j) == null) {
						child.setCity(j, parent2.getCity(i));
						break;
					}
				}
			}
		}
		//Return child with newly formed route
		return child;
	}

	/* Method to mutate a route
	 * Process:
	 * loop through cities in a route
	 * randomly decide whether city will undergo mutation - low probability
	 * if it undergoes mutation randomly select a second city
	 * swap cities
	 */
	private static void mutate(Route route) {
		// For loop to go through each city in a route
		for (int tourPos1 = 0; tourPos1 < route.routeSize(); tourPos1++) {
			// Using a random number generator and the probability constant of mutation determine whether
			// city will undergo mutation
			if (Math.random() < PROBABILITY_OF_MUTATION) {
				// Find a random city in the route
				int tourPos2 = (int) (route.routeSize() * Math.random());

				// Get the cities at the specified places in the tour
				Location city1 = route.getCity(tourPos1);
				Location city2 = route.getCity(tourPos2);

				// Exchange this two cities
				route.setCity(tourPos2, city1);
				route.setCity(tourPos1, city2);
			}
		}
	}

	/* Method to choose parent for crossover
	 * Process:
	 * create a new population of size outlined by constant defined in this class
	 * - larger the population, better the selection will be, but this will slow down the program
	 * Pick a route at random and add it to this population
	 * Get and return shortest (most fit) route 
	 */
	private static Route parentSelection(Population population) {
		// Creates a population of a defined size
		Population parentPopulation = new Population(SAMPLING_SIZE, false);
		// For each place in the population add a random route
		for (int i = 0; i < SAMPLING_SIZE; i++) {
			int randomRouteReference = (int) (Math.random() * population.populationSize());
			parentPopulation.addRouteToPopulation(i, population.getRoute(randomRouteReference));
		}
		// Get the most fit route in this small population and return that as the parent
		Route fittest = parentPopulation.getFittest();
		return fittest;
	}
}