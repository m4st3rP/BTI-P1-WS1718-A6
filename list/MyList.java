package list;

public class MyList<T> {

	private Node<T> first;
	private Node<T> last;
	private int size;

	public MyList() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * 
	 * @param position the index of the element in the list to return
	 * @return data of element at position
	 */
	public T getNo(int position) {
		assert 0 <= position && position < getSize() : "Argument no is not in size";
		return iGetNodeNo(position).getData();
	}

	/**
	 * 
	 * @return the first element's data of the list. List shouldn't be empty.
	 */
	public T getF() {
	    assert size > 0 : "List is empty";
		return first.getData();
	}

	/**
	 * 
	 * @return the last element's data of the list. List shouldn't be empty.
	 */
	public T getL() {
	    assert size > 0 : "List is empty";
		return last.getData();
	}

	/**
	 * remove a specific element from the list
	 * @param position the position of the element which should be removed
	 * @return the removed element data
	 */
	public T extractNo(int position) {
		assert 0 <= position && position < getSize() : "Argument no is not in size";
		Node<T> nodeToExtract = iGetNodeNo(position);
		iRemoveNode(nodeToExtract);
		return nodeToExtract.getData();
	}

	/**
	 * remove the first element of the list and return it. 
	 * @return the first element data of the list
	 */
	public T extractF() {
		if (!isEmpty()) {
			T ret = first.getData();
			iRemoveNode(first);
			return ret;
		} else {
			return null;
		}
	}

	/**
	 * remove the last element of the list and return it
	 * @return the last element data of the list
	 */
	public T extractL() {
		if (!isEmpty()) {
			T ret = last.getData();
			iRemoveNode(last);
			return ret;
		} else {
			return null;
		}
	}

	/**
	 * add a new element to the list at a specific position in the list or at the end of the list. 
	 * @param position position where the new element should be added. Needs to be 0 <= position <= size
	 * @param element the element that should be added
	 * @return
	 */
	public boolean putNo(int position, T element) {
		assert 0 <= position && position <= getSize() : "Argument no is not in size";
		assert element != null : "Argument can't be null!";

		Node<T> newNode = new Node<>(element);
		Node<T> node = iGetNodeNo(position);

		if (position > size)
			return false;

		if (isEmpty()) {
			first = newNode;
			last = first;
		} else if (position == 0) { //need to set new first element
			node.previous = newNode;
			newNode.next = node;
			first = newNode;
		} else if (position == size) {	//need to set new last element
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
		} else {	//usual case, don't get the order of new assignments wrong here 
			node.previous.next = newNode;
			newNode.previous = node.previous;
			node.previous = newNode;
			newNode.next = node;
		}

		size++;
		return true;
	}

	/**
	 * add a new element to the begin of the list. The other elements are shifted back
	 * @param element the element that should be added
	 */
	public void putF(T element) {
		Node<T> firstNode = first;
		if (!isEmpty()) {
			Node<T> newFirstNode = new Node<>(element);
			firstNode.previous = newFirstNode;
			newFirstNode.next = firstNode;
			first = newFirstNode;
		} else {
			first = new Node<>(element);
			last = first;
		}

		size++;
	}

	/**
	 * add a new element to the end of the list
	 * @param element the element that should be added
	 */
	public void putL(T element) {
		Node<T> lastNode = last;
		if (!isEmpty()) {
			Node<T> newLastNode = new Node<>(element);
			lastNode.next = newLastNode;
			newLastNode.previous = lastNode;
			last = newLastNode;
		} else {
			last = new Node<>(element);
			first = last;
		}

		size++;
	}

	/**
	 * write new element-object into the list at a specific position and replace the element at this position.
	 * @param position position where the element should be written to with 0 <= pos < size
	 * @param element the element that should be written into the list
	 * @return the overwritten/replaced element's data
	 */
	public T setNo(int position, T element) {
		assert 0 <= position && position < getSize() : "Argument no is not in size";
		assert element != null : "Argument can't be null!";
		Node<T> node = iGetNodeNo(position);

		Node<T> newNode = new Node<>(element);
		newNode.next = node.next;

		if (node == first) {
			if (first.hasNext())
				first.next.previous = newNode;
			newNode.next = first.next;
			first = newNode;
		} else {
			newNode.previous = node.previous;
			node.previous.next = newNode;
			if (node.hasNext())
				node.next.previous = newNode;
		}

		return node.getData();
	}

	/**
	 * @param position position of the element which should be removed from the list
	 */
	public void removeNo(int position) {
	    assert 0 <= position && position < size : "Illegal Argument";
		iRemoveNode(iGetNodeNo(position));
	}

	/**
	 * removes first occurance of a specific element in the list
	 * @param element the element which should be removed from the list
	 * @return true if the element was removed, false if the element wasn't in the list
	 */
	public boolean remove(T element) {
		return iRemoveNode(iSearchNode(element));
	}

	/**
	 * clear the list and set the size to zero
	 */
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * 
	 * @return true if the list is empty, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return the size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param element
	 *            of type T to check
	 * @return true if element is in the list, false if not
	 */
	public boolean contains(T element) {
		assert element != null : "Argument can't be null!";
		return iSearchNode(element) != null;
	}

	// -----------------------------------------------------------------------------------------

	/**
	 * private helper methods
	 */

	private Node<T> iGetNodeNo(int position) {
		// assert 0 <= no && no < getSize() : "Argument no is not in size";
		Node<T> node = first;
		if (node == null || position >= size)
			return null;

		for (int i = 0; i < position; i++) {
			node = node.next;
		}

		return node;
	}

	private Node<T> iSearchNode(T element) {
		assert element != null : "Argument can't be null!";
		Node<T> node = first;
		while (node != null) {
			if (node.getData().equals(element))
				return node;
			node = node.next;
		}
		return node;
	}

	private boolean iRemoveNode(Node<T> node) {
		boolean removed = false;

		if (node == null)
			return removed;

		if (node != first && node != last) {
			node.previous.next = node.next;
			node.next.previous = node.previous;
			removed = true;
		}
		if (node == first) {
			first = node.next;
			if (first != null)
				first.previous = null;
			removed = true;
		}
		if (node == last) {
			last.next = null;
			last = node.previous;
			removed = true;
		}

		if (removed)
			size--;

		return removed;
	}

	// -----------------------------------------------------------------------------------------
}
