import java.util.HashSet;
import java.util.Set;

// 83. Remove Duplicates from Sorted List
// https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        Set<Integer> record = new HashSet<>();
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (record.contains(prev.next.val)) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                record.add(prev.next.val);
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 1, 2, 3, 3});
        Solution83 solution83 = new Solution83();
        System.out.println(solution83.deleteDuplicates(head));
    }
}
