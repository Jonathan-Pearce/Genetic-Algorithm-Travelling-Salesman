package geneticAlgorithmSolution;

public class Population {

    // Array to hold all of the routes in the population
    Route[] routes;

    // Constructor to create a population
    public Population(int populationSize, boolean setUpPopulation) {
    	//Sets array of routes to size of the population variable defined in main
        routes = new Route[populationSize];
        // If we need to create an initial population 
        // Should only run when first generation is being created
        if (setUpPopulation) {
            // For loop to go through and fill every spot in routes array
            for (int i = 0; i < populationSize(); i++) {
                Route newTour = new Route();
                newTour.generateIndividual();
                addRouteToPopulation(i, newTour);
            }
        }
    }
    
    // Adds a tour to the population at the specified index
    public void addRouteToPopulation(int index, Route tour) {
        routes[index] = tour;
    }
    
    // Gets a tour from the population at the specified index
    public Route getRoute(int index) {
        return routes[index];
    }

    // Method to obtain the most fit (shortest) route in the population
    public Route getFittest() {
        Route fittest = routes[0];
        // Loop through every route to check whether it is more fit than current "fittest" route
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getRoute(i).getFitness()) {
                fittest = getRoute(i);
            }
        }
        //Return the most fit (shortest) route 
        return fittest;
    }

    // Returns the size of the population
    public int populationSize() {
        return routes.length;
    }
}