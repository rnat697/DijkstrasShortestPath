package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import com.sun.security.jgss.ExtendedGSSContext;

/**
 * You cannot add new fields.
 */
public class Graph {

    /**
     * Each node maps to a list of all the outgoing edges from that node
     */
    private HashMap<Node, EdgesLinkedList> adjacencyMap;
    /**
     * root of the graph, to know where to start the DFS or BFS
     */
    private Node root;

    /**
     * !!!!!! You cannot change this method !!!!!!!
     */
    public Graph(List<String> edges, List<String> weights) {
        if (edges.isEmpty() || weights.isEmpty()) {
            throw new IllegalArgumentException("edges and weights are empty");
        }
        adjacencyMap = new HashMap<>();
        int i = 0;
        for (String edge : edges) {
            String[] split = edge.split(",");
            Node source = new Node(split[0]);
            Node target = new Node(split[1]);
            Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
            if (!adjacencyMap.containsKey(source)) {
                adjacencyMap.put(source, new EdgesLinkedList());
            }
            adjacencyMap.get(source).append(edgeObject);
            if (i == 0) {
                root = source;
            }
            i++;
        }
    }


    /**
     * find a particular node, note that a node might not have outgoing edges but only ingoing edges
     * so you need to check also the target nodes of the edges
     *
     * @param node
     * @return true if adjacencyMap contains the node, false otherwise.
     */
    public boolean isNodeInGraph(Node node) {
    	
        EdgesLinkedList edge = adjacencyMap.get(node);
        Node targetNode = null;
        int i = 0;
        
        // If the node exists in the ajacencyMap
        if(edge!= null) {

        	// goes through the edges of the node and gets the target node
	        while(i <= edge.size() && edge.get(i)!=null) { 
	    	   targetNode = edge.get(i).getTarget();
	    	   
	    	   // Checks if the target node is not in the hashmap of nodes
	    	   if(!adjacencyMap.containsKey(targetNode)) {
	    		   adjacencyMap.put(targetNode, new EdgesLinkedList()); // adds the missing node into the hashmap
	    	   }
	    	   
	    	   i++;
	        }
        }
    	
    	return adjacencyMap.containsKey(node); // returns true if the hashmap contains the node, false otherwise
    }


    /**
     * This method finds an edge with a specific weight, if there are more
     * than one you need to return the first you encounter.
     * You must use Breath First Search (BFS) strategy starting from the root.
     * <p>
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * <p>
     * In addition to the data structure visited you can only create new
     * data structures of type EdgesLinkedList and NodesStackAndQue
     *
     * @param weight
     * @return the Edge with the specific weight, null if no edge with the specific weight exists in the graph
     */
    public Edge searchEdgeByWeight(int weight) {
    	Set<Node> visited = new HashSet<>();
        NodesStackAndQueue nodesQueue = new NodesStackAndQueue();
        EdgesLinkedList edges = new EdgesLinkedList();
        Edge edgeFound = new Edge(null,null,0);
        Node targetNode;
        Node visitNode;
        int currentWeight;
        int i = 0;
        
        nodesQueue.append(root); // adds the source node to the queue
 
        while(nodesQueue.nodeList.size() > 0) { // makes sure that the queue iterates as long as its not empty
        	visitNode = nodesQueue.pop(); 
        	
        	// Checks if the visitNode hasn't been visited before (from the visited list)
        	if(!visited.contains(visitNode)) {
        		visited.add(visitNode);
        		edges = adjacencyMap.get(visitNode); // gets the edges for current node (visiting node)
        		i = 0;
        		
        		// Loops through the edges of the visiting node & gets the target node
        		// and makes sure that we only get the first occurrence of parameter weight.
        		while(i <= edges.size() && edges.get(i)!=null && edgeFound.getWeight() != weight) {
        			targetNode = edges.get(i).getTarget();
        			currentWeight = edges.get(i).getWeight();
        			
        			// Checks if the current weight of the visiting node is the same 
        			// as the weight passed into the parameter of this method.
        			if(currentWeight == weight) {
        				edgeFound = edges.get(i);
        				break;
        			}
        			
        			// Checks if the target node hasn't been visited before
        			if(!visited.contains(targetNode)) {
        				nodesQueue.append(targetNode); 
        			}
        			i++;
        			
        		}
        	}
        }
        
        return edgeFound;
    }


