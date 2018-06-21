// 606. Construct String from Binary Tree
// https://leetcode.com/problems/construct-string-from-binary-tree/description/
public class Solution606 {
    // version1
    public String tree2str(TreeNode t) {
        StringBuilder res = new StringBuilder();
        preorderTraversal(t, res);
        return res.toString();
    }

    private void preorderTraversal(TreeNode node, StringBuilder res) {
        if (node == null) {
            return;
        }
//        res.append('(').append(node.val);
//        if (node.left != null && node.right != null) {
//            preorderTraversal(node.left, res);
//            preorderTraversal(node.right, res);
//        } else if (node.left != null) {// node.right == null
//            preorderTraversal(node.left, res);
//        } else if (node.right != null) {// node.left == null
//            res.append("()");
//            preorderTraversal(node.right, res);
//        }
//        res.append(')');
        res.append(node.val);
        if (node.left != null && node.right != null) {
            res.append('(');
            preorderTraversal(node.left, res);
            res.append(')');
            res.append('(');
            preorderTraversal(node.right, res);
            res.append(')');
        } else if (node.right != null) { // && node.left == null
            res.append("()");
            res.append('(');
            preorderTraversal(node.right, res);
            res.append(')');
        } else if (node.left != null) { // && node.right == null
            res.append('(');
            preorderTraversal(node.left, res);
            res.append(')');
        }
    }

    // version2
//    public String tree2str(TreeNode t) {
//        StringBuilder builder = new StringBuilder();
//        helper(t, builder);
//        return builder.toString();
//    }
//
//    private void helper(TreeNode root, StringBuilder builder){
//        if(root == null){
//            return;
//        }
//        builder.append(root.val);
//        if(root.left != null || root.right != null){
//            builder.append("(");
//            helper(root.left, builder);
//            builder.append(")");
//            if(root.right != null){
//                builder.append("(");
//                helper(root.right, builder);
//                builder.append(")");
//            }
//        }
//    }

    public static void main(String[] args) {
        /*
        Input: Binary tree: [1,2,3,4]
               1
             /   \
            2     3
           /
          4

        Output: "1(2(4))(3)"
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        Solution606 s = new Solution606();
        System.out.println(s.tree2str(root));
        System.out.println(s.tree2str(root).equals("1(2(4))(3)"));


        /*
        Input: Binary tree: [1,2,3,null,4]
               1
             /   \
            2     3
             \
              4

        Output: "1(2()(4))(3)"
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right = new TreeNode(3);
        System.out.println(s.tree2str(root2).equals("1(2()(4))(3)"));
    }
}
