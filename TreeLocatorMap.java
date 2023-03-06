
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	
	Map<K, Location> map;
	Locator<K> locator;

	public TreeLocatorMap() {
        map = new BST<K,Location>();
        locator = new TreeLocator<K>();
    }
	
	
	// Returns a map with the location of every key.
	
	@Override
	public Map<K, Location> getMap() {
		return map;
	}

	
	// Returns a locator that contains all keys.
	
	@Override
	public Locator<K> getLocator() {
		return locator;
	}

	
	// Inserts the key k at location loc if it does not exist. The first element of
		// the returned pair indicates whether k was inserted, and the second is the
		// number of key comparisons made.
	
	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		boolean inserted=false;
		Pair<Boolean, Integer> findk= map.find(k);
		int numOfComparisons=findk.second; 
		Pair<Boolean, Integer> pair;

		if(findk.first==false) {
			
			map.insert(k, loc);
			locator.add(k, loc);
			inserted=true;	
		}
		
		pair= new Pair<Boolean, Integer>(inserted,numOfComparisons);
		
		return pair;
	}

	
	// Moves the key k to location loc if k exists. The first element of
		// the returned pair indicates whether k exists, and the second is the
		// number of key comparisons made.
	
	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		
		Pair<Boolean, Integer> findk= map.find(k);
		boolean exists=findk.first;
		int numOfComparisons=findk.second; 
		Pair<Boolean, Integer> pair= new Pair<Boolean, Integer>(exists,numOfComparisons);
		
        if(findk.first==true) {
			
        	Location l= map.retrieve();
        	map.remove(k);
			locator.remove(k, l);
			
			map.insert(k, loc);
			locator.add(k, loc);
			
		}
		
		return pair;
	}

	
	// The first element of the returned pair contains the location of key k if it
		// exists, null otherwise. The second element is the number of key comparisons
		// required to find k.
	
	@Override
	public Pair<Location, Integer> getLoc(K k) {

		Location l=null;
		Pair<Boolean, Integer> findk= map.find(k);
		int numOfComparisons=findk.second; 
		Pair<Location, Integer> pair;
		
		if(findk.first==true) {
           l= map.retrieve();
		}
		
		pair= new Pair<Location, Integer>(l,numOfComparisons);

		return pair;
	}


	// Removes the element with key k if it exists. .The first element of the
	// returned pair indicates whether k was removed, and the second is the number
	// of key comparisons required to find k.
	
	@Override
	public Pair<Boolean, Integer> remove(K k) {
		
		Pair<Boolean, Integer> findk= map.find(k);
		int numOfComparisons=findk.second; 
		boolean removed=false;

		Pair<Boolean, Integer> pair;
		
        if(findk.first==true) {
        	
        	Location l= map.retrieve();
        	map.remove(k);
			locator.remove(k, l);
			removed=true;
		}
		
        pair= new Pair<Boolean, Integer>(removed,numOfComparisons);
        
		return pair;
	}

	
	// Returns all keys in the map sorted in increasing order.

	@Override
	public List<K> getAll() {
		List<K> list = map.getAll();
		return list;
	}
	
	
	
	// The first element of the returned pair is a list of all keys located within
		// the rectangle specified by its lower left and upper right corners (inclusive
		// of the boundaries). The second element of the pair is the number of
		// comparisons made.
	
	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		
		List<K> list= new LinkedList<K>();
		int numOfComparisons; 
		Pair<List<K>, Integer> pair;
		
		Pair<List<Pair<Location, List<K>>>, Integer> inRangePair= locator.inRange(lowerLeft,upperRight);
		
		numOfComparisons= inRangePair.second;
		
		List<Pair<Location, List<K>>> inRangeList= inRangePair.first;
		
		if(!inRangeList.empty()) {
		
		inRangeList.findFirst();
		
		while(!inRangeList.last()) {	

		List<K> nodList= inRangeList.retrieve().second;
		
		if(!nodList.empty()) {
			
		nodList.findFirst();
		
		while (!nodList.last()) {
			list.insert(nodList.retrieve());
			nodList.findNext();
		}
		
		list.insert(nodList.retrieve());
		
		} 
		
		inRangeList.findNext();
		}
		
		List<K> nodList= inRangeList.retrieve().second;
		
		if(!nodList.empty()) {
			
			nodList.findFirst();
			
			while (!nodList.last()) {
				list.insert(nodList.retrieve());
				nodList.findNext();
			}
			
			list.insert(nodList.retrieve());
			
			} 
		
		}
		
	    pair= new Pair<List<K>, Integer>(list,numOfComparisons);
	    
		return pair;
	}
	
	

}
