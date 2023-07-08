package datastructures;

import exceptions.EmptyQueueException;
import interfaces.Iterator;
import interfaces.QueueADT;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * enforces queue behavior on DLL parent datastructure
 * @param <E> generic type that the queue will contain
 */
public class MyQueue<E> extends MyDLL<E> implements QueueADT<E>, Iterator<E> {

	private static final long serialVersionUID = 1L;
	private static final int topOfQueue = 0;

    public MyQueue() {

    }
    @Override
    public boolean hasNext() {
        return super.hasNext();
    }

    @Override
    public E next() throws NoSuchElementException {
        return super.next();
    }

    @Override
    public void enqueue(E toQueue) throws NullPointerException {
        if (toQueue == null) throw new NullPointerException();
        this.add(toQueue);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (this.isEmpty()) throw new EmptyQueueException();
        E element = this.get(topOfQueue);
        this.remove(topOfQueue);
        return element;
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (this.isEmpty()) throw new EmptyQueueException();
        return this.get(topOfQueue);
    }

    @Override
    public boolean equals(QueueADT<E> otherQueue) {
        int i = 0;
        Object[] queueArray = this.toArray();
        Object[] otherArray = otherQueue.toArray();

        while (i < this.size()) {
            if (!queueArray[i].equals(otherArray[i])) return false;
            i++;
        }
        return true;
    }



    @Override
    public Object[] toArray() {
        Object[] temp = new Object[this.size()];
        MyDLLNode<E> current = this.getHead();
        for(int i = 0; i < this.size(); i++) {
            temp[i] = current.getElement();
            current = current.getNextNode();
        }
        return temp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        if(copy == null || copy.length == 0 || this.isEmpty()) {
            throw new NullPointerException();
        }
        if (this.size() > copy.length) {
            copy = (E[]) Array.newInstance(copy.getClass().getComponentType(), this.size());
        }
        MyDLLNode<E> current = this.getHead();
        for(int i = 0; i < this.size(); i++) {
            copy[i] = current.getElement();
            current = current.getNextNode();
        }
        return copy;
    }

    @Override
    public boolean isFull() {
        return this.getHead() != null && this.getTail() != null && this.size() != 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0 && this.getHead() == null && this.getTail() == null;
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public void dequeueAll() {
        try {
            while (this.iterator().hasNext()) {
                System.out.println(this.dequeue());
            }
            System.out.println(this.dequeue());
        } catch (EmptyQueueException ex) {
            System.out.println("Queue is empty.");
        }
    }

    @Override
    public Iterator<E> iterator() {
        //return super.iterator();
        return new QueueIterator(this.getHead());
    }

    @Override
    public String toString() {
        Iterator<E> it = this.iterator();
        StringBuilder returnStr = new StringBuilder(it.next().toString());
        while(it.hasNext()) {
            returnStr.append(" ").append(it.next().toString());
        }
        return returnStr.toString();
    }


    /**
     * private inner class QueueIterator for iterator() method
     */
    private class QueueIterator implements Iterator<E> {
        private MyDLLNode<E> current;
        private QueueIterator(MyDLLNode<E> current) {
        	
        	this.current = new MyDLLNode<>();
        	this.current.setNextNode(current);
        }

        @Override
        public boolean hasNext() {
            return current.getNextNode() != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if(!this.hasNext()) throw new NoSuchElementException();
            current = current.getNextNode();
            return current.getElement();
        }
    }
    
    /*private class QueueIterator implements Iterator<E> {
        private MyDLLNode<E> current;
        private QueueIterator(MyDLLNode<E> current) {this.current = current;}

        @Override
        public boolean hasNext() {
            return current.getNextNode() != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if(!this.hasNext()) throw new NoSuchElementException();
            current = current.getNextNode();
            return current.getElement();
        }
    } */
}
