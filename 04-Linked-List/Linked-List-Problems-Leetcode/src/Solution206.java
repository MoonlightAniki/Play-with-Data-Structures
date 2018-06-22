// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/description/
public class Solution206 {

    // version1:使用递归解决
//    public ListNode reverseList(ListNode head) {
//        // 如果链表为空或者只有一个节点直接返回
//        if (head == null || head.next == null) {
//            return head;
//        }
//        // 使用递归得到除头节点外的剩余节点的反转结果
//        ListNode newHead = reverseList(head.next);
//        // 找到剩余节点反转后的最后一个节点cur
//        ListNode cur = newHead;
//        while (cur.next != null) {
//            cur = cur.next;
//        }
//        // 将最后一个节点指向原来的头节点，并将原来的头结点的next指向null
//        cur.next = head;
//        head.next = null;
//        // 最后返回新的头节点
//        return newHead;
//    }

    // version2:非递归实现
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;//记录head的下一个节点
            head.next = prev;//将head指向新链表的头节点(并断开与temp的连接)
            prev = head;//将新链表的头节点更新为head
            head = temp;//将head指向原来head的下一个节点
        }
        return prev;//最终返回新链表的头节点
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9, 2};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        Solution206 s = new Solution206();
        System.out.println(s.reverseList(head));
    }
}
