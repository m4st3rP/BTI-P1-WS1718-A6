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
        assert -1 < position && position < getSize() : "Unsupported Argument";
        return iGetNodeNo(position).info;
    }

    public T getF() {
        return head.info;
    }

    public T getL() {
        return tail.info;
    }

    public T extractNo(int position) {
        assert -1 < position && position < getSize() : "Unsupported Argument";
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

    public boolean putNo(int no, T element) {
        assert 0 <= no && no <= getSize() : "Argument no is not in size";
        assert element != null : "Argument can't be null!";

        Node<T> newNode = new Node<>(element);
        Node<T> node = iGetNodeNo(no);

        if (no > size)
            return false;

        if (size == 0) {
            head = newNode;
            tail = head;
        } else if (no == 0) {
            node.previous = newNode;
            newNode.next = node;
            head = newNode;
        } else if (no == size) {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            node.previous.next = newNode;
            newNode.previous = node.previous;
            node.previous = newNode;
            newNode.next = node;
        }

        size++;
        return true;
    }

    // public boolean putNo(final int position, final T info) {
    // assert -1 < position && position <= getSize() && info != null : "Unsupported Argument(s)";
    //
    // final Node<T> newNode = new Node<T>(info);
    // final Node<T> node = iGetNodeNo(position);
    //
    // if (position != 0 && position != getSize() - 1) {
    // node.previous.next = newNode;
    // newNode.previous = node.previous;
    // node.previous = newNode;
    // newNode.next = node;
    // size++;
    // return true;
    // } else if (position == 0 && position != getSize()) {
    // newNode.next = node;
    // newNode.previous = null;
    // head.previous = newNode;
    // size++;
    // return true;
    // } else if (position != 0 && position == getSize()) {
    // newNode.next = null;
    // newNode.previous = tail;
    // tail.next = newNode;
    // size++;
    // return true;
    // } else if (position == 0 && position == getSize()) {
    // head = newNode;
    // tail = newNode;
    // size++;
    // return true;
    // } else {
    // return false;
    // }
    //
    // }

    // TODO Sonderfall beachten wenn Liste mit putF erstellt wird und dann mit putL von dieser Liste
    // neue generiert wird
    public void putF(T info) {
        assert null != info : "Unsupported Argument";

        final Node<T> newNode = new Node<T>(info);
        newNode.next = head;
        if (isEmpty()) {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    public void putL(T info) {
        assert null != info : "Unsupported Argument";

        final Node<T> newNode = new Node<T>(info);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // TODO Size und Ausnahmefälle
    public T setNo(int position, T info) {
        assert -1 < position && position <= getSize() && info != null : "Unsupported Argument";

        T oldInfo = null;
        if (getSize() != position) {
            oldInfo = iGetNodeNo(position).info;
            iGetNodeNo(position).info = info;
        } else {
            putL(info);
        }

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
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        if (head != null && tail != null) {
            return false;
        } else if (head == null && tail == null) {
            return true;
        } else {
            System.out.println("Serious error: Either head or tail are null but not both!");
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
        // assert null != info : "Unsupported Argument";
        return iSearchNode(info) != null;
    }

    // Helping Methods
    protected Node<T> iSearchNode(T info) {
        // assert null != info : "Unsupported Argument";
        if (info == null)
            return null;

        Node<T> work = head;
        while (work != null && !work.info.equals(info)) {
            work = work.next;
        }
        return work;
    }

    protected Node<T> iGetNodeNo(int position) {
        Node<T> work = head;

        if (work == null || size < position)
            return null;

        for (int i = 0; i < position; i++) {
            work = work.next;
        }

        return work;
    }

    protected boolean iRemoveNode(Node<T> node) {

        if (node == null)
            return false;

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
            tail.next = null;
            tail = node.previous;
            size--;
            return true;
        } else if (node.equals(head) && node.equals(tail)) {
            clear();
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
