import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
public class Solution102 {
    private interface Queue<E> {
        int getSize();

        boolean isEmpty();

        void enqueue(E e);

        E dequeue();

        E getFront();
    }

    private class LinkedListQueue<E> implements Queue<E> {
        private class Node {
            private E e;
            private Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node(E e) {
                this(e, null);
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return Objects.toString(e);
            }
        }

        private int size;
        private Node head;//头节点
        private Node tail;//尾节点

        public LinkedListQueue() {
            head = null;
            tail = null;
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
        public void enqueue(E e) {
            // 从链表尾部入队
            if (tail == null) {
                tail = new Node(e);
                head = tail;
            } else {
                tail.next = new Node(e);
                tail = tail.next;
            }
            ++size;
        }

        @Override
        public E dequeue() {
            if (size == 0) throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
            // 从链表头部出队
            Node delNode = head;
            head = head.next;
            delNode.next = null;
            if (head == null) tail = null;// 将链表中最后一个元素删除后，需要维护一下tail
            --size;
            return delNode.e;
        }

        @Override
        public E getFront() {
            return head.e;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("LinkedListQueue, size:")
                    .append(size)
                    .append('\n')
                    .append("front [");
            Node cur = head;
            while (cur != null) {
                res.append(cur.e);
                if (cur != tail) {
                    res.append(", ");
                }
                cur = cur.next;
            }
            res.append("] tail");
            return res.toString();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedListQueue<>();
        queue.enqueue(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> front = queue.dequeue();
            TreeNode node = front.getKey();
            Integer level = front.getValue();
            if (res.size() == level) res.add(new ArrayList<>());
            assert res.size() > level;
            res.get(level).add(node.val);
            if (node.left != null) queue.enqueue(new Pair<>(node.left, level + 1));
            if (node.right != null) queue.enqueue(new Pair<>(node.right, level + 1));
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
