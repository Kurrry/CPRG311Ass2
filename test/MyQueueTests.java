import static org.junit.jupiter.api.Assertions.*;

import exceptions.EmptyQueueException;
import main.MyQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Iterator;

import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author Eric
 *
 */
class MyQueueTests<E> {

	MyQueue<E> queueOne, queueTwo, queueThree;

	E element;

	Object[] arrayOne, arrayTwo;
	Iterator<E> iterator;
	String testString = "String 0 String 1 String 2 String 3";
	String testString2 = "String 1 String 2 String 3";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		queueOne = new MyQueue<>();
		queueTwo = new MyQueue<>();
		queueThree = new MyQueue<>();

		queueOne.enqueue((E) "String 0");
		queueOne.enqueue((E) "String 1");
		queueOne.enqueue((E) "String 2");
		queueOne.enqueue((E) "String 3");

		queueTwo.enqueue((E) "String Zero");
		queueTwo.enqueue((E) "String One");
		queueTwo.enqueue((E) "String Two");
		queueTwo.enqueue((E) "String Three");

		element = (E) "Some element";
		arrayOne = new Object[4];
		arrayTwo = new Object[1];
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		queueOne.dequeueAll();
		queueTwo.dequeueAll();
		if (queueThree != null) queueThree.dequeueAll();

