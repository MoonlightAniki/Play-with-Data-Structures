// 404. Sum of Left Leaves
// https://leetcode.com/problems/sum-of-left-leaves/description/
public class Solution404 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = sumOfLeftLeaves(root.right);
        TreeNode leftNode = root.left;
        if (leftNode != null) {
            if (leftNode.left == null && leftNode.right == null) {
                sum += leftNode.val;
            } else {
                sum += sumOfLeftLeaves(leftNode);
            }
        }
        return sum;
    }

    public int sumOfRightLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = sumOfRightLeaves(root.left);
        TreeNode rightNode = root.right;
        if (rightNode != null) {
            if (rightNode.left == null && rightNode.right == null) {//rightNode是叶子节点-->rightNode是右叶子
                sum += rightNode.val;
            } else {
                sum += sumOfRightLeaves(rightNode);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        /*
            3
           / \
          9  20
            /  \
           15   7

        There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution404 s = new Solution404();
        System.out.println(s.sumOfLeftLeaves(root));
        System.out.println(s.sumOfRightLeaves(root));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right = new TreeNode(3);
        System.out.println(s.sumOfLeftLeaves(root2));
        System.out.println(s.sumOfRightLeaves(root2));
    }
}
