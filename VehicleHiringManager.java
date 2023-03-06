
public class VehicleHiringManager {
	
	LocatorMap<String> locatormap;

	public VehicleHiringManager() {
		
		locatormap= new TreeLocatorMap<String>();

	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return locatormap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		locatormap=locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		
		Pair<Boolean, Integer> pair= locatormap.add(id,loc);
		
		return pair.first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		
        Pair<Boolean, Integer> pair= locatormap.move(id,loc);
		
		return pair.first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		
		 Pair<Boolean, Integer> pair= locatormap.remove(id);
			
	     return pair.first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		
		Pair<Location, Integer> pair= locatormap.getLoc(id);
		
	    return pair.first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		
		List<String> vehiclesInRange= new LinkedList<String>();
		Location l1= new Location(loc.x-r,loc.y-r);
		Location l2= new Location(loc.x+r,loc.y+r);
		Pair<List<String>, Integer> pair= locatormap.getInRange(l1, l2);
		
		vehiclesInRange= pair.first;
		
		
		return vehiclesInRange;

	}
	
	
}
