package geneticAlgorithmSolution;

public class Location {
	
	//X and Y coordinate values for location
	double x = 0.0;
    double y = 0.0;
    
    // Constructs a Location at specified x, y coordinate
    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    // Gets the distance from this location to the specified location
    public double distanceTo(Location city){
    	//Calculates the distance from the two points (locations)
        double xDistance = Math.abs(getX() - city.getX());
        double yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        return distance;
    }
    
    // Gets the x coordinate of the location
    public double getX(){
        return this.x;
    }
    // Gets the y coordinate of the location
    public double getY(){
        return this.y;
    }
}
