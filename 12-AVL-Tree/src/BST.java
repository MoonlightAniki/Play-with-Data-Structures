public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {// key.compareTo(node.key) == 0
            node.value = value;
        }
        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {// if (key.compareTo(node.key) > 0)
            return contains(node.right, key);
        }
    }

    public void set(K key, V value) {
        root = set(root, key, value);
    }

    private Node set(Node node, K key, V value) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = set(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = set(node.right, key, value);
        } else {// key.compare(node.key) == 0
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    public K maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).key;
    }

    private Node maximum(Node node) {
        if (node.right != null) {
            node = maximum(node.right);
        }
        return node;
    }

    public K minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).key;
    }

    private Node minimum(Node node) {
        if (node.left != null) {
            node = minimum(node.left);
        }
        return node;
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

    public V remove(K key) {
        V ret = get(key);
        root = remove(root, key);
        return ret;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {// key.compareTo(node.key) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                --size;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                --size;
                retNode = leftNode;
            } else {// node.left!=null&&node.right!=null
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.right = node.left = null;
                retNode = successor;
            }
        }
        return retNode;
    }
}
