// 21. Merge Two Sorted Lists
// https://leetcode.com/problems/merge-two-sorted-lists/description/
public class Solution21 {
    // version1:非递归实现
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode head = null;
//        ListNode cur1 = l1;
//        ListNode cur2 = l2;
//        ListNode cur = null;
//
//        while (true) {
//            if (cur1 == null && cur2 == null) {
//                break;
//            }
//            if (cur1 == null) {
//                if (head == null) {
//                    head = new ListNode(cur2.val);
//                    cur = head;
//                } else {
//                    cur.next = new ListNode(cur2.val);
//                    cur = cur.next;
//                }
//                cur2 = cur2.next;
//            } else if (cur2 == null) {
//                if (head == null) {
//                    head = new ListNode(cur1.val);
//                    cur = head;
//                } else {
//                    cur.next = new ListNode(cur1.val);
//                    cur = cur.next;
//                }
//                cur1 = cur1.next;
//            } else if (cur1.val <= cur2.val) {
//                if (head == null) {
//                    head = new ListNode(cur1.val);
//                    cur = head;
//                } else {
//                    cur.next = new ListNode(cur1.val);
//                    cur = cur.next;
//                }
//                cur1 = cur1.next;
//            } else {// cur1.val > cur2.val
//                if (head == null) {
//                    head = new ListNode(cur2.val);
//                    cur = head;
//                } else {
//                    cur.next = new ListNode(cur2.val);
//                    cur = cur.next;
//                }
//                cur2 = cur2.next;
//            }
//        }
//        return head;
//    }


    // version2:递归实现
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {// l1 != null && l2 != null
            if (l1.val <= l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {// l1.val > l2.val
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{5});
        System.out.println(l1);
        ListNode l2 = new ListNode(new int[]{1, 2, 4});
        System.out.println(l2);
        Solution21 s = new Solution21();
        System.out.println(s.mergeTwoLists(l1, l2));
    }
}
