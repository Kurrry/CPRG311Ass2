package datastructures;

import interfaces.Iterator;
import interfaces.StackADT;

import java.util.EmptyStackException;

/**
 * enforces stack behavior on ArrayList parent datastructure
 * @param <E> generic type that the stack will contain
 */
public class MyStack<E> extends MyArrayList<E> implements StackADT<E>, Iterator<E> {

    public MyStack() {}

    @Override
    public void push(E toPush) throws NullPointerException {
        if (toPush == null)
            throw new NullPointerException();

        this.add(toPush);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        E topElement = this.get(this.size() - 1);
        this.remove(this.size() - 1);

        return topElement;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        return this.get(this.size() - 1);
    }

    @Override
    public boolean equals(StackADT<E> otherStack) {
        Object[] thisStackArray = this.toArray();
        Object[] otherStackArray = otherStack.toArray();

        for (int i = 0; i < this.size(); i++) {
            if (!thisStackArray[i].equals(otherStackArray[i]))
                return false;
        }

        return true;
    }

    @Override
    public int search(E obj) {
        for (int i = 1; i < this.size() + 1; i++) {
            if (this.get(this.size() - i) == obj)
                    return i;
        }

        return -1;
    }

    @Override
    public boolean contains(E obj) throws NullPointerException {
        return super.contains(obj);
    }

    @Override
    public int size() {
        return super.size();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void clear() {
        super.clear();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        return super.toArray(copy);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
        //return new IteratorForMyStack();
    }

//    private class IteratorForMyStack implements Iterator<E>{
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public E next() throws NoSuchElementException {
//            return null;
//        }
//    }
}
