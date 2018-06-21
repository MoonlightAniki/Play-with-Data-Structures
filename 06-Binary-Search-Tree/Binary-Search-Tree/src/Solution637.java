import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 637. Average of Levels in Binary Tree
// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
public class Solution637 {

    // version1
//    public List<Double> averageOfLevels(TreeNode root) {
//        List<Double> res = new ArrayList<>();
//        if (root == null) {
//            return res;
//        }
//        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
//        queue.add(new Pair<>(root, 0));
//        double sum = 0.0;
//        int count = 0;
//        while (!queue.isEmpty()) {
//            Pair<TreeNode, Integer> front = queue.remove();
//            TreeNode node = front.getKey();
//            Integer level = front.getValue();
//            if (res.size() + 1 == level) {
//                res.add(sum / count);
//                sum = 0.0;
//                count = 0;
//            }
//            sum += node.val;
//            count++;
//            if (node.left != null) {
//                queue.add(new Pair<>(node.left, level + 1));
//            }
//            if (node.right != null) {
//                queue.add(new Pair<>(node.right, level + 1));
//            }
//        }
//        res.add(sum / count);
//        return res;
//    }


    // version2
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();//当前层的节点数
            long sum = 0;
            for (int i = 0; i < count; ++i) {
                TreeNode front = queue.poll();
                sum += front.val;
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            double average = 1.0 * sum / count;
            res.add(average);
        }
        return res;
    }

    /*
    Input:
        3
       / \
      9  20
        /  \
       15   7
    Output: [3, 14.5, 11]
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution637 s = new Solution637();
        System.out.println(s.averageOfLevels(root));
    }
}
