 
class TLNode<T>{
	
		Location location;
        List<T> data = new LinkedList<T>();
        TLNode<T> Child1 , Child2, Child3 , Child4;
		
		public TLNode(Location loc, T e) {
			location=loc;
			data.insert(e);
			Child1=Child2=Child3=Child4=null;
		}
			
	}
 
 
public class TreeLocator<T> implements Locator<T> {
	
	TLNode<T> root, current;
	
	public TreeLocator() {
		root=current=null;
	}
	

	// Inserts e at location loc and returns the number of comparisons made when
		// searching for loc.
	
	@Override
	public int add(T e, Location loc) {
		
		int numOfComparisons=0;
		
		if(root==null) {
			root=current=new TLNode<T>(loc, e);
		}
		else {
			
			TLNode<T> c=root;
			TLNode<T> cp=null;
			
		while(c!=null) {
			
			cp=c;
			numOfComparisons++;
			
			if((c.location.x == loc.x ) && (c.location.y == loc.y)) {
				c.data.insert(e);
				break;
			}
			else  if (( loc.x  < c.location.x ) && ( loc.y <= c.location.y ))
                    c = c.Child1;
			else if (( loc.x  <= c.location.x ) && ( loc.y > c.location.y ))
	                c = c.Child2;
	        else if (( loc.x  > c.location.x ) && ( loc.y >= c.location.y ))
	                c = c.Child3;
	        else if (( loc.x  >= c.location.x ) && ( loc.y < c.location.y ))
	                c = c.Child4;
      
		 }
		
		TLNode<T> newnode= new TLNode<T>(loc, e);
		
		 if (( loc.x  < cp.location.x ) && ( loc.y <= cp.location.y ))
			 cp.Child1 = newnode;
         else if (( loc.x  <= cp.location.x ) && ( loc.y > cp.location.y ))
        	 cp.Child2 = newnode;
         else if (( loc.x  > cp.location.x ) && ( loc.y >= cp.location.y ))
        	 cp.Child3 = newnode;
         else if (( loc.x  >= cp.location.x ) && ( loc.y < cp.location.y ))
        	 cp.Child4 = newnode;

     current = newnode;
			
	}		
		
		return numOfComparisons;
	}

	
	// The first element of the returned pair is a list containing all elements
		// located at loc. If loc does not exist or has no elements, the returned list
		// is empty. The second element of the pair is the number of comparisons made
		// when searching for loc.
	
	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		
        int numOfComparisons=0;
        List<T> list= new LinkedList<T>();
        Pair<List<T>, Integer> pair;
		
		if(root!=null) {
			
			TLNode<T> c=root;
			
		while(c!=null) {
			
			numOfComparisons++;
			
			if((c.location.x == loc.x ) && (c.location.y == loc.y)) {
				list=c.data;
				break;
			}
			else  if (( loc.x  < c.location.x ) && ( loc.y <= c.location.y ))
                    c = c.Child1;
			else if (( loc.x  <= c.location.x ) && ( loc.y > c.location.y ))
	                c = c.Child2;
	        else if (( loc.x  > c.location.x ) && ( loc.y >= c.location.y ))
	                c = c.Child3;
	        else if (( loc.x  >= c.location.x ) && ( loc.y < c.location.y ))
	                c = c.Child4;
      
		 }
				
	}		
		
		pair= new Pair<List<T>, Integer>(list,numOfComparisons);
		
