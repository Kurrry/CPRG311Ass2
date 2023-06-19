package main;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

import java.util.NoSuchElementException;

public class MyQueue<E> extends MyDLL<E> implements QueueADT<E>, Iterator<E> {

    public MyQueue() {

    }
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() throws NoSuchElementException {
        return null;
    }

    @Override
    public void enqueue(E toQueue) throws NullPointerException {
        try {
            this.add(toQueue);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        try {
            if (this.size() == 0) {
                throw new EmptyQueueException();
            }
            E element = this.get(0);
            this.remove(0);
            return element;
        } catch (EmptyQueueException ex) {
            return null;
        }
    }

    @Override
    public E peek() throws EmptyQueueException {
        try {
            if (this.size() == 0) {
                throw new EmptyQueueException();
            }
            return this.get(0);
        } catch (EmptyQueueException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(QueueADT<E> otherQueue) {
        int i = 0;
        Object[] queueArray = this.toArray();
        Object[] otherArray = otherQueue.toArray();

        while (i < this.size()) {
            if (!queueArray[i].equals(otherArray[i])) {
                return false;
            }
            i++;
        }
        return true;
    }



    @Override
    public Object[] toArray() {
        Object[] temp = new Object[this.size()];
        Iterator<E> iterator = this.iterator();
        int i = 0;

        while(iterator.hasNext()) {
            temp[i] = this.get(i);
            i++;
        }
        return temp;
    }

    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        if(copy == null || copy.length == 0) {
            throw new NullPointerException();
        }
        copy = (E[]) new Object[this.size()];
        Iterator<E> iterator = this.iterator();
        int i = 0;

        while(iterator.hasNext()) {
            copy[i] = this.get(i);
            i++;
        }
        return copy;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0 && this.getHead() == null && this.getTail() == null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void dequeueAll() {

    }

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
        //return new QueueIterator();
    }
}
