import java.util.Objects;
import java.util.Random;

public class LinkedListMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return Objects.toString(key) + ":" + Objects.toString(value);
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(K key) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (Objects.equals(key, cur.key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(K key, V value) {
        if (!contains(key)) {
            Node node = new Node(key, value);
            node.next = dummyHead.next;
            dummyHead.next = node;
            ++size;
        }
    }

    @Override
    public V remove(K key) {
        for (Node prev = dummyHead; prev != null && prev.next != null; prev = prev.next) {
            if (Objects.equals(key, prev.next.key)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                --size;
                return delNode.value;
            }
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (Objects.equals(key, cur.key)) {
                cur.value = newValue;
                break;
            }
        }
    }

    @Override
    public V get(K key) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (Objects.equals(key, cur.key)) {
                return cur.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedListMap, size:")
                .append(size)
                .append('\n');
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur).append("->");
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedListMap<>();
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            int key = random.nextInt(256);
            map.add(key, toHexString(key));
            System.out.println(map);
        }
    }

    private static String toHexString(int i) {
        return "0X" + Integer.toHexString(i).toUpperCase();
    }
}
