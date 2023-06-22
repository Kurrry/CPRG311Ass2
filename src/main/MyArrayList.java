package main;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>, Iterator<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053764349239653143L;
	// declare variables
	private E[] array;
	private int size;
	private int capacity; 
	private int iteratorIndex = 0;
	private static final int DEFAULT = 10;
	private static final double expandValue = 1.5;

	// default constructor
	public MyArrayList() {
		this(DEFAULT);
	}

	// constructor for MyArrayList of specific size
	@SuppressWarnings("unchecked")
	public MyArrayList(int initalSize) {
		array = (E[]) new Object[initalSize];
		capacity = initalSize;
		size = 0;
	}

	// method to shift all values after a specific index to the right
	// will allow MyArrayList to have values inserted at an index and automatically
	// shift the values to the right of that index over to make space
	private void shiftRight(int index) {
		for (int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
	}

	// method to shift all values after a specific index to the left
	// will allow MyArrayList to have values REMOVED at an index and automatically
	// shift the values to the left, thereby removing the empty space
	private void shiftLeft(int index) {

		for (int i = index; i < size-1; i++) {
			array[i] = array[i + 1];
		}
	}

	// Increases the capacity of the array by 50 percent.
	@SuppressWarnings("unchecked")
	private void increaseCap() {
		capacity = (int) Math.round(size * expandValue);
		E[] tempArray = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			tempArray[i] = array[i];
		}
		array = tempArray;
	}
	
	//returns the current capacity of the arrayList
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return The current element count.
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		while (size > 0) {
			remove(size - 1);
		}
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) {
			throw new NullPointerException();
		}

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (size >= capacity) {
			increaseCap();
		}

		shiftRight(index);

		array[index] = toAdd;
		size++;
		return true;
	}

	/*
	 * Append element to end of list return true if successfully added element
	 * 
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException();
		}

		if (size >= capacity) {
			increaseCap();
		}

		array[size] = toAdd;
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException();
		}
		
		Iterator<? extends E> iterator = toAdd.iterator();
		
		while(iterator.hasNext()) {
			add(iterator.next());
		}
		
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		E tempElement = array[index];

		if (index < (size - 1)) {
			shiftLeft(index);
		}
		size--;
		return tempElement;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i < size; i++) {
			if (array[i].equals(toRemove)) {
				return remove(i);
			}
		}

		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException();
		}

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		E tempElement = array[index];
		array[index] = toChange;

		return tempElement;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i < size; i++) {
			if (array[i].equals(toFind)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException();
		}

		if (size > toHold.length) {
			toHold = (E[])Array.newInstance(toHold.getClass().getComponentType(), size);
		}

		for (int i = 0; i < size; i++) {
			toHold[i] = array[i];
		}

		return toHold;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {

		E[] tempArray = (E[]) new Object[size];

		for (int i = 0; i < size; i++) {
			tempArray[i] = array[i];
		}

		return tempArray;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorForMyArrayList();
	}

	@Override
	public boolean hasNext() {
		if(iteratorIndex < size()){
			return true;
		}
		
		else {
			iteratorIndex = 0;
			return false;
		}
	}

	@Override
	public E next() throws NoSuchElementException {
		E tempElement;
		
		if(hasNext()) {
			tempElement = array[iteratorIndex];
			iteratorIndex++;
		}
		else {
			throw new NoSuchElementException();
		}
		return tempElement;
	}

	public String toString() {
		
//		StringBuilder returnString = new StringBuilder(this.next().toString());
//
//		while(this.hasNext()) {
//			returnString.append(" ").append(this.next().toString());
//		}
		
		Iterator<E> iterator = this.iterator();
		StringBuilder returnString = new StringBuilder(iterator.next().toString());

		while(iterator.hasNext()) {
			returnString.append(" ").append(iterator.next().toString());
		}
		
		return returnString.toString();
	}


	private class IteratorForMyArrayList implements Iterator<E> {
		
		private int innerClassIteratorIndex = 0;
		
		@Override
		public boolean hasNext() {
			if(innerClassIteratorIndex >= size()){
				innerClassIteratorIndex = 0;
				return false;
			}
			
			else {
				
				return true;
			}
		}

		@Override
		public E next() throws NoSuchElementException {
			E tempElement;
			
			if(hasNext()) {
				tempElement = array[innerClassIteratorIndex];
				innerClassIteratorIndex++;
			}
			else {
				throw new NoSuchElementException();
			}
			return tempElement;

		}
		
	}



}

//     
//                      _..--+~/@-~--.
//                  _-=~      (  .   "}
//               _-~     _.--=.\ \""""
//             _~      _-       \ \_\
//            =      _=          '--'
//           '      =                             .
//          :      :       ____                   '=_. ___
//     ___  |      ;                            ____ '~--.~.
//          ;      ;                               _____  } |
//       ___=       \ ___ __     __..-...__           ___/__/__
//          :        =_     _.-~~          ~~--.__
//     _____ \         ~-+-~                   ___~=_______
//          ~@#~~ == ...______ __ ___ _--~~--_