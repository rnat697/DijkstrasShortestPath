package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EdgesLinkedListTest {

    EdgesLinkedList list;
    EdgesLinkedList expected;

    @Before
    public void setUp() {
        list = new EdgesLinkedList();
        expected = new EdgesLinkedList();
    }

    @Test
    public void testPrependEmptyList() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }
    
    @Test
    public void testPrepend_List1ExistingEdge() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(0));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
    }
    
    @Test
    public void testPrepend_List2ExistingEdge() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.prepend(new Edge(new Node("3"), new Node("5"), 6));
        
        assertEquals(new Edge(new Node("3"), new Node("5"), 6), list.get(0));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(2));
    }
    
    @Test
    public void testGet_InvalidPosition() {
    	 list.prepend(new Edge(new Node("1"), new Node("2"), 1));
         list.prepend(new Edge(new Node("2"), new Node("3"), 2));
         try {
        	 list.get(0);
        	 list.get(1);
        	 list.get(2);
         }catch(InvalidPositionException e) {
        	 // test passes if exception is thrown
         }
    }
    
    @Test
    public void testGet_Position2() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.prepend(new Edge(new Node("3"), new Node("5"), 6));
        
         try {
        	 assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(2));
         }catch(InvalidPositionException e) {
        	 fail();
         }
    }
    
    @Test
    public void testGet_InvalidPosition_NegativePos() {
    	 list.prepend(new Edge(new Node("1"), new Node("2"), 1));
         list.prepend(new Edge(new Node("2"), new Node("3"), 2));
         try {
        	 list.get(-1);
         }catch(InvalidPositionException e) {
        	 // test passes if exception is thrown
         }
    }
    
    @Test
    public void testSize_SizeOf3() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.prepend(new Edge(new Node("3"), new Node("5"), 6));
        
        assertEquals(3, list.size());
    }
    
    @Test
    public void testSize_SizeOf0() {
        assertEquals(0, list.size());
    }
    @Test
    public void testSize_SizeOf1() {
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	assertEquals(1, list.size());
    }
    
    @Test
    public void testAppend() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.append(new Edge(new Node("3"), new Node("4"), 3));
        
        
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(2));
    }
    
    @Test
    public void testAppend_AllAppended() {
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
        list.append(new Edge(new Node("4"), new Node("5"), 2));
        list.append(new Edge(new Node("5"), new Node("6"), 3));
        
        
        assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(0));
        assertEquals(new Edge(new Node("4"), new Node("5"), 2), list.get(1));
        assertEquals(new Edge(new Node("5"), new Node("6"), 3), list.get(2));
    }
    
    @Test
    public void testInsert() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.insert(1,new Edge(new Node("3"), new Node("4"), 3));
        
        
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(1));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(2));
    }
    
    @Test
    public void testInsert_4Edges_InsertAtPos2() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
        list.append(new Edge(new Node("2"), new Node("3"), 2));
        list.append(new Edge(new Node("4"), new Node("5"), 4));
        try {
        	
            list.insert(1,new Edge(new Node("3"), new Node("4"), 3));
            assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
            assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(1));
            assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(2));
            assertEquals(new Edge(new Node("4"), new Node("5"), 4), list.get(3));
            
            
       }catch(InvalidPositionException e){
           fail();
       }
    }
    @Test
    public void testInsert_InvalidPosition_PositionGreaterThanSize() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        try {
        	
        list.insert(2,new Edge(new Node("3"), new Node("4"), 3));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        
        }catch(InvalidPositionException e){
        	// test passes when exception is thrown
        }
    }
    @Test
    public void testInsert_InvalidPosition_NegativePosition() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        
        try {
        
        list.insert(-1,new Edge(new Node("3"), new Node("4"), 3));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        
        }catch(InvalidPositionException e) {
        	// test passes when exception is thrown
        }
    }
    
    @Test
    public void testInsert_InvalidPosition_FirstPosition() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        
        try {
        
        list.insert(0,new Edge(new Node("3"), new Node("4"), 3));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        
        }catch(InvalidPositionException e) {
        	// test passes when exception is thrown
        }
    }
    
    @Test
    public void testRemovePosition1() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 2));
    	list.append(new Edge(new Node("3"), new Node("4"), 3));
    	try {
    		list.remove(1);
    		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    		assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(1));
    	}catch(InvalidPositionException e) {
    		fail();
    	}
    }
    
    @Test 
    public void testRemove_1Edge_RemovingHead() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	try {
    		list.remove(0);
    		assertEquals(null, list.get(0));
    	}catch(InvalidPositionException e) {
    		fail();
    	}
    }
    
    @Test 
    public void testRemove_4Edge_RemovingHead() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 2));
    	list.append(new Edge(new Node("3"), new Node("4"), 3));
    	list.append(new Edge(new Node("4"), new Node("5"), 4));
    	
    	try {
    		list.remove(0);
    		assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(0));
    		assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(1));
    		assertEquals(new Edge(new Node("4"), new Node("5"), 4), list.get(2));
    	}catch(InvalidPositionException e) {
    		fail();
    	}
    }
    
    @Test 
    public void testRemove_Position1_4Edges() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 2));
    	list.append(new Edge(new Node("3"), new Node("4"), 3));
    	list.append(new Edge(new Node("4"), new Node("5"), 4));

    	try {
    		list.remove(1);
    		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    		assertEquals(new Edge(new Node("3"), new Node("4"), 3), list.get(1));
    		assertEquals(new Edge(new Node("4"), new Node("5"), 4), list.get(2));
    	}catch(InvalidPositionException e) {
    		fail();
    	}
    }
    
    @Test 
    public void testRemove_InvalidPosition_NegativePosition() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        
        try {
        
        list.remove(-1);
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        
        }catch(InvalidPositionException e) {
        	// test passes when exception is thrown
        }
    }
    
    @Test 
    public void testRemove_InvalidPosition_PositionOutsideBoundary() {
    	list.prepend(new Edge(new Node("2"), new Node("3"), 2));
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        
        try {
        
        list.remove(2);
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        
        }catch(InvalidPositionException e) {
        	// test passes when exception is thrown
        }
    }
 
}
