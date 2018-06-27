import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LinkedListSetSolution349 {
    private interface Set<E> {
        int getSize();

        boolean isEmpty();

        boolean contains(E e);

        void add(E e);

        void remove(E e);
    }

    private class LinkedListSet<E> implements Set<E> {

        private LinkedList<E> list;

        public LinkedListSet() {
            list = new LinkedList<>();
        }

        @Override
        public int getSize() {
            return list.getSize();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public boolean contains(E e) {
            return list.contains(e);
        }

        @Override
        public void add(E e) {
            if (!list.contains(e)) {
                list.addFirst(e);
            }
        }

        @Override
        public void remove(E e) {
            list.removeElement(e);
        }
    }

    private class LinkedList<E> {
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
    }

    // version2:使用自己实现的LinkedListSet解决
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Set<Integer> set = new LinkedListSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            set.add(nums1[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (set.contains(nums2[i])) {
                list.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }
}
