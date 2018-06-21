import java.util.LinkedList;
import java.util.Queue;

// 617. Merge Two Binary Trees
// https://leetcode.com/problems/merge-two-binary-trees/description/
public class Solution617 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {//t1!=null&&t2!=null
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right, t2.right);
            return node;
        }
    }

    /*
    Input:
       Tree 1                     Tree 2
             1                         2
            / \                       / \
           3   2                     1   3
          /                           \   \
         5                             4   7
   Output:
   Merged tree:
            3
           / \
          4   5
         / \   \
        5   4   7
     */
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.left.right = new TreeNode(4);
        t2.right = new TreeNode(3);
        t2.right.right = new TreeNode(7);

        Solution617 s = new Solution617();
        TreeNode result = s.mergeTrees(t1, t2);

        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        if (result == null) {
            return;
        }
        queue.add(result);
        while (!queue.isEmpty()) {
            TreeNode front = queue.remove();
            System.out.println(front.val);
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
    }
}
