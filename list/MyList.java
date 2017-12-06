package list;

public class MyList<T> {
    // Attributes
    private Node<T> head;
    private Node<T> tail;

    // Constructor
    public MyList() {
        this.head = null;
        this.tail = null;
    }

    // Methods
    public T getNo(int position) {
        // assert position > -1 && position <= getSize() : "Unsupported Argument";
        return iGetNodeNo(position).info;
    }

    public T getF() {
        return head.info;
    }

    public T getL() {
        return tail.info;
    }

    public T extractNo(int position) {
        // assert position > -1 && position <= getSize() : "Unsupported Argument";
        T info = iGetNodeNo(position).info;
        iRemoveNode(iGetNodeNo(position));
        return info;
    }

    public T extractF() {
        T oldHead = head.info;
        iRemoveNode(head);
        return oldHead;
    }

    public T extractL() {
        T oldTail = tail.info;
        iRemoveNode(tail);
        return oldTail;
    }

    // TODO Ausnahmefälle
    public boolean putNo(int position, T info) {
        assert position > -1 && position <= getSize() + 1 : "Unsupported Argument";

        final Node<T> newNode = new Node<T>(info);
        iGetNodeNo(position).previous.next = newNode;
        iGetNodeNo(position).previous = newNode;
        return true;
    }

    public void putF(T info) {
        assert null != info : "Unsupported Argument";
        final Node<T> newNode = new Node<T>(info);
        newNode.next = head;
        newNode.previous = null;
        if (head == null) {
            tail = newNode;
        }
        head = newNode;
    }

    public void putL(T info) {
        assert null != info : "Unsupported Argument";
        final Node<T> newNode = new Node<T>(info);
        if (head == null) {
            head = newNode;
        } else {
            newNode.previous = tail;
            newNode.next = null;
            tail.next = newNode;
        }
        tail = newNode;
    }

    public T setNo(int position, T info) {
        assert position > -1 && position <= getSize() : "Unsupported Argument";

        T oldObject = iGetNodeNo(position).info;
        iGetNodeNo(position).info = info;

        return oldObject;
    }

    public void removeNo(int position) {
        iRemoveNode(iGetNodeNo(position));
    }

    public boolean remove(T info) {
        return iRemoveNode(iSearchNode(info));
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return !head.equals(null);
    }

    public int getSize() {
        int counter = 0;
        Node<T> work = head;
        while (!work.equals(null)) {
            work = work.next;
            counter++;
        }
        return counter;
    }

    public boolean contains(T info) {
        assert null != info : "Unsupported Argument";
        return !iSearchNode(info).equals(null);
    }

    // Helping Methods
    protected Node<T> iSearchNode(T info) {
        assert null != info : "Unsupported Argument";
        Node<T> work = head;
        while (work != null && !work.info.equals(info)) {
            work = work.next;
        }
        return work;
    }

    protected Node<T> iGetNodeNo(int position) {
        assert position > -1 && position <= getSize() : "Unsupported Argument";
        Node<T> work = head;
        for (int i = 0; i <= position; i++) {
            work = work.next;
        }
        return work;
    }

    protected boolean iRemoveNode(Node<T> node) {
        assert node != null : "Unsupporterd Argument";
        if (!node.equals(head) && !node.equals(tail)) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            return true;
        } else if (node.equals(head) && !node.equals(tail)) {
            head = node.next;
            node.next.previous = null;
            return true;
        } else if (!node.equals(head) && node.equals(tail)) {
            tail = node.previous;
            node.previous.next = null;
            return true;
        } else {
            return false;
        }
    }


    public void printElemF2L() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println(getNo(i));
        }
    }

    public void printElemL2F() {
        for (int i = getSize() - 1; i > -1; i--) {
            System.out.println(getNo(i));
        }
    }

    protected void printNodeF2L() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println("Info: " + iGetNodeNo(i).info + " Previous: " + iGetNodeNo(i).previous + " Next: " + iGetNodeNo(i).next);
        }
    }

    protected void printNodeL2F() {
        for (int i = getSize() - 1; i > -1; i--) {
            System.out.println("Info: " + iGetNodeNo(i).info + " Previous: " + iGetNodeNo(i).previous + " Next: " + iGetNodeNo(i).next);
        }
    }
}