    /**
     * Returns the weight of the Edge with Node source and Node target if the
     * given Edge is inside the graph.
     * If there is no edge with the specified source and target, the method returns -1
     * You must use Depth First Search (DFS) strategy starting from the root.
     * <p>
     * RULES
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * In addition to the data structure visited you can only create new data structures of type
     * <p>
     * NodesStackAndQueue and EdgesLinkedList
     *
     * @param source
     * @param target
     * @return the weight of the first encountered edge with source and target,
     * -1 if no edge with the given source and target exists
     */
    public int searchWeightByEdge(Node source, Node target) {
    	Set<Node> visited = new HashSet<>();
        NodesStackAndQueue nodesStack = new NodesStackAndQueue();
        EdgesLinkedList edges = new EdgesLinkedList();
        int weightFound = -1;
        Node targetNode;
        Node visiting;
        int i = 0;
        
        nodesStack.append(root); // appends the source node to the stack
        
        while(!nodesStack.nodeList.isEmpty()) { // make sure that it iterates as long as the stack is not empty
	        visiting = nodesStack.pop();

	        // checks if the visiting node has been visited before
	        if(!visited.contains(visiting)) {
	        	visited.add(visiting); // adds visiting node to the visited list
	        	edges = adjacencyMap.get(visiting); // gets the edges of the visiting node
	        	i = 0;
	    
	        	// Goes through each edge of the visiting node (source node) and gets the target node value
	        	while(i <= edges.size() && edges.get(i)!= null) {
	        		targetNode =  edges.get(i).getTarget(); // gets the target node value
	        		
	        		// Checks if the current edge have the corresponding source and target node from parameters
	        		if(edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
	        			weightFound = edges.get(i).getWeight(); // gets weight of corresponding edge
	        			break;
	        		}
	        		
	        		// appends the target node to the stack if it hasn't been visited yet
	        		if(!visited.contains(targetNode)) {
	        			nodesStack.append(targetNode);
	        			
	        		}
	        		i++;
	        	}
	        }
        }
        return weightFound;
    }


    /**
     * Given a source Node and a target Node it returns the shortest path
     * between source and target
     * <p>
     * A Path is represented as an ordered sequence of nodes, together with the
     * total weight of the path. (see Path.java class)
     *
     * @param source
     * @param target
     * @return the shortest path between source and target
     */
    public Path computeShortestPath(Node source, Node target) {
    	// Checks if all the nodes are in the adjacencyMap before initialisation
	    for(Node nodeInGraph : adjacencyMap.keySet()) {
	    	isNodeInGraph(nodeInGraph);
	    }
	    
    	Node prev[] = new Node[adjacencyMap.size()];
	    Integer dist[] = new Integer[adjacencyMap.size()];
	    Edge nextLeadNode;
	    NodesStackAndQueue queue = new NodesStackAndQueue();
    	Path path; 
	    List<Node> pathOfNodes = new ArrayList<>();
	    EdgesLinkedList edgeOfLead = new EdgesLinkedList();
	    Node leadVertex = source;
	    Node nextLead = source;

	    int indexNext = 0;
	    int i = 0;
	    int indexLead = Integer.parseInt(source.getValue())-1;
	    int targetIndex = Integer.parseInt(target.getValue())-1;
	    int newDist;
	    
	    // --------------Initialisation--------
	    // - Sets all distances to be infinite
	    // - Sets all previous nodes to be null
	    // - Adds all nodes in adjacencyMap into queue
	    for(Node nodes : adjacencyMap.keySet()) {
	    	dist[i] = Integer.MAX_VALUE;
	    	prev[i] = null;
	    	queue.append(nodes);
	    	i++;
	    	
	    }
	    // checks if source node is 0, hence index would be -1 
	    if(indexLead == -1) {
	    	indexLead = adjacencyMap.size()-1; // node 0 is at the back of list, adjacencyMap
	    }
	    
	    dist[indexLead] = 0; // Source node always has a distance of 0
	   
	    //-------- Iteration ------------
	    while(!queue.isEmpty()) {
	    	
	    	//--------------- Finds minimum distance----------------
	    	// Finds the target node with the shortest distance and its corresponding position number
    		nextLeadNode = getMinimumDistance(adjacencyMap.get(leadVertex),dist,indexLead,leadVertex,target,queue);
    		nextLead = nextLeadNode.getTarget();
    		indexNext = nextLeadNode.getWeight();
    		queue.remove(leadVertex); // remove current leadVertex from queue
    		
    		// -----------Performs relaxation-------------
    		// Goes through all the edges in the node leadVertex
    		for(int k = 0;  k<= adjacencyMap.get(leadVertex).size()-1; k++ ) {
    			edgeOfLead = adjacencyMap.get(leadVertex);
    			
    			// checks that the edge is not null
    			if(edgeOfLead.get(k)!= null) {
    				
    				//checks if the target node value is 0
    				if(Integer.parseInt(edgeOfLead.get(k).getTarget().getValue())-1 == -1) {
    					targetIndex = adjacencyMap.size()-1; // for node 0 which is at the back of the adjacencyMap list
    					
    				}else {
    					targetIndex = Integer.parseInt(edgeOfLead.get(k).getTarget().getValue())-1;
    				}
	    			
    				
    				newDist = dist[indexLead] + edgeOfLead.get(k).getWeight();
	    			
    				// Compares the new distance with the current distance in the array to see if the new one is 
    				// smaller than the other
	    			if(newDist < dist[targetIndex]) {
	    				dist[targetIndex] = newDist;
	    				prev[targetIndex] = leadVertex;
	    				
	    			}
	    		}
    		}
    		// Updates the leadVertex and it's corresponding position in the array for the next iteration
    		leadVertex = nextLead;
    		indexLead = indexNext;
    		
    		// When target is found, create the path of nodes that it took
    		if(leadVertex.equals(target)) {
    			pathOfNodes = createPath(target, prev);
    			break;
    		}
		    
	    }
	    path = new Path(dist[Integer.parseInt(target.getValue())-1],pathOfNodes);
	    return path;
    }
    
