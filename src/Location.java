
public class Location {
	private double x, y;
	
	public Location(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Returns the distance between two locations.
	 * @param l1, l2: the two locations
	 * @return the distance
	 */
	public static double distance(Location l1, Location l2){
		return Math.sqrt(Math.pow((l1.getX() - l2.getX()), 2) + Math.pow((l1.getY() - l2.getY()), 2));
	}
	
	public String toString(){
		return "("+ x +", "+ y +")";
	}
}

