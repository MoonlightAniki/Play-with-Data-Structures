import java.util.Objects;

public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

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
            return Objects.toString(e);
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public LinkedList(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Arr cannot be empty.");
        }
        dummyHead = new Node();
        size = arr.length;
        Node prev = dummyHead;
        for (int i = 0; i < size; ++i) {
            Node node = new Node(arr[i]);
            prev.next = node;
            prev = node;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index <= size.");
        }
        // 找到index上一个节点
        Node prev = dummyHead;
        for (int i = 0; i < index; ++i) {
            prev = prev.next;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        // 等价于上面3行代码
        prev.next = new Node(e, prev.next);
        ++size;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; ++i) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        --size;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(E e) {
        for (Node prev = dummyHead; prev != null && prev.next != null; prev = prev.next) {
            if (Objects.equals(e, prev.next.e)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                --size;
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(E e) {
        boolean flag = false;
        for (Node prev = dummyHead; prev != null && prev.next != null; ) {
            if (Objects.equals(e, prev.next.e)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                --size;
                flag = true;
            } else {
                prev = prev.next;
            }
        }
        return flag;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (Objects.equals(e, cur.e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList, size:")
                .append(size)
                .append('\n');
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur.e).append("->");
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            list.addLast(i);
        }
        for (int i = 9; i >= 0; --i) {
            list.addLast(i);
        }
        System.out.println(list);
        for (int i = 9; i >= 0; --i) {
            System.out.println(list.removeAll(i));
            System.out.println(list);
        }

//        for (int i = 0; i < 10; ++i) {
//            System.out.println(list.removeLast());
//            System.out.println(list);
//        }
    }
}
