import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MyDLL;
import main.MyDLLNode;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author Eric
 * @param <E>
 *
 */
class MyDLLTests<E> {
	
	MyDLLNode<E> nodeOne, nodeTwo, nodeThree, nodeFour, nodeFive;
	MyDLL<E> listOne, listTwo, listThree;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		nodeOne = new MyDLLNode<E>((E)"String 1");
		nodeTwo = new MyDLLNode<E>((E)"String 2");
		
		nodeThree = new MyDLLNode<E>((E)"String 3");
		nodeFive = new MyDLLNode<E>((E)"String 5");
		nodeFour = new MyDLLNode<E>((E)"String 4", nodeThree, nodeFive);
		
		listOne = new MyDLL<E>(nodeOne);
		listTwo = new MyDLL<E>(nodeThree);
		listThree = new MyDLL<E>(nodeThree, nodeFour);
	}
		
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// Reset nodes
		nodeOne.setElement(null);
		nodeOne.setNextNode(null);
		nodeOne.setPrevNode(null);
		nodeTwo.setElement(null);
		nodeTwo.setNextNode(null);
		nodeTwo.setPrevNode(null);
		nodeThree.setElement(null);
		nodeThree.setNextNode(null);
		nodeThree.setPrevNode(null);
		nodeFour.setElement(null);
		nodeFour.setNextNode(null);
		nodeFour.setPrevNode(null);
		nodeFive.setElement(null);
		nodeFive.setNextNode(null);
		nodeFive.setPrevNode(null);
		
		// Reset lists
		listOne.setHead(null);
		listOne.setTail(null);
		listOne.setCount(0);
		listTwo.setHead(null);
		listTwo.setTail(null);
		listTwo.setCount(0);
		listThree.setHead(null);
		listThree.setTail(null);
		listThree.setCount(0);
		
	}

	
	
	
	
	// Tests for MyDLLNode
	
	/**
	 * Test method for {@link main.MyDLLNode#MyDLLNode(java.lang.Object)}.
	 */
	@Test
	void testMyDLLNodeE() {
		assertEquals("String 1", nodeOne.getElement());
		assertNull(nodeOne.getNextNode());
		assertNull(nodeOne.getPrevNode());
	}

	/**
	 * Test method for {@link main.MyDLLNode#getNextNode()}.
	 */
	@Test
	void testGetSetNextNode() {
		nodeOne.setNextNode(nodeTwo);
		assertSame(nodeTwo, nodeOne.getNextNode());
	}
	
	/**
	 * Test method for {@link main.MyDLLNode#MyDLLNode(java.lang.Object, main.MyDLLNode, main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLNodeEMyDLLNodeOfEMyDLLNodeOfE() {
		assertEquals("String 4", nodeFour.getElement());
		assertSame(nodeThree, nodeFour.getPrevNode());
		assertSame(nodeFive, nodeFour.getNextNode());
		assertSame(nodeFour, nodeThree.getNextNode());
		assertSame(nodeFour, nodeFive.getPrevNode());
	}

	/**
	 * Test method for {@link main.MyDLLNode#getPrevNode()}.
	 */
	@Test
	void testGetSetPrevNode() {
		nodeTwo.setPrevNode(nodeOne);
		assertSame(nodeOne, nodeTwo.getPrevNode());
	}

	/**
	 * Test method for {@link main.MyDLLNode#getElement()}.
	 */
	@Test
	void testGetElement() {
		assertEquals("String 1", nodeOne.getElement());
	}

	/**
	 * Test method for {@link main.MyDLLNode#setElement(java.lang.Object)}.
	 */
	@Test
	void testSetElement() {
		nodeOne.setElement((E)"String 1 changed");
		assertEquals("String 1 changed", nodeOne.getElement());
	}
	
	
	
	
	
	
	// Tests for MyDLL
	
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfEOne() {
		assertSame(listOne.getHead(), nodeOne);
		assertSame(listOne.getTail(), nodeOne);
		assertEquals(listOne.getCount(), 1);
	}

	// Tests for MyDLL
	
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfEMulti() {
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getHead(), nodeFive);
		assertEquals(listTwo.getCount(), 3);
	}
		
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode, main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfEMyDLLNodeOfE() {
		assertSame(listThree.getHead(), nodeThree);
		assertSame(listThree.getTail(), nodeFour);
		assertEquals(listThree.getCount(), 2);
	}

	/**
	 * Test method for {@link main.MyDLL#getHead()}.
	 */
	@Test
	void testGetSetHead() {
		assertSame(listOne.getHead(), nodeOne);
		listOne.setHead(nodeTwo);
		assertSame(listOne.getHead(), nodeTwo);
	}

	/**
	 * Test method for {@link main.MyDLL#getTail()}.
	 */
	@Test
	void testGetSetTail() {
		assertSame(listOne.getTail(), nodeOne);
		listOne.setTail(nodeTwo);
		assertSame(listOne.getTail(), nodeTwo);
	}

	/**
	 * Test method for {@link main.MyDLL#getCount()}.
	 */
	@Test
	void testGetSetCount() {
		assertEquals(listOne.getCount(), 1);
		assertEquals(listTwo.getCount(), 3);
		assertEquals(listThree.getCount(), 2);
		listThree.setCount(4);
		assertEquals(listThree.getCount(), 4);
	}

	/**
	 * Test method for {@link main.MyDLL#hasNext()}.
	 */
	@Test
	void testHasNext() {
		assertFalse(listOne.hasNext());
		assertTrue(listThree.hasNext());
	}

	/**
	 * Test method for {@link main.MyDLL#next()}.
	 */
	@Test
	void testNextFail() {
		try {
			listOne.next();
			fail("Failed this test");
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link main.MyDLL#next()}.
	 */
	@Test
	void testNextSuccess() {
		assertSame(nodeFour, listTwo.next());
	}

	/**
	 * Test method for {@link main.MyDLL#size()}.
	 */
	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#clear()}.
	 */
	@Test
	void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#addAll(utilities.ListADT)}.
	 */
	@Test
	void testAddAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#get(int)}.
	 */
	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#toArray()}.
	 */
	@Test
	void testToArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyDLL#iterator()}.
	 */
	@Test
	void testIterator() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}