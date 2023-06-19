package main;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

import java.util.NoSuchElementException;

public class MyQueue<E> extends MyDLL<E> implements QueueADT<E>, Iterator<E> {

    private MyQueue<E> queue;

    public MyQueue() {
        queue = new MyQueue<>();
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
            if (queue.size() == 0) {
                queue.add(toQueue);
            } else {
                queue.add(queue.size(), toQueue);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        try {
            if (queue.size() == 0) {
                throw new EmptyQueueException();
            }
            E element = queue.get(0);
            queue.remove(0);
            return element;
        } catch (EmptyQueueException ex) {
            return null;
        }
    }

    @Override
    public E peek() throws EmptyQueueException {
        try {
            if (queue.size() == 0) {
                throw new EmptyQueueException();
            }
            return queue.get(0);
        } catch (EmptyQueueException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(QueueADT<E> otherQueue) {
        int i = 0;
        Object[] queueArray = queue.toArray();
        Object[] otherArray = otherQueue.toArray();

        while (i < queue.size()) {
            if (!queueArray[i].equals(otherArray[i])) {
                return false;
            }
            i++;
        }
        return true;
    }



    @Override
    public Object[] toArray() {
        Object[] temp = new Object[queue.size()];
        Iterator<E> iterator = queue.iterator();
        int i = 0;

        while(iterator.hasNext()) {
            temp[i] = queue.get(i);
            i++;
        }
        return temp;
    }

    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        if(copy == null || copy.length == 0) {
            throw new NullPointerException();
        }
        copy = (E[]) new Object[queue.size()];
        Iterator<E> iterator = queue.iterator();
        int i = 0;

        while(iterator.hasNext()) {
            copy[i] = queue.get(i);
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
        return false;
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
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private MyDLLNode<E> node;

        private QueueIterator() {
            node = queue.getHead();
        }
        @Override
        public boolean hasNext() {
            return node.getNextNode() != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (hasNext()) {
                node = node.getNextNode();
                return node.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
