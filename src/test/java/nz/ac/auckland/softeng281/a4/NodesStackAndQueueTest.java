package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class NodesStackAndQueueTest {

    NodesStackAndQueue stack;
    NodesStackAndQueue expected;
    Node expectedNode;

    @Before
    public void setUp() {
        stack = new NodesStackAndQueue();
        expected = new NodesStackAndQueue();
        
    }

    @Test
    public void isEmptyEmptyStack() {
        try{
        	stack.isEmpty();
        } catch(EmptyStackException e) {
        	
        }
    }

    @Test
    public void isEmptyNotEmpty() {
        try{
	        stack.append(new Node("4"));
	        assertFalse(stack.isEmpty());
        } catch(EmptyStackException e) {
        	
        }
    }
    
    @Test
    public void isEmptyNotEmpty_2NodesInStack() {
        try {	
	    	stack.append(new Node("1"));
	        stack.append(new Node("2"));
	        assertFalse(stack.isEmpty());
        }catch(EmptyStackException e) {
        	
        }
    }
    
    @Test
    public void isEmptyNotEmpty_4NodesInStack() {
	       try{
	        stack.append(new Node("1"));
	        stack.append(new Node("2"));
	        stack.append(new Node("3"));
	        stack.append(new Node("4"));
	        assertFalse(stack.isEmpty());
        }catch(EmptyStackException e) {
        	
        }
    }
    
    @Test
  public void appendTest_4Nodes() {
      stack.append(new Node("1"));
      stack.append(new Node("2"));
      stack.append(new Node("3"));
      stack.append(new Node("4"));
      
      expected.append(new Node("1"));
      expected.append(new Node("2"));
      expected.append(new Node("3"));
      expected.append(new Node("4"));
      
      assertEquals(expected.nodeList,stack.nodeList);
     
  }
    public void appendTest_2Nodes() {
        stack.append(new Node("1"));
        stack.append(new Node("2"));
        
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        
        assertEquals(expected.nodeList,stack.nodeList);
       
    }
    public void appendTest_6Nodes() {
        stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        stack.append(new Node("4"));
        stack.append(new Node("5"));
        stack.append(new Node("6"));
        
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        expected.append(new Node("4"));
        expected.append(new Node("5"));
        expected.append(new Node("6"));
        
        assertEquals(expected.nodeList,stack.nodeList);
       
    }
    
    @Test
    public void pushTest_3Nodes_Node4PushedTop() {
    	stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        stack.push(new Node("4"));
        
        expected.append(new Node("4"));
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        
        assertEquals(expected.nodeList,stack.nodeList);
    }
    
    @Test
    public void pushTest_2Nodes_Node5PushedTop() {
    	stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.push(new Node("5"));
        
        expected.append(new Node("5"));
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        
        assertEquals(expected.nodeList,stack.nodeList);
    }
    
    @Test
    public void pushTest_5Nodes_Node8PushedTop() {
    	stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        stack.append(new Node("4"));
        stack.append(new Node("5"));
        stack.push(new Node("8"));
        
        expected.append(new Node("8"));
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        expected.append(new Node("4"));
        expected.append(new Node("5"));
        
        assertEquals(expected.nodeList,stack.nodeList);
    }
    
    @Test
    public void peekTest_4Nodes_Node1IsPeeked() {
    	stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        stack.append(new Node("4"));
        
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        expected.append(new Node("4"));
        
        expectedNode = new Node("1");
        assertEquals(expectedNode, stack.peek());
        assertEquals(expected.nodeList, stack.nodeList);
    }
    
    @Test
    public void peekTest_4Nodes_Node4IsPeeked() {
    	stack.append(new Node("4"));
        stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        
        expected.append(new Node("4"));
        expected.append(new Node("1"));
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        
        expectedNode = new Node("4");
        assertEquals(expectedNode, stack.peek());
        assertEquals(expected.nodeList, stack.nodeList);
    }
    
    @Test
    public void peekTest_7Nodes_Node5IsPeeked() {
    	stack.append(new Node("5"));
        stack.append(new Node("7"));
        stack.append(new Node("4"));
        stack.append(new Node("3"));
        stack.append(new Node("1"));
        stack.append(new Node("6"));
        stack.append(new Node("2"));
        
        expected.append(new Node("5"));
        expected.append(new Node("7"));
        expected.append(new Node("4"));
        expected.append(new Node("3"));
        expected.append(new Node("1"));
        expected.append(new Node("6"));
        expected.append(new Node("2"));
        
        expectedNode = new Node("5");
        assertEquals(expectedNode, stack.peek());
        assertEquals(expected.nodeList, stack.nodeList);
    }
    
    @Test
    public void popTest_4Nodes_Node1IsPopped() {
    	stack.append(new Node("1"));
        stack.append(new Node("2"));
        stack.append(new Node("3"));
        stack.append(new Node("4"));
        
        expected.append(new Node("2"));
        expected.append(new Node("3"));
        expected.append(new Node("4"));
        
        expectedNode = new Node("1");
        assertEquals(expectedNode, stack.pop());
        assertEquals(expected.nodeList, stack.nodeList);
    }
    
    @Test
    public void popTest_5Nodes_Node3IsPopped() {
    	stack.append(new Node("3"));
        stack.append(new Node("5"));
        stack.append(new Node("2"));
        stack.append(new Node("1"));
        stack.append(new Node("4"));
        
        expected.append(new Node("5"));
        expected.append(new Node("2"));
        expected.append(new Node("1"));
        expected.append(new Node("4"));
        
        
        
        expectedNode = new Node("3");
        assertEquals(expectedNode, stack.pop());
        assertEquals(expected.nodeList, stack.nodeList);
    }
    
    @Test
    public void popTest_7Nodes_Node4IsPopped() {
    	stack.append(new Node("4"));
        stack.append(new Node("6"));
        stack.append(new Node("7"));
        stack.append(new Node("1"));
        stack.append(new Node("3"));
        stack.append(new Node("2"));
        stack.append(new Node("5"));
        
        expected.append(new Node("6"));
        expected.append(new Node("7"));
        expected.append(new Node("1"));
        expected.append(new Node("3"));
        expected.append(new Node("2"));
        expected.append(new Node("5"));
        
        
        expectedNode = new Node("4");
        assertEquals(expectedNode, stack.pop());
        assertEquals(expected.nodeList, stack.nodeList);
    }
 
}