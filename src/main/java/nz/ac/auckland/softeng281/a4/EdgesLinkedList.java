package nz.ac.auckland.softeng281.a4;

/**
 * The Linked List Class Has only one head pointer to the start node. Nodes are
 * indexed starting from 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
    // the head of the linked list
    private Edge head;

    public EdgesLinkedList() {
        head = null;
        
    }


    /**     
     * This method adds an edge as the start edge of the list     
     *      
     *  @param edge to prepend     
     */
    public void prepend(Edge e) {
        Edge edge = new Edge(e.getSource(),e.getTarget(),e.getWeight());
        edge.setNext(head);
        head = edge;
        
    }

    /** This method adds an edge as the end edge of the list     
     *      
     *  @param edge to append     
     */

    public void append(Edge edge) {
        Edge e = new Edge(edge.getSource(),edge.getTarget(),edge.getWeight());
        Edge pointer = head;
        
        // checks if list is empty (ie: head is null)
        if(pointer == null) {
            head = e;
        }else {
	        // Locates the last edge
	        while(pointer.getNext()!=null) {
	        	pointer = pointer.getNext();
	        	
	        }
	        
	        // rearrange pointers
	        e.setNext(null); // last edge should be null
	        pointer.setNext(e);
        }
    }

    /**     
     * * This method gets the edge at a given position        
     * 
     *  @param pos: an integer, which is the position     
     *  @return the Edge at the position pos     
     */

    public Edge get(int pos) throws InvalidPositionException {
        
    	// Checks if position is outside of list boundary
    	if (pos < 0 || (pos > size() && size() >0)) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
            
        }else {
        	int i=0;
    		Edge edgeAtPos = head;
    		
    		// goes through list until position is found and gets the edge at that position
    		while(i!=pos) {
    			++i;
    			edgeAtPos = edgeAtPos.getNext();
    		}
    		
    		return edgeAtPos;
        }
        
    }

    /**     
     * This method adds an edge at a given position in the List     
     *  
     *  @param pos:  an integer, which is the position     
     *  @param edge: the edge to add     
     *  @throws InvalidPositionException     
     */
    public void insert(int pos, Edge edge) throws InvalidPositionException {
        // Checks if the position is invalid: negative position, position greater than the size of list -1 or position is 0
    	if (pos < 0 || pos > size() - 1 || pos == 0) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }else {
        	
        	Edge current = head;
        	Edge previous = null;
        	int counter = 0;
        	
        	// Looks for the corresponding element that is occupying that position currently and all the next 
	        while(current != null && counter < pos) {
	        	previous = current;
	        	current = current.getNext();
	        	counter++;
	        }
	        
	        // rearrange the Positions of existing edges and insert the new edge in
	        Edge newEdge = new Edge(edge.getSource(),edge.getTarget(),edge.getWeight());
	        newEdge.setNext(previous.getNext());
	        previous.setNext(newEdge);
        	
        }
   }
        

    /**
     * This method removes a node at a given position
     *
     * @param pos: an integer, which is the position
     * @return void
     */

    public void remove(int pos) throws InvalidPositionException {
       // Checks if position is invalid: negative position or position greater than size of list -1
    	if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
            
        }else {
        	// Removing the head of the list
	        if(pos == 0) {
	        	head = head.getNext();
	        	
	        // Removing an element in the list at any other point
	        }else {
		        Edge current = head;
		        
		        // goes through the list and stores edges that are after the deleted edge
		        for(int i=0;i < pos-1;i++) {
		        	current = current.getNext();
		        }
		        
		        // Sets the element of the deleted edge to the edges we found above
		        current.setNext(current.getNext().getNext());
	        }
        }
        
    }

    /**
     * This method returns the size of a list
     *
     * @param
     * @return the size of the list
     */

    public int size() {
        int size=0;
        Edge edge = head;
       
        // checks if the list is empty
        if(edge == null) {
        	size = 0;
        }else {
	    	size = 1;
	    	
	    	// Goes through the edges in the list and increases the size until the next element in list is null
	        for(Edge currentEdge = head; currentEdge.getNext() != null; currentEdge = currentEdge.getNext()) {
	        	size++;
	        }
        }
        
        return size;
    }

    /**
     * This method is used for printing the data in the list from head till the last
     * node
     *
     * @param
     * @return void
     */

    public void print() {
        Edge edge = head;
        while (edge != null) {
            System.out.println(edge);
            edge = edge.getNext();
        }
    }
}
