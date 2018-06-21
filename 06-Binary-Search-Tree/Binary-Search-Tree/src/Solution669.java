import java.util.LinkedList;
import java.util.Queue;

// 669. Trim a Binary Search Tree
// https://leetcode.com/problems/trim-a-binary-search-tree/description/
public class Solution669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val == L) {
            root.right = trimBST(root.right, L, R);
            root.left = null;
            return root;
        } else if (root.val > L && root.val < R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        } else if (root.val == R) {
            root.left = trimBST(root.left, L, R);
            root.right = null;
            return root;
        } else {//root.val > R
            return trimBST(root.left, L, R);
        }
    }

    public static void main(String[] args) {
        /*
        Input:
            1
           / \
          0   2

          L = 1
          R = 2

        Output:
            1
              \
               2
        */
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(0);
//        root.right = new TreeNode(2);
//
//        Solution669 s = new Solution669();
//        TreeNode result = s.trimBST(root, 1, 2);


        /*
        Input:
            3
           / \
          0   4
           \
            2
           /
          1

          L = 1
          R = 3

        Output:
              3
             /
           2
          /
         1
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.right = new TreeNode(4);
        Solution669 s = new Solution669();
        TreeNode result = s.trimBST(root, 1, 3);

        if (result == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
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
