import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MyArrayList;
import utilities.Iterator;

import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author John
 *
 */
class MyArrayListTests<E> {

	MyArrayList<E> myArrayListOne, myArrayListTwo, myArrayListThree;
	E element;
	Object[] arrayOne, arrayTwo;
	Iterator<E> iterator;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		myArrayListOne = new MyArrayList<E>();
		myArrayListTwo = new MyArrayList<E>();
		
		myArrayListOne.add((E)"String 0");
		myArrayListOne.add((E)"String 1");
		myArrayListOne.add((E)"String 2");
		myArrayListOne.add((E)"String 3");
		
		myArrayListTwo.add((E)"String Zero");
		myArrayListTwo.add((E)"String One");
		myArrayListTwo.add((E)"String Two");
		myArrayListTwo.add((E)"String Three");
		
		element = (E)"Element to add";
		

		arrayOne = (E[])new Object[4];
		arrayTwo = (E[])new Object[1];
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		
		myArrayListOne.clear();
		myArrayListTwo.clear();
		
		element = null;
		
		arrayOne = null;
		arrayTwo = null;
		
		iterator = null;
		
	}

	/**
	 * Test method for {@link main.MyArrayList#MyArrayList()}.
	 */
	@Test
	void testMyArrayList() {
		assertNull(myArrayListThree);
		myArrayListThree = new MyArrayList<E>();
		assertNotNull(myArrayListThree);
		assertEquals(10,myArrayListThree.getCapacity());
	}

	/**
	 * Test method for {@link main.MyArrayList#MyArrayList(int)}.
	 */
	@Test
	void testMyArrayListInt() {
		assertNull(myArrayListThree);
		myArrayListThree = new MyArrayList<E>(100);
		assertNotNull(myArrayListThree);
		assertEquals(100,myArrayListThree.getCapacity());
	}

//	 MyArrayList.shiftRight() is a private method, will no longer be tested after confirming it works properly.
//	
//	/**
//	 * Test method for {@link main.MyArrayList#shiftRight(int)}.
//	 */
//	@Test
//	void testShiftRight() {
//		assertNotEquals(myArrayListOne.get(0),myArrayListOne.get(1));
//		myArrayListOne.shiftRight(0);
//		assertEquals(myArrayListOne.get(0),myArrayListOne.get(1));
//		assertEquals("String 0", myArrayListOne.get(0));
//		assertEquals("String 0", myArrayListOne.get(1));
//	}

	
//  MyArrayList.shiftLeft() is a private method, will no longer be tested after confirming it works properly.  
//
//	/**
//	 * Test method for {@link main.MyArrayList#shiftLeft(int)}.
//	 */
//	@Test
//	void testShiftLeft() {
//		assertNotEquals(myArrayListOne.get(myArrayListOne.size()-1), myArrayListOne.get(myArrayListOne.size()-2));
//		myArrayListOne.shiftLeft(0);
//		assertEquals(myArrayListOne.get(myArrayListOne.size()-1), myArrayListOne.get(myArrayListOne.size()-2));
//		
//	}

	
	
