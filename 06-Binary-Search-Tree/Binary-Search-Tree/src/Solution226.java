// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/description/
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = invertTree(rightNode);
        root.right = invertTree(leftNode);
        return root;
    }
}
