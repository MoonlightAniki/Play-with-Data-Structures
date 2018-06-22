// 234. Palindrome Linked List
// https://leetcode.com/problems/palindrome-linked-list/description/
public class Solution234 {
    //version1
    //时间复杂度O(n)
    //空间复杂度O(n)
//    public boolean isPalindrome(ListNode head) {
//        List<Integer> values = new ArrayList<>();
//        ListNode cur = head;
//        while (cur != null) {
//            values.add(cur.val);
//            cur = cur.next;
//        }
//        int l = 0, r = values.size() - 1;
//        while (l < r) {
//            if (!values.get(l).equals(values.get(r))) {
//                return false;
//            } else {
//                ++l;
//                --r;
//            }
//        }
//        return true;
//    }

    //version2
//    public boolean isPalindrome(ListNode head) {
//        if (head == null) return true;
//        // detect the length
//        int len = 0;
//        for (ListNode p = head; p != null; p = p.next) len++;
//        // reverse the first half list
//        ListNode p = head, q = null, r = p.next;
//        for (int i = 0; i < len / 2; i++) {
//            p.next = q;
//            q = p;
//            p = r;
//            r = r.next;
//        }
//        // detect the palindrome from the mid
//        r = len % 2 == 0 ? p : r;
//        while (r != null && q != null) {
//            if (r.val != q.val) return false;
//            r = r.next;
//            q = q.next;
//        }
//        return r == null && q == null;
//    }


    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 获取链表的长度
        int len = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++len;
        }
        //将链表的前半部分反转
        ListNode prev = null;
        for (int i = 0; i < len / 2; ++i) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        ListNode leftHead = prev;//左半部分的头节点
        //获取右半部分的头节点
        ListNode rightHead = len % 2 == 0 ? head : head.next;

        //检测左右两部分是否相等
        while (leftHead != null && rightHead != null) {
            if (leftHead.val != rightHead.val) {
                return false;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{1, 2, 3, 2, 1});
        Solution234 s = new Solution234();
        System.out.println(s.isPalindrome(l1));

        ListNode l2 = new ListNode(new int[]{2});
        System.out.println(s.isPalindrome(null));
    }
}
