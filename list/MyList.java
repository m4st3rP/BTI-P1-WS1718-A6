package list;

public class MyList<T> {
    // Attributes
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Constructor
    public MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Methods
    public T getNo(int position) {
        assert position > -1 && position < getSize() : "Unsupported Argument";
        return iGetNodeNo(position).info;
    }

    public T getF() {
        return head.info;
    }

    public T getL() {
        return tail.info;
    }

    public T extractNo(int position) {
        assert position > -1 && position < getSize() : "Unsupported Argument";
        T info = iGetNodeNo(position).info;
        iRemoveNode(iGetNodeNo(position));
        return info;
    }

    public T extractF() {
        if (!isEmpty()) {
            T oldHead = head.info;
            iRemoveNode(head);
            return oldHead;
        } else {
            return null;
        }
    }

    public T extractL() {
        if (!isEmpty()) {
            T oldTail = tail.info;
            iRemoveNode(tail);
            return oldTail;
        } else {
            return null;
        }
    }

    // TODO Ausnahmefälle
    public boolean putNo(final int position, final T info) {
        assert position > -1 && position < getSize() + 1 && info != null : "Unsupported Argument";

        final Node<T> newNode = new Node<T>(info);
        newNode.next = iGetNodeNo(position);
        newNode.previous = iGetNodeNo(position).previous;
        newNode.previous.next = newNode;
        iGetNodeNo(position).previous = newNode;
        size++;
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
        size++;
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
        size++;
    }

    // TODO Size und Ausnahmefälle
    public T setNo(int position, T info) {
        assert position > -1 && position < getSize() && info != null : "Unsupported Argument";

        T oldInfo = iGetNodeNo(position).info;
        iGetNodeNo(position).info = info;

        return oldInfo;
    }

    public void removeNo(int position) {
        iRemoveNode(iGetNodeNo(position));
    }

    public boolean remove(T info) {
        return iRemoveNode(iSearchNode(info));
    }

    // TODO Evtl alle Verweise auch bei anderen Knoten auf null setzen?
    public void clear() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        if (getSize() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        // int counter = 0;
        // Node<T> work = head;
        // while (work != null) {
        // work = work.next;
        // counter++;
        // }
        // return counter;
        return size;
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
        assert position > -1 && position < getSize() : "Unsupported Argument";
        Node<T> work;

        // if (getSize() / 2 > position) {
        work = head;
        for (int i = 0; i < position; i++) {
            work = work.next;
        }
        // } else {
        // work = tail;
        // //TODO überprüfen ob das hier so stimmt
        // for (int i = getSize(); i > position; i--) {
        // work = work.previous;
        // }
        // }
        return work;
    }

    protected boolean iRemoveNode(Node<T> node) {
        assert node != null : "Unsupporterd Argument";
        if (!node.equals(head) && !node.equals(tail)) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            size--;
            return true;
        } else if (node.equals(head) && !node.equals(tail)) {
            head = node.next;
            head.previous = null;
            size--;
            return true;
        } else if (!node.equals(head) && node.equals(tail)) {
            tail = node.previous;
            tail.next = null;
            size--;
            return true;
        } else {
            return false;
        }
    }


    public void printElemF2L() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println(getNo(i));
        }
        System.out.println("Size: " + getSize());
    }

    public void printElemL2F() {
        for (int i = getSize() - 1; i > -1; i--) {
            System.out.println(getNo(i));
        }
        System.out.println("Size: " + getSize());
    }

    protected void printNodeF2L() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println("Info: " + iGetNodeNo(i).info + " Previous: " + iGetNodeNo(i).previous + " Next: " + iGetNodeNo(i).next);
        }
        System.out.println("Size: " + getSize());
    }

    protected void printNodeL2F() {
        for (int i = getSize() - 1; i > -1; i--) {
            System.out.println("Info: " + iGetNodeNo(i).info + " Previous: " + iGetNodeNo(i).previous + " Next: " + iGetNodeNo(i).next);
        }
        System.out.println("Size: " + getSize());
    }
}
