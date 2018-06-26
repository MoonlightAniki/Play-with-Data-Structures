import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BST<E extends Comparable<E>> {
    private class Node {
        public final E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return Objects.toString(e);
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

    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根节点的bst中添加元素e，返回添加添加完成后新的根节点
    private Node add(Node node, E e) {
        if (node == null) {
            ++size;
            return new Node(e);
        }
        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }
        return node;
    }

    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maximum(node.right);
        }
    }

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
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

    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
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

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else {// e.compareTo(node.e) == 0
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
            } else {// node.left!=null && node.right!=null
                Node successor = maximum(node.left);
                node.left = removeMax(node.left);
                successor.left = node.left;
                successor.right = node.right;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (Objects.equals(e, node.e)) {
            return true;
        }
        if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.println(front.e);
            if (front.left != null) {
                queue.offer(front.left);
            }
            if (front.right != null) {
                queue.offer(front.right);
            }
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.remove(4);
        bst.levelOrder();
    }
}
