package interfaces;

import exceptions.EmptyQueueException;

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
    void enqueue(E toQueue) throws NullPointerException;

    /**
     * Removes and returns the first element placed in the queue.
     *
     * @return The item removed from the start of the queue.
     * @throws EmptyQueueException when the attempting to dequeue from an empty queue (length = 0).
     */
    E dequeue() throws EmptyQueueException;

    /**
     * Returns a reference to the first element placed in the queue.
     *
     * @return The first element placed in the queue.
     * @throws EmptyQueueException when the attempting to peek into an empty queue (length = 0).
     */
    E peek() throws EmptyQueueException;

    /**
     * Checks if another queue is equal to this one. The queues must have the same
     * elements in the same order to be equal.
     *
     * @param otherQueue The queue to be compared for equality.
     * @return True if the queues are equal. False otherwise.
     */
    boolean equals(QueueADT<E> otherQueue);

    /**
     * Returns an Iterator which iterates over the queue's elements in order.
     *
     * @return The Iterator which iterates over the queue's elements.
     */
    Iterator<E> iterator();

    /**
     * Copies all the queue's elements into an Object array. The head of the queue is
     * the first item in the array.
     *
     * @return The Object array containing all the queue's elements in order.
     */
    Object[] toArray();

    /**
     * Copies all the queue's elements into an E array. The head of the queue is
     * the first item in the array. The class type of the returned copied array is the
     * same as the specified array.
     *
     * @param copy E Array where the queue will be copied to if large enough. If not, a new array
     *             is allocated with the same class type.
     * @return The E array containing all the queue's elements in order.
     * @throws NullPointerException when the specified array is null
     */
    E[] toArray(E[] copy) throws NullPointerException;

    /**
     * (Optional method)
     * Checks if the fixed size queue is full. The queue is full when the number of elements equals
     * the queue length.
     *
     * @return True if the queue is full. False otherwise.
     */
    boolean isFull();

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue has no elements. False otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return The current size of the queue.
     */
    int size();

    /**
     * Removes all elements in the queue in order of start to end.
     */
    void dequeueAll();
}