//	MyArrayList.increaseCap() is a private method, will no longer be tested after confirming it works properly.   
//	
//	/**
//	 * Test method for {@link main.MyArrayList#increaseCap()}.
//	 */
//	@Test
//	void testIncreaseCap() {
//		assertEquals(10, myArrayListOne.getCapacity());
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		myArrayListOne.add(element);
//		assertNotEquals(10, myArrayListOne.getCapacity());
//		assertEquals(15, myArrayListOne.getCapacity());
//
//
//	}

	/**
	 * Test method for {@link main.MyArrayList#size()}.
	 */
	@Test
	void testSize() {
		assertEquals(4,myArrayListOne.size());
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.MyArrayList#clear()}.
	 */
	@Test
	void testClear() {
		myArrayListOne.clear();
		assertEquals(0,myArrayListOne.size());
		assertTrue(myArrayListOne.isEmpty());
	}

	/**
	 * Test method for {@link main.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		assertTrue(myArrayListOne.add(0,element));
		assertEquals(element,myArrayListOne.get(0));
	}

	/**
	 * Test method for {@link main.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		assertTrue(myArrayListOne.add(element));
	}

	/**
	 * Test method for {@link main.MyArrayList#addAll(utilities.ListADT)}.
	 */
	@Test
	void testAddAll() {
		int orginalSize = myArrayListOne.size();
		assertNotEquals(myArrayListOne.size() + myArrayListTwo.size(), myArrayListOne.size());
		assertFalse(myArrayListOne.contains((E)"String Zero"));
		myArrayListOne.addAll(myArrayListTwo);
		assertEquals(orginalSize + myArrayListTwo.size(), myArrayListOne.size());
		assertTrue(myArrayListOne.contains((E)"String Zero"));
		assertEquals("String Three", myArrayListOne.get(myArrayListOne.size()-1));
	}

	/**
	 * Test method for {@link main.MyArrayList#get(int)}.
	 */
	@Test
	void testGet() {
		assertEquals("String 0",myArrayListOne.get(0));
	}

	/**
	 * Test method for {@link main.MyArrayList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		assertEquals("String 0",myArrayListOne.get(0));
		assertEquals(4, myArrayListOne.size());
		myArrayListOne.remove(0);
		assertNotEquals("String 0",myArrayListOne.get(0));
		assertFalse(myArrayListOne.contains((E)"String 0"));
		assertEquals(3, myArrayListOne.size());

	}

	/**
	 * Test method for {@link main.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		assertEquals(4, myArrayListOne.size());
		assertEquals("String 3", myArrayListOne.remove((E)"String 3"));
		assertEquals(3, myArrayListOne.size());
		assertNull(myArrayListOne.remove((E)"String 3"));
		}

	/**
	 * Test method for {@link main.MyArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		assertEquals("String 0",myArrayListOne.set(0,element));
		assertEquals(element,myArrayListOne.get(0));
		}

	/**
	 * Test method for {@link main.MyArrayList#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		myArrayListOne.clear();
		assertTrue(myArrayListOne.isEmpty());
		}

	/**
	 * Test method for {@link main.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		assertTrue(myArrayListOne.contains((E)"String 1"));
	}

	/**
	 * Test method for {@link main.MyArrayList#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		//Test with EArray containing same number of elements as MyArrayList
		//arrayOne and myArrayListOne both have 4 elements
		assertEquals(arrayOne.length,myArrayListOne.size());
		
		assertNotEquals(arrayOne[0],myArrayListOne.get(0));
		assertNotEquals(arrayOne[1],myArrayListOne.get(1));
		assertNotEquals(arrayOne[2],myArrayListOne.get(2));
		assertNotEquals(arrayOne[3],myArrayListOne.get(3));
		
		myArrayListOne.toArray((E[])arrayOne);
		
		assertEquals(arrayOne[0],myArrayListOne.get(0));
		assertEquals(arrayOne[1],myArrayListOne.get(1));
		assertEquals(arrayOne[2],myArrayListOne.get(2));
		assertEquals(arrayOne[3],myArrayListOne.get(3));
		
		//Test with EArray containing fewer number of elements than MyArrayList
		//arrayTwo has a length of 1, MyArrayListOne has 4 elements
		assertNotEquals(arrayTwo.length,myArrayListOne.size());
		arrayTwo = myArrayListOne.toArray((E[])arrayTwo);
		assertEquals(arrayTwo.length,myArrayListOne.size());
		assertEquals(arrayTwo[0],myArrayListOne.get(0));
		assertEquals(arrayTwo[1],myArrayListOne.get(1));
		assertEquals(arrayTwo[2],myArrayListOne.get(2));
		assertEquals(arrayTwo[3],myArrayListOne.get(3));
	}

	/**
	 * Test method for {@link main.MyArrayList#toArray()}.
	 */
	@Test
	void testToArray() {
		assertNotEquals(arrayTwo.length,myArrayListOne.size());
		arrayTwo = myArrayListOne.toArray();
		assertEquals(arrayTwo.length,myArrayListOne.size());
		assertEquals(arrayTwo[0],myArrayListOne.get(0));
		assertEquals(arrayTwo[1],myArrayListOne.get(1));
		assertEquals(arrayTwo[2],myArrayListOne.get(2));
		assertEquals(arrayTwo[3],myArrayListOne.get(3));
	}

	/**
	 * Test method for {@link main.MyArrayList#iterator()}.
	 */
	@Test
	void testIterator() {
		iterator = myArrayListOne.iterator();
		assertEquals(iterator.next(), myArrayListOne.next());
		assertEquals(iterator.next(), myArrayListOne.next());
		assertEquals(iterator.next(), myArrayListOne.next());
		assertEquals(iterator.next(), myArrayListOne.next());
		assertFalse(iterator.hasNext());
		assertFalse(myArrayListOne.hasNext());
	}

	/**
	 * Test method for {@link main.MyArrayList#hasNext()}.
	 */
	@Test
	void testHasNext() {
		myArrayListThree = new MyArrayList<E>();
		assertFalse(myArrayListThree.hasNext());
		myArrayListThree.add(element);
		assertTrue(myArrayListThree.hasNext());
	}

	/**
	 * Test method for {@link main.MyArrayList#next()}.
	 */
	@Test
	void testNext() {
		assertEquals("String 0", myArrayListOne.next());
		assertEquals("String 1", myArrayListOne.next());
		assertEquals("String 2", myArrayListOne.next());
		assertEquals("String 3", myArrayListOne.next());
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	void testToString() {
		String testString = "String 0" + " " + "String 1" + " " + "String 2" + " " + "String 3";
		assertEquals(testString,myArrayListOne.toString());
	}

}
