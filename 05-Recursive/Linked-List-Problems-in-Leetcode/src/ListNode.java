public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array can not be empty.");
        }
//        ListNode curNode = this;
//        for (int i = 0; i < nums.length - 1; ++i) {
//            curNode.val = nums[i];
//            curNode.next = new ListNode(nums[i + 1]);
//            curNode = curNode.next;
//        }
        this.val = nums[0];
        ListNode curNode = this;
        for (int i = 1; i < nums.length; ++i) {
            curNode.next = new ListNode(nums[i]);
            curNode = curNode.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("front: ");
        ListNode curNode = this;
        while (curNode != null) {
            res.append(curNode.val).append("->");
            curNode = curNode.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
