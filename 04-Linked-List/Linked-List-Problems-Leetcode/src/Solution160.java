// 160. Intersection of Two Linked Lists
// https://leetcode.com/problems/intersection-of-two-linked-lists/description/
public class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(new int[]{1, 2, 3, 4, 5});
        ListNode headB = new ListNode(new int[]{0, 2, 3, 3, 4, 5});
        Solution160 s = new Solution160();
        System.out.println(s.getIntersectionNode(headA, headB));
    }

//    public ListNode reverse(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode newHead = null;
//        while (head != null) {
//            ListNode temp = head.next;
//            head.next = newHead;
//            newHead = head;
//            head = temp;
//        }
//        return newHead;
//    }
//
//    public static void main(String[] args) {
//        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
//        Solution160 s = new Solution160();
//        System.out.println(s.reverse(head));
//    }

//    public boolean isSameLinkedList(ListNode headA, ListNode headB) {
//        if (headA == null && headB == null) {
//            return true;
//        } else if (headA == null) {
//            return false;
//        } else if (headB == null) {
//            return false;
//        } else {
//            return headA.val == headB.val && isSameLinkedList(headA.next, headB.next);
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] arr = new int[]{1, 2, 3, 4, 5};
//        int[] arr2 = new int[]{5, 4, 3, 2, 1};
//        ListNode headA = new ListNode(arr);
//        ListNode headB = new ListNode(arr);
//        Solution160 s = new Solution160();
//        System.out.println(s.isSameLinkedList(headA, headB));
//    }
}
