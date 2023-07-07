import static org.junit.jupiter.api.Assertions.*;

import datastructures.MyDLL;
import datastructures.MyDLLNode;
import interfaces.ListADT;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Iterator;

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
	
	MyDLLNode<E> nodeOne, nodeTwo, nodeThree, nodeFour, nodeFive, nodeSix;
	MyDLL<E> listOne, listTwo, listThree, listFour;
	E element;
	Object[] arrayOne, arrayTwo;
	Iterator<E> it;
	
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
		
		nodeSix = new MyDLLNode<E>();
		
		listOne = new MyDLL<E>(nodeOne);
		listTwo = new MyDLL<E>(nodeThree);
		listThree = new MyDLL<E>(nodeThree, nodeFour);
		listFour = new MyDLL<E>();
		
		element = (E)"Info to add/change";
		
		arrayOne = (E[])new Object[3];
		arrayTwo = (E[])new Object[1];
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
		listOne.clear();
		listTwo.clear();
		listThree.clear();
		listFour.clear();
		
		element = null;
		
		arrayOne = null;
		arrayTwo = null;
		
		it = null;
	}

	
	
	
	
	// Tests for MyDLLNode
	
	/**
	 * Test method for {@link MyDLLNode#MyDLLNode(java.lang.Object)}.
	 */
	@Test
	void testMyDLLNode() {
		assertNull(nodeSix.getElement());
		assertNull(nodeSix.getNextNode());
		assertNull(nodeSix.getPrevNode());
	}
	
	/**
	 * Test method for {@link MyDLLNode#MyDLLNode(java.lang.Object)}.
	 */
	@Test
	void testMyDLLNodeE() {
		assertEquals("String 1", nodeOne.getElement());
		assertNull(nodeOne.getNextNode());
		assertNull(nodeOne.getPrevNode());
	}
	
	/**
	 * Test method for {@link MyDLLNode#MyDLLNode(java.lang.Object, MyDLLNode, MyDLLNode)}.
	 */
	@Test
	void testMyDLLNodeEMyDLLNodeOfEMyDLLNodeOfE() {
		assertEquals("String 4", nodeFour.getElement());
		assertEquals(nodeThree, nodeFour.getPrevNode());
		assertEquals(nodeFive, nodeFour.getNextNode());
		assertEquals(nodeFour, nodeThree.getNextNode());
		assertEquals(nodeFour, nodeFive.getPrevNode());
	}

	/**
	 * Test method for {@link MyDLLNode#getNextNode()}.
	 */
	@Test
	void testGetSetNextNode() {
		nodeOne.setNextNode(nodeTwo);
		assertEquals(nodeTwo, nodeOne.getNextNode());
	}

	/**
	 * Test method for {@link MyDLLNode#getPrevNode()}.
	 */
	@Test
	void testGetSetPrevNode() {
		nodeTwo.setPrevNode(nodeOne);
		assertEquals(nodeOne, nodeTwo.getPrevNode());
	}

	/**
	 * Test method for {@link MyDLLNode#getElement()}.
	 */
	@Test
	void testGetElement() {
		assertEquals("String 1", nodeOne.getElement());
	}

	/**
	 * Test method for {@link MyDLLNode#setElement(java.lang.Object)}.
	 */
	@Test
	void testSetElement() {
		nodeOne.setElement(element);
		assertEquals(element, nodeOne.getElement());
	}
	
	
	
	
	
	
	// Tests for MyDLL
	
	/**
	 * Test method for {@link MyDLL#MyDLL(MyDLLNode)}.
	 */
	@Test
	void testMyDLL() {
		assertNull(listFour.getHead());
		assertNull(listFour.getTail());
		assertEquals(listFour.size(), 0);
	}
	
	/**
	 * Test method for {@link MyDLL#MyDLL(MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfE() {
		assertEquals(listOne.get(0), nodeOne.getElement());
		assertEquals(listOne.size(), 1);
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(2), nodeFive.getElement());
		assertEquals(listTwo.size(), 3);
	}
		
	/**
	 * Test method for {@link MyDLL#MyDLL(MyDLLNode, MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfEMyDLLNodeOfE() {
		assertEquals(listThree.get(0), nodeThree.getElement());
		assertEquals(listThree.get(1), nodeFour.getElement());
		assertEquals(listThree.size(), 2);
	}

	/**
	 * Test method for {@link MyDLL#getHead()}.
	 */
	@Test
	void testGetHead() {
		assertEquals(listOne.getHead().getElement(), nodeOne.getElement());
		assertEquals(listTwo.getHead().getElement(), nodeThree.getElement());
	}

	/**
	 * Test method for {@link MyDLL#getTail()}.
	 */
	@Test
	void testGetTail() {
		assertEquals(listOne.getTail().getElement(), nodeOne.getElement());
		assertEquals(listTwo.getTail().getElement(), nodeFive.getElement());
	}

	/**
	 * Test method for {@link MyDLL#hasNext()}.
	 */
	@Test
	void testHasNext() {
		assertFalse(listFour.hasNext());
		assertTrue(listTwo.hasNext());
		assertTrue(listOne.hasNext());
	}

	/**
	 * Test method for {@link MyDLL#next()}.
	 */
	@Test
	void testNextFailNull() {
		try {
			listFour.next();
			fail("Failed this test");
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#next()}.
	 */
	@Test
	void testNext() {
		E returnEle = listTwo.next();
		assertEquals(returnEle, listTwo.get(0));
		assertEquals(returnEle, nodeThree.getElement());
	}

	/**
	 * Test method for {@link MyDLL#size()}.
	 */
	@Test
	void testSize() {
		assertEquals(listOne.size(), 1);
		assertEquals(listTwo.size(), 3);
		assertEquals(listThree.size(), 2);
		assertEquals(listFour.size(), 0);
	}

	/**
	 * Test method for {@link MyDLL#clear()}.
	 */
	@Test
	void testClear() {
		listTwo.clear();
		assertNull(listTwo.getHead());
		assertNull(listTwo.getTail());
		assertEquals(listTwo.size(), 0);
	}

	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		boolean returnVar = listTwo.add(1, element);
		
		MyDLLNode<E> tempNode = (MyDLLNode<E>)listTwo.getNode(1);
		
		assertEquals(tempNode.getElement(), element);
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(3), nodeFive.getElement());
		assertEquals(listTwo.size(), 4);
		
		assertEquals(tempNode.getPrevNode().getElement(), nodeThree.getElement());
		assertEquals(tempNode.getNextNode().getElement(), nodeFour.getElement());
		
		assertTrue(returnVar);
		
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEEmpty() {
		boolean returnVar = listFour.add(0, element);
		
		MyDLLNode<E> tempNode = listFour.getHead();
		
		assertEquals(tempNode.getElement(), element);
		assertEquals(listFour.getTail(), tempNode);
		assertNull(tempNode.getNextNode());
		assertNull(tempNode.getPrevNode());
		assertEquals(listFour.size(), 1);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEHead() {
		boolean returnVar = listTwo.add(0, element);
		
		MyDLLNode<E> tempNode = listTwo.getHead();
		
		assertEquals(tempNode.getElement(), element);
		assertEquals(listTwo.get(3), nodeFive.getElement());
		assertEquals(tempNode.getNextNode().getElement(), nodeThree.getElement());
		assertEquals(listTwo.size(), 4);
		assertNull(tempNode.getPrevNode());
		assertTrue(returnVar);
	}

	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntETail() {
		boolean returnVar = listTwo.add(3, element);
		
		MyDLLNode<E> tempNode = listTwo.getTail();
		
		assertEquals(tempNode.getElement(), element);
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(tempNode.getPrevNode().getElement(), nodeFive.getElement());
		assertNull(tempNode.getNextNode());
		assertEquals(listTwo.size(), 4);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEFailOOB() {
		try {
			listTwo.add(4, element);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			listTwo.add(-1, element);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEFailNull() {
		try {
			listTwo.add(1, null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		boolean returnVar = listTwo.add(element);
		
		MyDLLNode<E> tempNode = listTwo.getTail();
		
		assertEquals(tempNode.getElement(), element);
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(tempNode.getPrevNode().getElement(), nodeFive.getElement());
		assertNull(tempNode.getNextNode());
		assertEquals(listTwo.size(), 4);
		assertTrue(returnVar);
	}

	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddEFailNull() {
		try {
			listTwo.add(null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#addAll(ListADT)}.
	 */
	@Test
	void testAddAll() {
		boolean returnVar = listOne.addAll(listTwo);
		
		assertEquals(listOne.get(0), nodeOne.getElement());
		assertEquals(listOne.getTail().getElement(), listTwo.getTail().getElement());
		assertEquals(listOne.size(), 4);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddAllFailNull() {
		try {
			listOne.addAll(null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link MyDLL#get(int)}.
	 */
	@Test
	void testGet() {
		assertEquals("String 3", listTwo.get(0));
		assertEquals("String 4", listTwo.get(1));
		assertEquals("String 5", listTwo.get(2));
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testGetFailOOB() {
		try {
			listOne.get(1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			listOne.get(-1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#get(int)}.
	 */
	@Test
	void testGetNode() {
		assertEquals(nodeThree.getElement(), listTwo.getNode(0).getElement());
		assertEquals(nodeFour.getElement(), listTwo.getNode(1).getElement());
		assertEquals(nodeFive.getElement(), listTwo.getNode(2).getElement());
	}

	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testGetNodeFailOOB() {
		try {
			listOne.getNode(1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			listOne.getNode(-1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveIntOneEle() {	
		String returnVar = (String)listOne.remove(0);
		
		assertNull(listOne.getHead());
		assertNull(listOne.getTail());
		assertEquals(listOne.size(), 0);
		assertEquals(returnVar, "String 1");
	}
	
	/**
	 * Test method for {@link MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveInt() {	
		String returnVar = (String)listTwo.remove(1);
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(1), nodeFive.getElement());
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 4");
	}

	/**
	 * Test method for {@link MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveIntHead() {
		String returnVar = (String)listTwo.remove(0);
		
		assertEquals(listTwo.get(0), nodeFour.getElement());
		assertEquals(listTwo.get(1), nodeFive.getElement());
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 3");
	}
	
	/**
	 * Test method for {@link MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveIntTail() {
		String returnVar = (String)listTwo.remove(2);
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(1), nodeFour.getElement());
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 5");
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testRemoveIntFailOOB() {
		try {
			listOne.remove(1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			listOne.remove(-1);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		String returnVar = (String)listTwo.remove((E)"String 4");
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(1), nodeFive.getElement());
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 4");
		
	}
	
	/**
	 * Test method for {@link MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveEOneEle() {
		String returnVar = (String)listOne.remove((E)"String 1");
		
		assertNull(listOne.getHead());
		assertNull(listOne.getTail());
		assertEquals(0, listOne.size());
		assertEquals(returnVar, "String 1");
		
	}

	/**
	 * Test method for {@link MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveEHead() {
		String returnVar = (String)listTwo.remove((E)"String 3");
		
		assertEquals(listTwo.get(0), nodeFour.getElement());
		assertEquals(listTwo.get(1), nodeFive.getElement());
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 3");
		
	}
	
	/**
	 * Test method for {@link MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveETail() {
		String returnVar = (String)listTwo.remove((E)"String 5");
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(1), nodeFour.getElement());
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 5");
		
	}
	
	/**
	 * Test method for {@link MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveEInexistant() {
		String returnVar = (String)listTwo.remove(element);
		
		assertEquals(listTwo.get(0), nodeThree.getElement());
		assertEquals(listTwo.get(2), nodeFive.getElement());
		assertEquals(3, listTwo.size());
		assertNull(returnVar);
		
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testRemoveEFailNull() {
		try {
			listOne.remove(null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		String returnVal = (String)listTwo.set(1, element);
		
		assertEquals(element, listTwo.get(1));
		assertEquals(returnVal, "String 4");
	}
	
	/**
	 * Test method for {@link MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetHead() {
		String returnVal = (String)listTwo.set(0, element);
		
		assertEquals(element, listTwo.getHead().getElement());
		assertEquals(returnVal, "String 3");
	}
	
	/**
	 * Test method for {@link MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetTail() {
		String returnVal = (String)listTwo.set(2, element);
		
		assertEquals(element, listTwo.getTail().getElement());
		assertEquals(returnVal, "String 5");
		
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testSetFailNull() {
		try {
			listOne.set(0, null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testSetFailOOB() {
		try {
			listOne.set(1, element);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			listOne.set(-1, element);
			fail("Failed this test");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link MyDLL#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertFalse(listOne.isEmpty());
		listOne.clear();
		assertTrue(listOne.isEmpty());
	}

	/**
	 * Test method for {@link MyDLL#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		assertTrue(listOne.contains((E)"String 1"));
		assertTrue(listTwo.contains((E)"String 5"));
		assertFalse(listOne.contains(element));
	}

	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testContainsFailNull() {
		try {
			listOne.contains(null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		arrayOne = listTwo.toArray((E[]) arrayOne);
		assertEquals(listTwo.getHead().getElement(), arrayOne[0]);
		assertEquals(listTwo.getTail().getElement(), arrayOne[2]);	
	}
	
	/**
	 * Test method for {@link MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArraySmallArr() {
		assertEquals(arrayTwo.length, 1);
		arrayTwo = listTwo.toArray((E[]) arrayTwo);
		assertEquals(listTwo.getHead().getElement(), arrayTwo[0]);
		assertEquals(listTwo.getTail().getElement(), arrayTwo[2]);	
		assertEquals(arrayTwo.length, 3);
	}
	
	/**
	 * Test method for {@link MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArrayNoEle() {
		arrayOne = listFour.toArray((E[]) arrayOne);
		assertNull(arrayOne[0]);
		assertNull(arrayOne[2]);	
	}
	
	/**
	 * Test method for {@link MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testToArrayEArrayFailNull() {
		try {
			listOne.toArray(null);
			fail("Failed this test");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link MyDLL#toArray()}.
	 */
	@Test
	void testToArray() {
		Object[] newArr = listTwo.toArray();
		
		assertEquals(listTwo.getHead().getElement(), newArr[0]);
		assertEquals(listTwo.getTail().getElement(), newArr[2]);	
		assertEquals(newArr.length, 3);
	}

	/**
	 * Test method for {@link MyDLL#iterator()}.
	 */
	@Test
	void testIterator() {
		it = listTwo.iterator();
		assertEquals(listTwo.next(), it.next());
		assertEquals(listTwo.next(), it.next());
		assertEquals(listTwo.next(), it.next());
		assertFalse(listTwo.hasNext());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	void testToStringNode() {
		String checkString = "String 1";
		String returnString = nodeOne.toString();
		assertEquals(checkString, returnString);
	}
	

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	void testToString() {
		String checkString = "String 3 String 4 String 5";
		String returnString = listTwo.toString();
		assertEquals(checkString, returnString);
	}

}
