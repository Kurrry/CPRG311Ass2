package main;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E>, Iterator<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyDLLNode<E> head, tail;
	private int count;
	private int current = 0;
	
	
	
	public MyDLL(MyDLLNode<E> head) {
		this.head = head;
		count = 1;
		
		MyDLLNode<E> currentNode = head;
		MyDLLNode<E> nextNode = currentNode.getNextNode();
		while(nextNode != null) {
			currentNode = nextNode;
			count += 1;
			nextNode = nextNode.getNextNode();
		}
		this.tail = currentNode;
	}
	
	public MyDLL(MyDLLNode<E> head, MyDLLNode<E> tail) {
		this.head = head;
		this.tail = tail;
		count = 1;
		
		MyDLLNode<E> currentNode = head;
		while(currentNode != tail) {
			currentNode = currentNode.getNextNode();
			count += 1;
		}	
	}
	
	
	
	/**
	 * @return the head
	 */
	public MyDLLNode<E> getHead() {
		return head;
	}


	/**
	 * @param head the head to set
	 */
	public void setHead(MyDLLNode<E> head) {
		this.head = head;
	}


	/**
	 * @return the tail
	 */
	public MyDLLNode<E> getTail() {
		return tail;
	}


	/**
	 * @param tail the tail to set
	 */
	public void setTail(MyDLLNode<E> tail) {
		this.tail = tail;
	}


	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}


	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	
	

	@Override
	public boolean hasNext() {
		return count - current > 1;
	}

	@Override
	public E next() throws NoSuchElementException {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		current += 1;
		E e = (E)this.get(current);
		return e;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return getCount();
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		count = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		} else if(toAdd == null) {
			throw new NullPointerException();
		}
		
		if(isEmpty()) {
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
			head = newNode;
			tail = newNode;
			count++;
		} else if(index == 0) {
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
			newNode.setNextNode(head);
			head.setPrevNode(newNode);
			head = newNode;
			count++;
		} else if (index == count - 1) {
			add(toAdd);
		} else {
			MyDLLNode<E> currentNode = (MyDLLNode<E>)this.get(index);
			new MyDLLNode<E>(toAdd, currentNode.getPrevNode(), currentNode);
			count++;
		}
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		
		if(isEmpty()) {
			head = newNode;
		} else {
			newNode.setPrevNode(tail);
			tail.setNextNode(newNode);
		}
		
		tail = newNode;
		count += 1;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		MyDLLNode<E> currentNode;
		MyDLLNode<E> prevNode = tail;
		
		// Change to FOR EACH?
		for(int i = 0; i < toAdd.size(); i++) {
			currentNode = new MyDLLNode<E>(toAdd.get(i));
			currentNode.setPrevNode(prevNode);
			tail = currentNode;
			prevNode.setNextNode(currentNode);
			prevNode = currentNode;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> currentNode = head;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNextNode();
		}
		return (E)currentNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> removedNode;
		
		if(index == 0) {
			removedNode = head;
			head = head.getNextNode();
			head.setPrevNode(null);
		} else if (index == count - 1) {
			removedNode = tail;
			tail = tail.getPrevNode();
			tail.setNextNode(null);
		} else {
			removedNode = (MyDLLNode<E>)this.get(index);
			MyDLLNode<E> prevNode = removedNode.getPrevNode();
			MyDLLNode<E> nextNode = removedNode.getNextNode();
			prevNode.setNextNode(nextNode);
			nextNode.setPrevNode(prevNode);
		}
		
		count--;
		removedNode.setNextNode(null);
		removedNode.setPrevNode(null);
		return (E) removedNode;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove == null) {
			throw new NullPointerException();
		}
		// Change to WHILE LOOP
		MyDLLNode<E> removedNode = head;
		int index = 0;
		for(int i = 0; i < count; i++) {
			if(removedNode.getElement() == toRemove) {
				return this.remove(index);
			} else {
				index += 1;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		} else if(toChange == null) {
			throw new NullPointerException();
		}
		MyDLLNode<E> currentNode = (MyDLLNode<E>)this.get(index);
		E oldElement = (E)currentNode.getElement();
		currentNode.setElement(toChange);
		
		return oldElement;
	}

	@Override
	public boolean isEmpty() {
		if (count == 0 && head == null && tail == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) {
			throw new NullPointerException();
		}
		
		// Change to WHILE LOOP
		MyDLLNode<E> currentNode = head;
		for(int i = 0; i < count; i++) {
			if(currentNode.getElement() == toFind) {
				return true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold == null) {
			throw new NullPointerException();
		} else if(count > toHold.length) {
			toHold = (E[])new Object[count];
		}
		
		// Change to WHILE LOOP
		MyDLLNode<E> currentNode = head;
		for(int i = 0; i < count; i++) {
			toHold[i] = (E) currentNode.getElement();
			currentNode = currentNode.getNextNode();
		}
		
		return toHold;
	}

	@Override
	public Object[] toArray() {
		Object[] arrList = new Object[count];
		
		// Change to WHILE LOOP
		MyDLLNode<E> currentNode = head;
		for(int i = 0; i < count; i++) {
			arrList[i] = currentNode.getElement();
			currentNode = currentNode.getNextNode();
		}
		return arrList;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorForDLL();
	}

	private class IteratorForDLL implements Iterator<E> {

		private MyDLLNode<E> nextNode;
		
		private IteratorForDLL() {
			nextNode = head;
		}
		
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public E next() throws NoSuchElementException {
			E result;
			if (hasNext()) {
				result = (E) nextNode.getElement();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
		
	}
	
}
