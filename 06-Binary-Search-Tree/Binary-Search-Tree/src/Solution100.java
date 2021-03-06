// 100. Same Tree
// https://leetcode.com/problems/same-tree/description/
public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        } else if (p == null && q == null) {
            return true;
        } else {
            return false;
        }
    }
}
