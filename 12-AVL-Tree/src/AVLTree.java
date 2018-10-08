import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int height;//当前节点的高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 0; i + 1 < keys.size(); ++i) {
            if (keys.get(i).compareTo(keys.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
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
        } else {
            node.value = value;
        }

        // 回溯过程
        // 更新当前节点的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算当前节点的平衡因子
//        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced : " + balanceFactor);
//        }
        // LL
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR
        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) < 0) {
            //            y                              y                                    z
            //           / \                            / \                                 /  \
            //          x   T4      左旋(x)            z   T4          右旋(y)             x     y
            //         / \      ===============>     / \        =================>       / \   / \
            //        T1  z                         x   T3                              T1 T2 T3 T4
            //           / \                       / \
            //          T2 T3                     T1 T2
            // T1 < x < T2 < z < T3 < y < T4
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //            y
    //           / \
    //          x   T4                          x
    //         / \          右旋(y)            /  \
    //        z   T3    ============>        z     y
    //       / \                            / \   / \
    //      T1  T2                         T1 T2 T3  T4
    // T1 < z < T2 < x < T3 < y < T4
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        // 旋转结束后更新高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //       y                                    x
    //      / \                                  / \
    //     T1  x         左旋(y)                y    z
    //        / \  ===================>       / \   / \
    //       T2  z                           T1 T2 T3 T4
    //          / \
    //         T3  T4
    // T1 < y < T2 < x < T3 < z < T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        // 旋转结束后更新高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
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
        // 回溯过程中维护树的平衡
        if (retNode == null) {
            return null;
        }
        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // LL
        if (getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // LR
        if (getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RR
        if (getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // RL
        if (getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
