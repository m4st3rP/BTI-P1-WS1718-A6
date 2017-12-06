package list;

public class Node<Data> {
    private Data info;
    private Node<Data> next;
    private Node<Data> previous;

    
    public Node(Data info) {
        this.info = info;
        this.next = null;
    }
}
