package list;

class Node<T> {
	private T data;
	Node<T> next;
	Node<T> previous;

	public Node(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public boolean hasNext() {
		return next != null;
	}

	public boolean hasPrevious() {
		return previous != null;
	}
}
