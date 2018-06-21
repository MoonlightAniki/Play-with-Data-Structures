// 538. Convert BST to Greater Tree
// https://leetcode.com/problems/convert-bst-to-greater-tree/description/
public class Solution535 {
    // version1
//    public TreeNode convertBST(TreeNode root) {
//        List<Integer> nums = new ArrayList<>();
//        if (root == null) {
//            return null;
//        }
//        inorderTraversal(root, nums);
//        Map<Integer, Integer> map = new HashMap<>();
//        fillMap(nums, map);
//        inorderTraversal(root, map);
//        return root;
//    }
//
//    private void inorderTraversal(TreeNode root, Map<Integer, Integer> map) {
//        if (root == null) {
//            return;
//        }
//        inorderTraversal(root.left, map);
//        root.val = map.get(root.val);
//        inorderTraversal(root.right, map);
//    }
//
//    private void fillMap(List<Integer> nums, Map<Integer, Integer> map) {
//        int size = nums.size();
//        map.put(nums.get(size - 1), nums.get(size - 1));
//        for (int i = size - 2; i >= 0; --i) {
//            map.put(nums.get(i), map.get(nums.get(i + 1)) + nums.get(i));
//        }
//    }
//
//    private void inorderTraversal(TreeNode root, List<Integer> nums) {
//        if (root == null) {
//            return;
//        }
//        inorderTraversal(root.left, nums);
//        nums.add(root.val);
//        inorderTraversal(root.right, nums);
//    }

    // version2
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode cur) {
        if (cur == null) {
            return;
        }
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }

    public static void main(String[] args) {
        /*
        Input: The root of a Binary Search Tree like this:
                      5
                    /   \
                   2     13

        Output: The root of a Greater Tree like this:
                     18
                    /   \
                  20     13
                 */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);

        Solution535 s = new Solution535();
        TreeNode result = s.convertBST(root);
    }
}
