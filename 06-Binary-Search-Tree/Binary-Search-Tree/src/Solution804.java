import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 804. Unique Morse Code Words
// https://leetcode.com/problems/unique-morse-code-words/description/
public class Solution804 {

    private static final String[] MORSE_CODES = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    // 使用HashSet解决
//    public int uniqueMorseRepresentations(String[] words) {
//        Set<String> set = new HashSet<>();
//        for (String word : words) {
//            set.add(wordToMorseCode(word));
//        }
//        return set.size();
//    }

    public int uniqueMorseRepresentations(String[] words) {
        BST<String> bst = new BST<>();
        for (String word : words) {
            bst.add(wordToMorseCode(word));
        }
        return bst.size();
    }

    private static String wordToMorseCode(String word) {
        StringBuilder res = new StringBuilder();
        for (int i = 0, length = word.length(); i < length; ++i) {
            res.append(MORSE_CODES[word.charAt(i) - 'a']);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution804 s = new Solution804();
        String[] words = {"gin", "zen", "gig", "msg"};
        int count = s.uniqueMorseRepresentations(words);
        System.out.println(count);
    }

    private class BST<E extends Comparable<E>> {

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
    }
}
