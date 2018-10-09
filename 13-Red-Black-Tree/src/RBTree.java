/**
 * 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        } else {
            return node.color;
        }
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;//保持根节点为黑色
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
        } else {
            node.value = value;
        }

        // T1 < x < T2 < y < T3 < node < T4
        //
        //       node                      node
        //       /  \                      /  \                          y
        //      x   T4     左旋x          y    T4      右旋node         /  \         颜色翻转
        //     / \     ============>    / \       ==============>     x    node  ============>
        //    T1  y                    x   T3                        / \   /  \
        //       / \                  / \                           T1 T2 T3  T4
        //      T2 T3                T1 T2

        // 检查是否需要左旋
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 检查是否需要右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 检查是否需要翻转颜色
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
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
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node retNode = get(root, key);
        return retNode == null ? null : retNode.value;
    }

    // 在以node为根节点的二分搜索树中查找键等于key的节点
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

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maximum(node.right);
        }
    }

    public V remove(K key) {
        Node node = get(root, key);
        if (node != null) {
            root = remove(root, node.key);
        }
        return node == null ? null : node.value;
    }

    // 从以node为根节点的二分搜索树中删除键等于key的节点，并返回新的根节点
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
        } else {
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
            } else {
                Node successor = maximum(node.left);
                successor.left = remove(node.left, successor.key);
                successor.right = node.right;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        return retNode;
    }

    // T1 < y < T2 < x < T3 < node < T4
    //
    //        node(B)                                  x
    //        /  \                                    / \
    //       x(R) T4         右旋(node)              y   node
    //      / \        ===================>        / \   / \
    //    y(R) T3                                 T1 T2 T3 T4
    //    / \
    //   T1 T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // T1 < x < T2 < y < T3 < node < T4
    //
    //      node(B)                                    node(B)
    //      /   \                                      /   \
    //     x     T4          左旋(x)                  y     T4
    //    / \         ======================>       / \
    //   T1  y                                     x  T3
    //      / \                                   / \
    //     T2  T3                                T1 T2
    private Node leftRotate(Node x) {
        Node y = x.right;

        x.right = y.left;
        y.left = x;

        y.color = x.color;
        x.color = RED;

        return y;
    }

    // 颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}
