import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Pair<TreeNode, Integer>> queue = new LoopQueue<>();
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

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();//Pair的值表示当前处于第几层
//        q.add(new Pair<>(root, 0));
//        while (!q.isEmpty()) {
//            Pair<TreeNode, Integer> front = q.remove();
//            TreeNode node = front.getKey();
//            Integer level = front.getValue();
//            // 开始遍历下一层
//            if (level == res.size()) {
//                res.add(new ArrayList<>());
//            }
//            res.get(level).add(node.val);
//            if (node.left != null) {
//                q.add(new Pair<>(node.left, level + 1));
//            }
//            if (node.right != null) {
//                q.add(new Pair<>(node.right, level + 1));
//            }
//        }
//        return res;
//    }

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) {
//            return res;
//        }
//        Queue<DepthTreeNode> q = new LinkedList<>();
//        q.offer(new DepthTreeNode(root, 0));
//        int currentDepth = 0;//当前的深度
//        List<Integer> curList = new ArrayList<>();
//        while (!q.isEmpty()) {
//            DepthTreeNode front = q.poll();
//            TreeNode node = front.node;
//            int depth = front.depth;
//            // 当前深度不等于depth，说明开始遍历下一层
//            if (currentDepth != depth) {
//                res.add(curList);
//                curList = new ArrayList<>();
//                currentDepth = depth;
//            }
//            curList.add(node.val);
//            if (node.left != null) {
//                q.offer(new DepthTreeNode(node.left, depth + 1));
//            }
//            if (node.right != null) {
//                q.offer(new DepthTreeNode(node.right, depth + 1));
//            }
//        }
//        res.add(curList);
//        return res;
//    }

//    private class DepthTreeNode {
//        public Solution102.TreeNode node;
//        public int depth;
//
//        public DepthTreeNode(TreeNode node, int depth) {
//            this.node = node;
//            this.depth = depth;
//        }
//    }

    // Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        Solution102 s = new Solution102();
        List<List<Integer>> result = s.levelOrder(root);
        System.out.println(result);

        root = null;
        System.out.println(s.levelOrder(root));
    }
}
