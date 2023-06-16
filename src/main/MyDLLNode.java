package main;

public class MyDLLNode<E> {
	
	private MyDLLNode<E> nextNode, prevNode;
	private E element;
	
	public MyDLLNode(E element) {
		this.element = element;
	}
	
	public MyDLLNode(E element, MyDLLNode<E> prevNode, MyDLLNode<E> nextNode) {
		//MyDLLNode<E> tempNext = prevNode.getNextNode();
		this.element = element;
		this.prevNode = prevNode;
		this.nextNode = nextNode;
		
		prevNode.setNextNode(this);
		nextNode.setPrevNode(this);
	}

	/**
	 * @return the nextNode
	 */
	public MyDLLNode<E> getNextNode() {
		return nextNode;
	}

	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(MyDLLNode<E> nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * @return the prevNode
	 */
	public MyDLLNode<E> getPrevNode() {
		return prevNode;
	}

	/**
	 * @param prevNode the prevNode to set
	 */
	public void setPrevNode(MyDLLNode<E> prevNode) {
		this.prevNode = prevNode;
	}

	/**
	 * @return the element
	 */
	public E getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		return this.getElement().toString();
	}
}