		element = null;
		arrayOne = null;
		arrayTwo = null;
		iterator = null;
	}

	/**
	 * Test if a new queue exists and is empty
	 */
	@Test
	void testMyQueue() {
		assertNull(queueThree.getHead());
		assertEquals(0, queueThree.size());
	}

	/**
	 * Test if a queue with elements in it has a next element
	 */
	@Test
	void testHasNextTrue() {
		assertTrue(queueOne.hasNext());
	}

	/**
	 * Test if an empty queue has no next
	 */
	@Test
	void testHasNextFalse() {

		assertFalse(queueThree.hasNext());
	}

	/**
	 * Test if next will return the expected value
	 */
	@Test
	void testNext() {
		assertEquals("String 0", queueOne.next());
	}

	/**
	 * Test the size of a queue to have the expected number of elements
	 */
	@Test
	void testSize() {
		assertEquals(4, queueOne.size());
	}

	/**
	 * Test if a queue with elements in it is not empty
	 */
	@Test
	void testIsNotEmpty() {
		assertFalse(queueOne.isEmpty());
	}

	/**
	 * Test if an empty queue is empty
	 */
	@Test
	void testIsEmpty() {
		assertTrue(queueThree.isEmpty());
	}

	/**
	 * Test if we can copy an E array of Same size as our queue
	 */
	@Test
	void testToArrayEArraySameSize() {
		assertEquals(arrayOne.length, queueOne.size());
		assertNotEquals(arrayOne[0], queueOne.get(0));
		assertNotEquals(arrayOne[1], queueOne.get(1));
		assertNotEquals(arrayOne[2], queueOne.get(2));
		assertNotEquals(arrayOne[3], queueOne.get(3));

		arrayOne = queueOne.toArray((E[]) arrayOne);
		assertEquals(arrayOne[0], queueOne.get(0));
		assertEquals(arrayOne[1], queueOne.get(1));
		assertEquals(arrayOne[2], queueOne.get(2));
		assertEquals(arrayOne[3], queueOne.get(3));
	}

	/**
	 * Test if we can copy an E array of different size as our queue
	 */
	@Test
	void testToArrayEArrayDiffSize() {
		assertNotEquals(arrayTwo.length, queueOne.size());

		arrayTwo = queueOne.toArray((E[]) arrayTwo);
		assertEquals(arrayTwo.length, queueOne.size());
		assertEquals(arrayTwo[0], queueOne.get(0));
		assertEquals(arrayTwo[1], queueOne.get(1));
		assertEquals(arrayTwo[2], queueOne.get(2));
		assertEquals(arrayTwo[3], queueOne.get(3));
	}

	/**
	 * Test if we can copy an Object array of different size as our queue
	 */
	@Test
	void testToArrayDiffSize() {
		assertNotEquals(arrayTwo.length, queueOne.size());
		arrayTwo = queueOne.toArray();
		assertEquals(arrayTwo.length, queueOne.size());
		assertEquals(arrayTwo[0], queueOne.get(0));
		assertEquals(arrayTwo[1], queueOne.get(1));
		assertEquals(arrayTwo[2], queueOne.get(2));
		assertEquals(arrayTwo[3], queueOne.get(3));
	}

	/**
	 * Test if we can copy an Object array of same size as our queue
	 */
	@Test
	void testToArraySameSize() {
		assertEquals(arrayOne.length, queueOne.size());
		arrayOne = queueOne.toArray();
		assertEquals(arrayOne.length, queueOne.size());
		assertEquals(arrayOne[0], queueOne.get(0));
		assertEquals(arrayOne[1], queueOne.get(1));
		assertEquals(arrayOne[2], queueOne.get(2));
		assertEquals(arrayOne[3], queueOne.get(3));
	}

	/**
	 * Test if iterator iterates through list and returns false at end of list
	 */
	@Test
	void testIterator() {
		iterator = queueOne.iterator();
		assertEquals(iterator.next(), queueOne.next());
		assertEquals(iterator.next(), queueOne.next());
		assertEquals(iterator.next(), queueOne.next());
		assertEquals(iterator.next(), queueOne.next());
		assertFalse(iterator.hasNext());
		assertFalse(queueOne.hasNext());
	}

	/**
	 * Test if the queue can be turned into a string
	 */
	@Test
	void testToString() {
		assertEquals(testString, queueOne.toString());
	}

	/**
	 * Test if we can add an element to the end of the queue
	 */
	@Test
	void testEnqueue() {
		assertEquals(testString, queueOne.toString());
		queueOne.enqueue((E) "String 4");
		testString = testString + " String 4";
		assertEquals(testString, queueOne.toString());
	}

	/**
	 * Test if we can dequeue the first element in the queue
	 * @Throws EmptyQueueException if the queue is empty. This should not happen.
	 */
	@Test
	void testDequeueNoEx() throws EmptyQueueException {
		assertEquals(testString, queueOne.toString());
		queueOne.dequeue();
		assertEquals(testString2, queueOne.toString());
	}

	/**
	 * Test if we get an exception while dequeue the first element in empty queue
	 * @Throws EmptyQueueException if the queue is empty. This should happen.
	 */
	@Test
	void testDequeueEx() {
		try {
			queueThree.dequeue();
			fail("Queue had element");
		} catch (EmptyQueueException ex) {
			assertTrue(true);
		}
	}

	/**
	 * Test if we can peek at the first element in the queue
	 * @Throws EmptyQueueException if the queue is empty. This should not happen.
	 */
	@Test
	void testPeekNoEx() throws EmptyQueueException {
		assertEquals("String 0", queueOne.peek());
	}

	/**
	 * Test if we cannot peek at the first element in empty queue
	 * @Throws EmptyQueueException if the queue is empty. This should happen.
	 */
	@Test
	void testPeekEx() {
		try {
			queueThree.peek();
			fail("Queue had element");
		} catch (EmptyQueueException ex) {
			assertTrue(true);
		}
	}

	/**
	 * Test if two queues are equal to each other
	 */
	@Test
	void testEqualsQueueADTOfEPass() {
		queueThree = queueOne;
		assertTrue(queueOne.equals(queueThree));
	}

	/**
	 * Test if two queues are not equal to each other
	 */
	@Test
	void testEqualsQueueADTOfEFail() {
		assertFalse(queueOne.equals(queueTwo));
	}

	/**
	 * Test if a queue with elements is full
	 * In this case full means that the list as a number of elements greater than 0
	 */
	@Test
	void testIsFull() {
		assertTrue(queueOne.isFull());
	}

	/**
	 * Test if a queue with elements has all its elements dequeued
	 * @Throws EmptyQueueException if the queue is empty, and we try to peek. This should happen.
	 */
	@Test
	void testDequeueAll() {
		try {
			assertNotNull(queueOne);
			queueOne.dequeueAll();
			queueOne.peek();
			fail("Queue not empty");
		} catch (EmptyQueueException ex) {
			assertTrue(true);
		}
	}

}
