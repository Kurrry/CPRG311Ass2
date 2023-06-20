package main;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>, Iterator<E> {

	//declare variables
	private E[] array;
	private int size;
	public int capacity; //
	private static final int DEFAULT = 10;
	private static final double expandValue = 1.5;
	
	//default constructor
	public MyArrayList() {
		this(DEFAULT);
	}
	
	//constructor for MyArrayList of specific size
	//
	//NOTE TO SELF: Why does IDE recommend the SuppressWarinings("unchecked")?
	public MyArrayList(int initalSize) {
		@SuppressWarnings("unchecked")
		E[] tempArray = (E[]) new Object[initalSize];
		array = tempArray;
		capacity = initalSize;
		size = 0;
		//initialized = true;
	}
	
	

	//method to shift all values after a specific index to the right
	//will allow MyArrayList to have values inserted at an index and automatically
	//shift the values to the right of that index over to make space
	public void shiftRight(int index) {
		for(int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
	}
	
	
	//method to shift all values after a specific index to the left
	//will allow MyArrayList to have values REMOVED at an index and automatically
	//shift the values to the left, thereby removing the empty space
	public void shiftLeft(int index) {

		for(int i = index; i < size; i++) {
			array[i] = array[i + 1];
		}
	}
	
	
	//Increases the capacity of the array by 50 percent.
	public void increaseCap() {
		System.out.println("running increaseCap()");
		capacity = (int) Math.round(size * expandValue);
		@SuppressWarnings("unchecked")
		E[] tempArray = (E[]) new Object[capacity];
		for(int i = 0; i < size; i++) {
			tempArray[i] = array[i];
		}
		array = tempArray;
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
		// TODO Auto-generated method stub
		while(size > 0) {
			remove(size -1);
		}
	}


	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size >= capacity) {
			increaseCap();
		}
		
		shiftRight(index);

		array[index] = toAdd;
		size++;
		return true;
	}

	
	
	/*
	 *Append element to end of list
	 *return true if successfully added element 
	 * 
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		if(size >= capacity) {
			increaseCap();
		}
		
		array[size] = toAdd;
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		E tempElement = array[index];
		
		if(index < (size - 1)) {
			shiftLeft(index);
		}
		size--;
		return tempElement;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if(toChange == null) {
			throw new NullPointerException();
		}
		
		if(index < 0 || index >= size) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
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
//                                                         =