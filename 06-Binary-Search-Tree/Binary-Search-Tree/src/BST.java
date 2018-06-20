import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    // 节点类
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;//根节点
    private int size;//节点个数

    // 构造函数
    public BST() {
        root = null;
        size = 0;
    }

    // 返回节点个数
    public int size() {
        return size;
    }

    // 返回BST是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 将元素e添加至BST中
    public void add(E e) {
        root = add(root, e);
    }

    // 将元素e添加至以node为跟节点的二分搜索树中，并返回添加完成后的根节点
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

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {// e.compareTo(node.e) < 0
            return contains(node.left, e);
        }
    }

    // 前序遍历
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

    // 前序遍历非递归实现
    public void preOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            System.out.println(top.e);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }

    // 中序遍历
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

    //后序遍历
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

    // 层序遍历
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

    // 返回二分搜索树中的最小值
    public E getMin() {
        return getMin(root).e;
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        } else {
            return getMin(node.left);
        }
    }

    // 获取最小值的非递归实现
    public E getMinNR() {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.e;
    }

    public E getMax() {
        return getMax(root).e;
    }

    private Node getMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        } else {
            return getMax(node.right);
        }
    }

    // 获取最大值的非递归实现
    public E getMaxNR() {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.e;
    }

    public E removeMin() {
        E ret = getMin();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            --size;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = getMax();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            --size;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
            } else { //node.left!=null&&node.right!=null
                // 将右子树中的最小值移动至当前要删除节点的位置
                // or
                // 将左子树中的最大值移动至当前要删除节点的位置
//                node.e = getMin(node.right).e;
//                node.right = removeMin(node.right);
//                return node;
                Node successor = getMin(node.right);
                successor.left = node.left;
                successor.right = removeMin(node.right);
                node.left = node.right = null;
                return successor;
            }
        }
    }

    // 层序遍历
    @Override
    public String toString() {
        List<List<E>> res = new ArrayList<>();
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("Binary Search Tree, size:");
        if (root == null) {
            return sb.append(size)
                    .append('\n')
                    .append(res.toString())
                    .toString();
        }
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<Node, Integer> front = queue.remove();
            Node node = front.getKey();
            Integer level = front.getValue();
            if (res.size() == level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.e);

            if (node.left != null) {
                queue.add(new Pair<>(node.left, level + 1));
            }

            if (node.right != null) {
                queue.add(new Pair<>(node.right, level + 1));
            }
        }
        return sb.append(size)
                .append('\n')
                .append(res.toString())
                .toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            int rand = random.nextInt(20);
            System.out.println(rand);
            bst.add(rand);
        }
        System.out.println(bst);

        System.out.println(bst.contains(5));

        System.out.println(bst.getMin().equals(bst.getMinNR()));
        System.out.println(bst.getMax().equals(bst.getMaxNR()));

        System.out.println(bst.removeMax());
        System.out.println(bst);

        bst.remove(10);
        System.out.println(bst);

//        System.out.println("preOrder:");
//        bst.preOrder();
//
//        System.out.println("preOrderNR:");
//        bst.preOrderNR();
//
//        System.out.println("inOrder:");
//        bst.inOrder();
//
//        System.out.println("postOrder:");
//        bst.postOrder();
//
//        System.out.println("levelOrder:");
//        bst.levelOrder();
    }
}
