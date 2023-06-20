import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MyDLL;
import main.MyDLLNode;
import utilities.Iterator;

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
	 * Test method for {@link main.MyDLLNode#MyDLLNode(java.lang.Object)}.
	 */
	@Test
	void testMyDLLNodeE() {
		assertEquals("String 1", nodeOne.getElement());
		assertNull(nodeOne.getNextNode());
		assertNull(nodeOne.getPrevNode());
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
	 * Test method for {@link main.MyDLLNode#getNextNode()}.
	 */
	@Test
	void testGetSetNextNode() {
		nodeOne.setNextNode(nodeTwo);
		assertSame(nodeTwo, nodeOne.getNextNode());
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
		nodeOne.setElement(element);
		assertEquals(element, nodeOne.getElement());
	}
	
	
	
	
	
	
	// Tests for MyDLL
	
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode)}.
	 */
	@Test
	void testMyDLL() {
		assertNull(listFour.getHead());
		assertNull(listFour.getTail());
		assertEquals(listFour.size(), 0);
	}
	
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfE() {
		assertSame(listOne.getHead(), nodeOne);
		assertSame(listOne.getTail(), nodeOne);
		assertEquals(listOne.size(), 1);
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(listTwo.size(), 3);
	}
		
	/**
	 * Test method for {@link main.MyDLL#MyDLL(main.MyDLLNode, main.MyDLLNode)}.
	 */
	@Test
	void testMyDLLMyDLLNodeOfEMyDLLNodeOfE() {
		assertSame(listThree.getHead(), nodeThree);
		assertSame(listThree.getTail(), nodeFour);
		assertEquals(listThree.size(), 2);
	}

	/**
	 * Test method for {@link main.MyDLL#getHead()}.
	 */
	@Test
	void testGetHead() {
		assertSame(listOne.getHead(), nodeOne);
		assertSame(listTwo.getHead(), nodeThree);
	}

	/**
	 * Test method for {@link main.MyDLL#getTail()}.
	 */
	@Test
	void testGetTail() {
		assertSame(listOne.getTail(), nodeOne);
		assertSame(listTwo.getTail(), nodeFive);
	}

	/**
	 * Test method for {@link main.MyDLL#hasNext()}.
	 */
	@Test
	void testHasNext() {
		assertFalse(listFour.hasNext());
		assertTrue(listTwo.hasNext());
		assertTrue(listOne.hasNext());
	}

	/**
	 * Test method for {@link main.MyDLL#next()}.
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
	 * Test method for {@link main.MyDLL#next()}.
	 */
	@Test
	void testNext() {
		E returnEle = listTwo.next();
		assertEquals(returnEle, listTwo.get(0));
		assertEquals(returnEle, nodeThree.getElement());
	}

	/**
	 * Test method for {@link main.MyDLL#size()}.
	 */
	@Test
	void testSize() {
		assertEquals(listOne.size(), 1);
		assertEquals(listTwo.size(), 3);
		assertEquals(listThree.size(), 2);
		assertEquals(listFour.size(), 0);
	}

	/**
	 * Test method for {@link main.MyDLL#clear()}.
	 */
	@Test
	void testClear() {
		listTwo.clear();
		assertNull(listTwo.getHead());
		assertNull(listTwo.getTail());
		assertEquals(listTwo.size(), 0);
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		boolean returnVar = listTwo.add(1, element);
		
		MyDLLNode<E> tempNode = (MyDLLNode<E>)listTwo.getNode(1);
		
		assertSame(tempNode.getElement(), element);
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(listTwo.size(), 4);
		
		assertSame(tempNode.getPrevNode(), nodeThree);
		assertSame(tempNode.getNextNode(), nodeFour);
		assertSame(tempNode, nodeThree.getNextNode());
		assertSame(tempNode, nodeFour.getPrevNode());
		
		assertTrue(returnVar);
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEEmpty() {
		boolean returnVar = listFour.add(0, element);
		
		MyDLLNode<E> tempNode = listFour.getHead();
		
		assertSame(tempNode.getElement(), element);
		assertSame(listFour.getTail(), tempNode);
		assertNull(tempNode.getNextNode());
		assertNull(tempNode.getPrevNode());
		assertEquals(listFour.size(), 1);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntEHead() {
		boolean returnVar = listTwo.add(0, element);
		
		MyDLLNode<E> tempNode = listTwo.getHead();
		
		assertSame(tempNode.getElement(), element);
		assertSame(listTwo.getTail(), nodeFive);
		assertSame(tempNode.getNextNode(), nodeThree);
		assertSame(tempNode, nodeThree.getPrevNode());
		assertEquals(listTwo.size(), 4);
		assertTrue(returnVar);
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntETail() {
		boolean returnVar = listTwo.add(3, element);
		
		MyDLLNode<E> tempNode = listTwo.getTail();
		
		assertSame(tempNode.getElement(), element);
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(tempNode.getPrevNode(), nodeFive);
		assertNull(tempNode.getNextNode());
		assertEquals(listTwo.size(), 4);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		boolean returnVar = listTwo.add(element);
		
		MyDLLNode<E> tempNode = listTwo.getTail();
		
		assertSame(tempNode.getElement(), element);
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(tempNode.getPrevNode(), nodeFive);
		assertNull(tempNode.getNextNode());
		assertEquals(listTwo.size(), 4);
		assertTrue(returnVar);
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#addAll(utilities.ListADT)}.
	 */
	@Test
	void testAddAll() {
		boolean returnVar = listOne.addAll(listTwo);
		
		assertSame(listOne.getHead(), nodeOne);
		assertEquals(listOne.getTail().getElement(), listTwo.getTail().getElement());
		assertEquals(listOne.size(), 4);
		assertTrue(returnVar);
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#get(int)}.
	 */
	@Test
	void testGet() {
		assertEquals("String 3", listTwo.get(0));
		assertEquals("String 4", listTwo.get(1));
		assertEquals("String 5", listTwo.get(2));
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#get(int)}.
	 */
	@Test
	void testGetNode() {
		assertEquals(nodeThree, listTwo.getNode(0));
		assertEquals(nodeFour, listTwo.getNode(1));
		assertEquals(nodeFive, listTwo.getNode(2));
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#remove(int)}.
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
	 * Test method for {@link main.MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveInt() {	
		String returnVar = (String)listTwo.remove(1);
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 4");
	}

	/**
	 * Test method for {@link main.MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveIntHead() {
		String returnVar = (String)listTwo.remove(0);
		
		assertSame(listTwo.getHead(), nodeFour);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 3");
	}
	
	/**
	 * Test method for {@link main.MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveIntTail() {
		String returnVar = (String)listTwo.remove(2);
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFour);
		assertEquals(listTwo.size(), 2);
		assertEquals(returnVar, "String 5");
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		String returnVar = (String)listTwo.remove((E)"String 4");
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 4");
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveEHead() {
		String returnVar = (String)listTwo.remove((E)"String 3");
		
		assertSame(listTwo.getHead(), nodeFour);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 3");
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveETail() {
		String returnVar = (String)listTwo.remove((E)"String 5");
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFour);
		assertEquals(2, listTwo.size());
		assertEquals(returnVar, "String 5");
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveEInexistant() {
		String returnVar = (String)listTwo.remove(element);
		
		assertSame(listTwo.getHead(), nodeThree);
		assertSame(listTwo.getTail(), nodeFive);
		assertEquals(3, listTwo.size());
		assertNull(returnVar);
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		String returnVal = (String)listTwo.set(1, element);
		
		assertEquals(element, listTwo.get(1));
		assertEquals(returnVal, "String 4");
	}
	
	/**
	 * Test method for {@link main.MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetHead() {
		String returnVal = (String)listTwo.set(0, element);
		
		assertEquals(element, listTwo.getHead().getElement());
		assertEquals(returnVal, "String 3");
	}
	
	/**
	 * Test method for {@link main.MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetTail() {
		String returnVal = (String)listTwo.set(2, element);
		
		assertEquals(element, listTwo.getTail().getElement());
		assertEquals(returnVal, "String 5");
		
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertFalse(listOne.isEmpty());
		listOne.clear();
		assertTrue(listOne.isEmpty());
	}

	/**
	 * Test method for {@link main.MyDLL#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		assertTrue(listOne.contains((E)"String 1"));
		assertTrue(listTwo.contains((E)"String 5"));
		assertFalse(listOne.contains(element));
	}

	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		arrayOne = listTwo.toArray((E[]) arrayOne);
		assertEquals(listTwo.getHead().getElement(), arrayOne[0]);
		assertEquals(listTwo.getTail().getElement(), arrayOne[2]);	
	}
	
	/**
	 * Test method for {@link main.MyDLL#toArray(E[])}.
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
	 * Test method for {@link main.MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArrayNoEle() {
		arrayOne = listFour.toArray((E[]) arrayOne);
		assertNull(arrayOne[0]);
		assertNull(arrayOne[2]);	
	}
	
	/**
	 * Test method for {@link main.MyDLL#add(int, java.lang.Object)}.
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
	 * Test method for {@link main.MyDLL#toArray()}.
	 */
	@Test
	void testToArray() {
		Object[] newArr = listTwo.toArray();
		
		assertEquals(listTwo.getHead().getElement(), newArr[0]);
		assertEquals(listTwo.getTail().getElement(), newArr[2]);	
		assertEquals(newArr.length, 3);
	}

	/**
	 * Test method for {@link main.MyDLL#iterator()}.
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
