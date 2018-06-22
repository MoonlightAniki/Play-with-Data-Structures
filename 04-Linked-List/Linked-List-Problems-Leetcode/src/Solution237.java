// 237. Delete Node in a Linked List
// https://leetcode.com/problems/delete-node-in-a-linked-list/description/
public class Solution237 {
    public void deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                return;
            }
            prev = prev.next;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4, 5, 1, 9};
        ListNode head1 = new ListNode(arr1);
        System.out.println(head1);
        Solution237 s = new Solution237();
        s.deleteNode(head1, 9);
        System.out.println(head1);
    }
}
