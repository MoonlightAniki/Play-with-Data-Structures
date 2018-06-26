import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

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

    public static void main(String[] args) {
//        System.out.println("Pride and Prejudice");
//
//        ArrayList<String> words = new ArrayList<>();
//        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
//            System.out.println("Total words: " + words.size());
//
//            BSTMap<String, Integer> map = new BSTMap<>();
//            for (String word : words) {
//                if (map.contains(word))
//                    map.set(word, map.get(word) + 1);
//                else
//                    map.add(word, 1);
//            }
//
//            System.out.println("Total different words: " + map.getSize());
//            System.out.println("Frequency of PRIDE: " + map.get("pride"));
//            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
//        }
//
//        System.out.println();

        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new BSTMap<>();
        Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            int num = random.nextInt(100);
            set.add(num);
            map.add(num, num);
            System.out.println(map.getSize());
        }
        System.out.println();
        for (Integer i : set) {
            map.remove(i);
            System.out.println(map.getSize());
        }
    }
}
