import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class BSTSetSolution349 {
    private interface Set<E> {
        int getSize();

        boolean isEmpty();

        boolean contains(E e);

        void add(E e);

        void remove(E e);
    }

    private class BST<E extends Comparable<E>> {
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
            Queue<Node> queue = new java.util.LinkedList<>();
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
    }

    private class BSTSet<E extends Comparable<E>> implements Set<E> {
        private BST<E> bst;

        public BSTSet() {
            bst = new BST<E>();
        }

        @Override
        public int getSize() {
            return bst.getSize();
        }

        @Override
        public boolean isEmpty() {
            return bst.isEmpty();
        }

        @Override
        public boolean contains(E e) {
            return bst.contains(e);
        }

        @Override
        public void add(E e) {
            bst.add(e);
        }

        @Override
        public void remove(E e) {
            bst.remove(e);
        }
    }


    // version3:使用自己实现的BSTSet解决
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Set<Integer> set = new BSTSet<>();
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
