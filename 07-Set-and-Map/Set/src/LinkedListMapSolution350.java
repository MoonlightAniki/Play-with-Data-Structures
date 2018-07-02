import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 350. Intersection of Two Arrays II
// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
public class LinkedListMapSolution350 {

    public interface Map<K, V> {
        int getSize();

        boolean isEmpty();

        boolean contains(K key);

        void add(K key, V value);

        V remove(K key);

        void set(K key, V newValue);

        V get(K key);
    }

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
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new LinkedListMap<>();
        for (int i = 0; i < nums1.length; ++i) {
            int num = nums1[i];
            if (map.contains(num)) {
                map.set(num, map.get(num) + 1);
            } else {
                map.add(num, 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            int num = nums2[i];
            if (map.contains(num)) {
                list.add(num);
                map.set(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1};
        int[] nums2 = {1};
        LinkedListMapSolution350 s = new LinkedListMapSolution350();
        int[] result = s.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
