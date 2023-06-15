package utilities;

import exceptions.EmptyQueueException;
import java.util.Iterator;

/**
 * Queue interface.
 *
 * @author Group 9
 */
public interface QueueADT<E> {

    /**
     * Adds an element to the end of the queue.
     *
     * @param toQueue Element to be added to end of queue.
     * @throws NullPointerException when element to be added is null.
     */
    public void enqueue(E toQueue) throws NullPointerException;

    /**
     * Removes and returns the first element placed in the queue.
     *
     * @return The first element in the queue.
     * @throws EmptyQueueException when the attempting to dequeue from an empty queue (length = 0).
     */
    public E dequeue() throws EmptyQueueException;

    /**
     * Returns a reference to the first element placed in the queue.
     *
     * @return The first element placed in the queue.
     * @throws EmptyQueueException when the attempting to peek into an empty queue (length = 0).
     */
    public E peek() throws EmptyQueueException;

    /**
     * Checks if another queue is equal to this one. The queues must have the same
     * elements in the same order to be equal.
     *
     * @param otherQueue The queue to be compared for equality.
     * @return True if the queues are equal. False otherwise.
     */
    public boolean equals(QueueADT<E> otherQueue);

    /**
     * Returns an Iterator which iterates over the queue's elements in order.
     *
     * @return The Iterator which iterates over the queue's elements.
     */
    public Iterator<E> iterator();

    /**
     * Copies all the queue's elements into an array. The head of the queue is
     * the first item in the array.
     *
     * @return The array containing all the queue's elements in order.
     */
    public Object[] toArray();

    /**
     * Copies all the queue's elements into an array. The head of the queue is
     * the first item in the array. The runtime type of the returned copied array is the
     * same as the specified array.
     *
     * @param copy Array where the queue will be copied to if large enough. If not, a new array
     *             is allocated with the same runtime type.
     * @return The array containing all the queue's elements in order.
     * @throws NullPointerException when the specified array is null
     */
    public E[] toArray(E[] copy) throws NullPointerException;

    /**
     * Checks if the fixed size queue is full. The queue is full when the number of elements equals
     * the queue length.
     *
     * @return True if the queue is full. False otherwise.
     */
    public boolean isFull();

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue has no elements. False otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return The current size of the queue.
     */
    public int size();

    /**
     * Removes all elements in the queue.
     */
    public void dequeueAll();
}