		return pair;
	}

	
	// Removes all occurrences of element e from location loc. The first element
		// of the returned pair is true if e is removed and false if loc does not exist
		// or e does not exist in loc. The second element of the pair is the number of
		// comparisons made when searching for loc.
	
	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		
		boolean exists=false;
		Pair<Boolean, Integer> pair;
		Pair<List<T>, Integer> nodlist= get(loc);
		int numOfComparisons=nodlist.second;
	      
	    if(!nodlist.first.empty()) {
	    	
	    	nodlist.first.findFirst();
	    	
	    	while(!nodlist.first.last()) {
	    		
	    		if (e.equals(nodlist.first.retrieve()))
                {
	    			nodlist.first.remove();
	    			exists = true;
                }
                else
                	nodlist.first.findNext();
            }
	    	
            if (e.equals(nodlist.first.retrieve()))
            {
            	nodlist.first.remove();
            	exists = true;
            }
            
	    	}
	    	
	    
		pair= new Pair<Boolean, Integer>(exists,numOfComparisons);

	      return pair;
	}

	
	// Returns all locations and the elements they contain.

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> list = new LinkedList<Pair<Location, List<T>>>();
		recGetAll(root,list);
		return list;
	}
	
	private void recGetAll(TLNode<T> c, List<Pair<Location, List<T>>> list) {
	if (c == null) 
	return;
	else {
		Pair<Location, List<T>> pair= new Pair<Location, List<T>>(c.location, c.data);
        list.insert(pair);
		recGetAll(c.Child1,list);
		recGetAll(c.Child2,list);
		recGetAll(c.Child3,list);
		recGetAll(c.Child4,list);
	}
	}

	
	// The first element of the returned pair is a list of all locations and their
		// data if they are located within the rectangle specified by its lower left and
		// upper right corners (inclusive of the boundaries). The second element of the
		// pair is the number of comparisons made.
	
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		
		int numOfComparisons=0;
		List<Pair<Location, List<T>>> list = new LinkedList<Pair<Location, List<T>>>();
		Pair<List<Pair<Location, List<T>>>, Integer>  pair= new Pair<List<Pair<Location, List<T>>>, Integer>(list,numOfComparisons);
		
		recInRange(root,list,pair,lowerLeft,upperRight);
	    
		return pair;
	}
	
	private void recInRange(TLNode<T> c, List<Pair<Location, List<T>>> list, Pair<List<Pair<Location, List<T>>>, Integer>  ogpair, Location lowerLeft, Location upperRight) {
	if (c == null) 
	return;
	else {
		
		ogpair.second++;
		
		if((lowerLeft.x <= c.location.x && c.location.x <= upperRight.x) && ( lowerLeft.y <= c.location.y && c.location.y <= upperRight.y)){
		Pair<Location, List<T>> pair= new Pair<Location, List<T>>(c.location, c.data);
        list.insert(pair);
		}
        
		if (((lowerLeft.x < c.location.x)&&(lowerLeft.y <= c.location.y)) || ((upperRight.x < c.location.x)&&(upperRight.y <= c.location.y)) 
				|| ((upperRight.x < c.location.x )&&(lowerLeft.y <= c.location.y)) || ((lowerLeft.x < c.location.x)&&(upperRight.y <= c.location.y)))
			recInRange(c.Child1,list,ogpair,lowerLeft,upperRight);
	       
	       if (((lowerLeft.x <= c.location.x)&&(lowerLeft.y > c.location.y)) || ((upperRight.x <= c.location.x)&&(upperRight.y > c.location.y))
	            || ((upperRight.x <= c.location.x)&&(lowerLeft.y > c.location.y)) || ((lowerLeft.x <= c.location.x)&&(upperRight.y > c.location.y)))
	    	   recInRange(c.Child2,list,ogpair,lowerLeft,upperRight);
	       
	       if (((lowerLeft.x > c.location.x)&&(lowerLeft.y >= c.location.y)) || ((upperRight.x > c.location.x)&&(upperRight.y >= c.location.y))
	            || ((upperRight.x > c.location.x)&&(lowerLeft.y >= c.location.y)) || ((lowerLeft.x > c.location.x)&&(upperRight.y >= c.location.y)))
	    	   recInRange(c.Child3,list,ogpair,lowerLeft,upperRight);
	       
	       if (((lowerLeft.x >= c.location.x)&&( lowerLeft.y < c.location.y)) || ((upperRight.x >= c.location.x)&&(upperRight.y < c.location.y))
	            || ((upperRight.x >= c.location.x)&&(lowerLeft.y < c.location.y)) || ((lowerLeft.x >= c.location.x)&&(upperRight.y < c.location.y)))
	    	   recInRange(c.Child4,list,ogpair,lowerLeft,upperRight);
        
		}
	
	}
	
	
}