    /**Helper method for shortestPath:
     * Creates a path from the source to a specified target using the array that stores the previous nodes
     *
     *@param target
     *@param prev
     *@return list of nodes of the path taken
     */
    private List<Node> createPath(Node target, Node[] prev){
    	List<Node> path = new ArrayList<Node>();
    	Node node = target; // starts with the target node instead of source
    	int index;
    	
    	index = Integer.parseInt(target.getValue())-1;
    	
    	// Goes through the array prev and gets the node at a specified position
    	// and adds it to the path list (starting with the target node and ending with the source node)
    	while(node != null) {
    		path.add(node);
    		node = prev[index]; // gets the node from the specified position
    		
    		// checks if node is not null so we can get the next index position for the next iteration
    		if(node!= null) {
    			
    			// When the node value is 0, change position to the end of the prev array
    			if(Integer.parseInt(node.getValue())-1==-1) { 
        			index = prev.length-1;
        			
        		}else {
        			index = Integer.parseInt(node.getValue())-1;
        		}
    		}
    	}
    	
    	Collections.reverse(path); // reverses the list so it starts with source node and ends with target node
        return path;
    	
    }

    /**Helper function for shortestDistance: 
     * Finds the target node with the smallest weight (minimum distance from leadVertex) 
     * from a linked list of edges & finds the index of the target node found 
     * and returns an edge that has the format (lead Node, next lead node, index of next lead node).
     *
     * @param edges 
     * @param dist
     * @param indexLead
     * @param leadVertex
     * @param target
     * @param queue
     * @return an edge that has the target node with the smallest distance from lead vertex and has the index
     * 		   of that target node.
     */
	private Edge getMinimumDistance(EdgesLinkedList edges, Integer[] dist, int indexLead,Node leadVertex, Node target, NodesStackAndQueue queue){
		Edge nextLeadNode = new Edge (null,null,0);
		int j = 0;
		Set<Integer> prevIndex = new HashSet<>();
	    int index;
	    int currentWeight;
	    int smallWeight = Integer.MAX_VALUE;
		int indexNext = 0;
		Node nextLead = new Node(null);
		Node targetNode;
		
		// goes through each edge in the edgesLinkedList in the leadVertex node
		while(j <= edges.size()&& edges.get(j)!= null){
			
			targetNode = edges.get(j).getTarget();
			currentWeight = edges.get(j).getWeight(); 
			index = Integer.parseInt(targetNode.getValue())-1;
			
			// if the node is 0, the index will be at the end of the dist array & prev array (in shortestPath method)
			if(index == -1) {
				index =adjacencyMap.size()-1;
			}
			
			// Compares weight for the smallest distance/weight and:
			// - Self loops should be ignored
			// - Index of the target node has not been compared
			// - Target node is still in the queue
			// Or the target node of the current iteration is the same as the target node specified in shortestPath
			if((currentWeight < smallWeight && !targetNode.equals(leadVertex)&& !prevIndex.contains(index)
					&& queue.nodeList.contains(targetNode))|| targetNode.equals(target)) {
				
				smallWeight = currentWeight;
				indexNext = index;
				nextLead = targetNode;
				
			}
			
			prevIndex.add(index); // keeps track of positions that has been compared
			j++;
		}
		// creates an edge to store the nextLead and indexNext (and lead vertex)
		nextLeadNode = new Edge(leadVertex, nextLead, indexNext ); 
		return nextLeadNode;
	}
}
