import java.util.ArrayList;
import java.util.List;

// 653. Two Sum IV - Input is a BST
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
public class Solution653 {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorderTraversal(root, nums);
        if (nums.size() < 2) {
            return false;
        }
        int i = 0;
        int j = nums.size() - 1;
        while (i < j) {
            int sum = nums.get(i) + nums.get(j);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    private void inorderTraversal(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, nums);
        nums.add(root.val);
        inorderTraversal(root.right, nums);
    }
}
