
class BSTNode<K extends Comparable<K>, T>{
		public K key;
        public T data;
		public BSTNode<K,T> left,right;
		
		public BSTNode(K key, T d) {
			this.key=key;
			data=d;
			left=right=null;
		}
		public BSTNode(K key, T d,  BSTNode<K,T> l,  BSTNode<K,T> r) {
			this.key=key;
			data=d;
			left=l;
			right=r;
		}
		
	}

public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	
	BSTNode<K,T> root, current,cc;
	
	public BST() {
		root=current=null;
	}

	// Returns true if the tree is empty. Must be O(1).
	
	@Override
	public boolean empty() {
		return root==null;
	}

	// Returns true if the tree is full. Must be O(1).

	@Override
	public boolean full() {
		return false;
	}

	// Returns the data of the current element

	@Override
	public T retrieve() {
		return current.data;
	}

	// Updates the data of current element.

	@Override
	public void update(T e) {
		current.data=e;

	}
	

	// Makes the element with key k the current element if it exists, and if k does
		// not exist, the current is unchanged. The first element of the returned pair
		// indicates whether k exists, and the second is the number of key comparisons
		// made.
	
	@Override
	public Pair<Boolean, Integer> find(K key) {
		boolean exists=false;
		int numOfComparisons=0;
		Pair<Boolean, Integer> pair;
		
		if(!empty()) {
		BSTNode<K,T> p=root;
		while(p!=null) {
			numOfComparisons++;
			cc=p;
			if(p.key.equals(key)) {
				current =p;
				exists=true;
				break;
			}
			else if(key.compareTo(p.key)<0)
				p=p.left;
			else
				p=p.right;
		 }
		}
		
		pair=new Pair<Boolean, Integer>(exists,numOfComparisons);
		return pair;
	}
	
	
	// Inserts a new element if it does not exist and makes it the current element.
		// If the k already exists, the current does not change. The first element of
		// the returned pair indicates whether k was inserted, and the second is the
		// number of key comparisons made.

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K,T> orgcurr=current;
		BSTNode<K,T> p;
		Pair<Boolean, Integer> findPair =find(key);
		
		int numOfComparisons= findPair.second;
		boolean inserted=false;
		Pair<Boolean, Integer> pair;
		
		if(findPair.first==false) {
			
		p=new BSTNode<K,T>(key,data);
		
		if(empty()) {
			root=current=p;
			inserted= true;
		}
		else {
			if(key.compareTo(cc.key)<0)
				cc.left=p;
			else
				cc.right=p;
			
			current=p;
			inserted= true;
		}
		
	}
		else
			current=orgcurr;
		
		pair= new Pair<Boolean, Integer>(inserted,numOfComparisons);
		
		return pair;
	}

	
	// Removes the element with key k if it exists. The position of current is
		// unspecified after calling this method.The first element of the returned pair
		// indicates whether k was removed, and the second is the number of key
		// comparisons made.
	
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		
	      Pair<Boolean, Integer> pair=find(key);
	      
	    if(pair.first==true) {
	    	 K k1=key;
	    	 BSTNode<K,T> p = root;
		     BSTNode<K,T> q = null; 
	    	
		     while (p!=null) {
		    	 if (k1.compareTo(p.key)<0) {
	                    q =p;
	                    p = p.left;
	                } 
	           else if (k1.compareTo(p.key)>0) {    
	                    q = p;
	                    p = p.right;
	                }
	           else {
		    		  if ((p.left != null) && (p.right != null)) { 
		    			 BSTNode<K,T> min = p.right;
		    			 q = p;
		 	    		 while (min.left != null) {
		 	    			q = min;
		 	    			 min = min.left; }
		 	    		 
		 	    		 p.key = min.key;
		 	    		 p.data = min.data;
		 	    		 k1 = min.key;
		 	    		 p = min;
		 	    		 }
		    		  
		    		  if (p.left != null)
		    		  {  p = p.left; } 
		    		  else 
		    		  {p = p.right; }
		    		  
		    		  if (q == null) {
		    			  root = p; }
		    		  else {
		    				  if (k1.compareTo(q.key)<0) 
		    					  q.left = p; 
		    				  else 
		    					  q.right = p; 
		    				  }
		    		  
		    		  current = root;
		    }       
	    }
	  }
	    
	      return pair;
	      }
	
	
	// Returns all keys of the map as a list sorted in increasing order.

	@Override
	public List<K> getAll() {
		List<K> list = new LinkedList<K>();
		recGetAll(root,list);
		return list;
	}
	
	private void recGetAll(BSTNode<K,T> c, List<K> list) {
	if (c == null) 
	return;
	else {
		recGetAll(c.left,list);
        list.insert(c.key);
		recGetAll(c.right,list);
	}
	}
	

}