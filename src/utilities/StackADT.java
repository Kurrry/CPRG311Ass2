package utilities;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Stack interface.
 *
 * @author Group 9
 */
public interface StackADT<E> {

    /**
     * Places item on top of the stack.
     *
     * @param toPush Item to be placed on top of the stack.
     * @throws NullPointerException when the item to be placed is null.
     */
    public void push(E toPush) throws NullPointerException;

    /**
     * Removes and returns the top element of the stack.
     *
     * @return The item removed from the top of the stack.
     * @throws EmptyStackException when attempting to pop an empty stack.
     */
    public E pop() throws EmptyStackException;

    /**
     * Returns the top element of the stack.
     *
     * @return The top element of the stack.
     * @throws EmptyStackException when attempting to peek into an empty stack.
     */
    public E peek() throws EmptyStackException;

    /**
     * Checks if another stack is equal to this one. Stacks must have the same
     * elements in the same order to be equal.
     *
     * @param otherStack The other stack to be checked for equality.
     * @return True if both stacks are identical. False otherwise.
     */
    public boolean equals(StackADT<E> otherStack);

    /**
     * Returns an Iterator which iterates over the stack's elements in order.
     *
     * @return The Iterator which iterates over the stack's elements.
     */
    public Iterator<E> iterator();

    /**
     * Copies all the stack's elements into an Object array. The top of the stack is
     * the first item in the array.
     *
     * @return The Object array containing all the stack's elements in order.
     */
    public Object[] toArray();

    /**
     * Copies all the stack's elements into an array. The top of the stack is
     * the first item in the array. The class type of the returned copied array is the
     * same as the specified array.
     *
     * @param copy E Array where the stack will be copied to if large enough. If not, a new array
     *             is allocated with the same class type.
     * @return The E array containing all the stack's elements in order.
     * @throws NullPointerException when the specified array is null
     */
    public E[] toArray(E[] copy) throws NullPointerException;

    /**
     * Returns the position of the specified element. Starts with 1 at the top of the stack.
     *
     * @param obj Element to find position of.
     * @return The position of the specified element. The top of the stack is position 1,
     *         the next is position 2, etc. -1 is returned if the element isn't in the stack.
     */
    public int search(E obj);

    /**
     * Returns whether the specified element exists in the stack.
     *
     * @param obj Element to find in stack.
     * @return True if the element is in the stack. False if not.
     * @throws NullPointerException when the specified element is null.
     */
    public boolean contains(E obj) throws NullPointerException;

    /**
     * Returns the number of elements in the stack.
     *
     * @return The current size of the stack.
     */
    public int size();

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack has no elements. False otherwise.
     */
    public boolean isEmpty();

    /**
     * Clears all items in the stack from top to bottom.
     */
    public void clear();
}
