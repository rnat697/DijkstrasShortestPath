package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.List;

public class NodesStackAndQueue {
	protected List<Node> nodeList;
	private Node topElement;

    public NodesStackAndQueue() {
        nodeList =  new ArrayList<>();
        
    
    }

    public boolean isEmpty() {
    	boolean isListEmpty = false;
    	
    	// checks if list is empty
        if (nodeList.size() == 0) {
        	isListEmpty = true;
        	throw new EmptyStackException();
        }
        return isListEmpty;
    }

    /**
     * Push operation refers to inserting an element on the Top of the stack.
     *
     * @param node
     */
    public void push(Node node) {
        nodeList.add(0, node);
        
    }

    /**
     * pop an element from the top of the stack (removes and returns the top element)
     *
     * @return
     */
    public Node pop() {
        topElement = nodeList.get(0); // saves the top element before removing it
        nodeList.remove(0);
        return topElement;
    }

    /**
     * get the element from the top of the stack without removing it
     *
     * @return
     */
    public Node peek() {
    	return nodeList.get(0);
    	
    }

    /**
     * append an element at the end of the stack
     *
     * @param node
     */
    public void append(Node node) {
    	nodeList.add(node);
    	
    }
    /**Helper function for shortestPath:
     * Finds the node in the queue, gets its position in the queue and removes the node form the queue.
     * @param node
     */
    public void remove(Node node) {
    	int pos = 0;
    	
    	if(nodeList.indexOf(node) != -1) {
	    	pos = nodeList.indexOf(node); // gets position of node in queue
	    	nodeList.remove(pos);
    	}else {
    		throw new InvalidPositionException();
    	}
    }
   
}
