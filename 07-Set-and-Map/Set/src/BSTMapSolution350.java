import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 350. Intersection of Two Arrays II
// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
public class BSTMapSolution350 {
    public interface Map<K, V> {
        int getSize();

        boolean isEmpty();

        boolean contains(K key);

        void add(K key, V value);

        V remove(K key);

        void set(K key, V newValue);

        V get(K key);
    }

    public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
        private class Node {
            public K key;
            public V value;
            public Node left, right;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
                this.left = null;
                this.right = null;
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return Objects.toString(key) + ":" + Objects.toString(value);
            }
        }

        private Node root;
        private int size;

        public BSTMap() {
            root = null;
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
            return contains(root, key);
        }

        @Override
        public void add(K key, V value) {
            root = add(root, key, value);
        }

        @Override
        public V remove(K key) {
            V ret = get(key);
            root = remove(root, key);
            return ret;
        }

        @Override
        public void set(K key, V newValue) {
            Node node = get(root, key);
            if (node != null) {
                node.value = newValue;
            }
        }

        @Override
        public V get(K key) {
            Node node = get(root, key);
            return node == null ? null : node.value;
        }

        private boolean contains(Node node, K key) {
            if (node == null) {
                return false;
            }
            if (Objects.equals(key, node.key)) {
                return true;
            }
            if (key.compareTo(node.key) > 0) {
                return contains(node.right, key);
            } else {
                return contains(node.left, key);
            }
        }

        private Node add(Node node, K key, V value) {
            if (node == null) {
                ++size;
                return new Node(key, value);
            }
            if (key.compareTo(node.key) > 0) {
                node.right = add(node.right, key, value);
                return node;
            } else if (key.compareTo(node.key) < 0) {
                node.left = add(node.left, key, value);
                return node;
            } else {// key.compareTo(node.key) == 0
                node.value = value;
                return node;
            }
        }

        private Node get(Node node, K key) {
            if (node == null) {
                return null;
            }
            if (key.compareTo(node.key) > 0) {
                return get(node.right, key);
            } else if (key.compareTo(node.key) < 0) {
                return get(node.left, key);
            } else {// key.compareTo(node.key) == 0
                return node;
            }
        }

        private Node maximum(Node node) {
            if (node.right == null) {
                return node;
            } else {
                return maximum(node.right);
            }
        }

        private Node minimum(Node node) {
            if (node.left == null) {
                return node;
            } else {
                return minimum(node.left);
            }
        }

        private Node removeMax(Node node) {
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                --size;
                return leftNode;
            } else {
                node.right = removeMax(node.right);
                return node;
            }
        }

        private Node removeMin(Node node) {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                --size;
                return rightNode;
            } else {
                node.left = removeMin(node.left);
                return node;
            }
        }

        // 从以node为根节点的bst中删除键等于key的节点，返回删除节点后的新的根节点
        private Node remove(Node node, K key) {
            if (node == null) {
                return null;
            }
            if (key.compareTo(node.key) > 0) {
                node.right = remove(node.right, key);
                return node;
            } else if (key.compareTo(node.key) < 0) {
                node.left = remove(node.left, key);
                return node;
            } else {// key.compareTo(node.key) == 0
                if (node.left == null) {
                    Node rightNode = node.right;
                    node.right = null;
                    --size;
                    return rightNode;
                } else if (node.right == null) {
                    Node leftNode = node.left;
                    node.left = null;
                    --size;
                    return leftNode;
                } else {//delNode.left!=null && delNode.right!=null
                    Node successor = maximum(node.left);
                    successor.left = removeMax(node.left);
                    successor.right = node.right;
                    node.left = node.right = null;
                    return successor;
                }
            }
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new BSTMap<>();
        for (int i = 0; i < nums1.length; ++i) {
            int num = nums1[i];
            if (map.contains(num)) {
                map.add(num, map.get(num) + 1);
            } else {
                map.add(num, 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            int num = nums2[i];
            if (map.contains(num)) {
                list.add(num);
                map.add(num, map.get(num) - 1);
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
        BSTMapSolution350 s = new BSTMapSolution350();
        int[] result = s.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
