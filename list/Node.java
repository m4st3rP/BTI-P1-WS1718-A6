package list;

public class Node<T> {
    T info;
    Node<T> next;
    Node<T> previous;


    public Node(T info) {
        this.info = info;
        this.next = null;
        this.previous = null;
    }
}
