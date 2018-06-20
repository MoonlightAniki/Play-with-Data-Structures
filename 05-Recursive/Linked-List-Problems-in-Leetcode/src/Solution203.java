// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/description/
public class Solution203 {
//    public ListNode removeElements(ListNode head, int val) {
//        // 头节点为空
//        if (head == null) return null;
//
//        // 如果头节点的值等于val
//        while (head != null && head.val == val) {
//            head = head.next;
//        }
//        if (head == null) return null;
//
//        ListNode prev = head;//此时head.val一定不等于val
//        while (prev != null && prev.next != null) {
//            if (prev.next.val == val) {
//                ListNode delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
//                // 删除完一个节点后不需要将prev向后移动一位
//            } else {
//                prev = prev.next;
//            }
//        }
//        return head;
//    }

    // 使用虚拟头节点
//    public ListNode removeElements(ListNode head, int val) {
//        ListNode dummyHead = new ListNode(-1);
//        dummyHead.next = head;
//        ListNode prev = dummyHead;
//        while (prev != null && prev.next != null) {
//            if (prev.next.val == val) {
//                ListNode delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
//            } else {
//                prev = prev.next;
//            }
//        }
//        return dummyHead.next;
//    }

    // 使用递归解决
//    public ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return head;
//        }
//        if (head.val == val) {
//            head = removeElements(head.next, val);
//        } else {
//            head.next = removeElements(head.next, val);
//        }
//        return head;
//    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        Solution203 solution = new Solution203();
        head = solution.removeElements(head, 6);
        System.out.println(head);

        nums = new int[]{6, 6, 6, 1, 2, 3, 6, 6, 4, 5, 6, 6};
        head = new ListNode(nums);
        head = solution.removeElements(head, 6);
        System.out.println(head);
    }
}
