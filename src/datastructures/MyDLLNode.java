package datastructures;

/**
 * node class for use in MyDLL and MyQueue
 * @param <E> generic type that the node will contain
 */
public class MyDLLNode<E> {
	
	private MyDLLNode<E> nextNode, prevNode;
	private E element;

	/**
	 * no arg constructor
	 */
	public MyDLLNode() {
	}

	/**
	 * constructor
	 * node will not point to another node
	 * @param element generic element the node will contain
	 */
	public MyDLLNode(E element) {
		this.element = element;
	}

	/**
	 * constructor
	 * node will point to previous and next nodes
	 * @param element generic element to node will contain
	 * @param prevNode pointer to previous node
	 * @param nextNode pointer to next node
	 */
	public MyDLLNode(E element, MyDLLNode<E> prevNode, MyDLLNode<E> nextNode) {
		this.element = element;
		this.prevNode = prevNode;
		this.nextNode = nextNode;
		
		prevNode.setNextNode(this);
		nextNode.setPrevNode(this);
	}

	/**
	 * Returns the next node in the list from the current node.
	 * 
	 * @return the nextNode
	 */
	public MyDLLNode<E> getNextNode() {
		return nextNode;
	}

	/**
	 * Sets the next node in the list from the current node. 
	 * 
	 * @param nextNode the next MyDLLNode to the current node.
	 */
	public void setNextNode(MyDLLNode<E> nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * Returns the previous node in the list from the current node.
	 * 
	 * @return the prevNode
	 */
	public MyDLLNode<E> getPrevNode() {
		return prevNode;
	}

	/**
	 * Sets the previous node in the list from the current node. 
	 * 
	 * @param prevNode the previous MyDLLNode to the current node.
	 */
	public void setPrevNode(MyDLLNode<E> prevNode) {
		this.prevNode = prevNode;
	}

	/**
	 * Returns the element contained in the MyDLLNode
	 * 
	 * @return the element
	 */
	public E getElement() {
		return element;
	}

	/**
	 * Sets the element to be contained in the MyDLLNode.
	 * 
	 * @param element the element to contained by the MyDLLNode
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		return this.getElement().toString();
	}
}
