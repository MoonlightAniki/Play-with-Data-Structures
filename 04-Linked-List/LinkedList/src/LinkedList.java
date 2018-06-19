public class LinkedList<E> {
    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        head = new Node(e, head);
        ++size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index. Require index >= 0 and index <= size.");
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        Node prev = head;
        // 找到index前一个节点
        for (int i = 0; i < index - 1; ++i) {
            prev = prev.next;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;

        prev.next = new Node(e, prev.next);
        ++size;
    }

    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList, size:")
                .append(size)
                .append('\n');
        Node curNode = head;
        while (curNode != null) {
            res.append(curNode.e);
            res.append("->");
            curNode = curNode.next;
        }
        res.append("null");
        return res.toString();
    }
}
