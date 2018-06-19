import java.util.Objects;

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/description/
// 使用linkedListStack解决LeetCode第20号问题
public class Solution20 {

    private interface Stack<E> {
        int getSize();

        boolean isEmpty();

        void push(E e);

        E pop();

        E peek();
    }

    private class LinkedList<E> {
        private class Node {
            private E e;
            private Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node(E e) {
                this(e, null);
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return Objects.toString(e);
            }
        }

        private Node dummyHead;//虚拟头节点
        private int size;

        public LinkedList() {
            dummyHead = new Node();
            size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Invalid index. Require index >= 0 and index <= size.");
            }
            Node prev = dummyHead;
            for (int i = 0; i < index; ++i) {
                prev = prev.next;
            }
            prev.next = new Node(e, prev.next);
            ++size;
        }

        public void addFirst(E e) {
            add(0, e);
        }

        public void addLast(E e) {
            add(size, e);
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Invalid index. Require index >= 0 and index < size.");
            }
            Node curNode = dummyHead.next;
            for (int i = 0; i < index; ++i) {
                curNode = curNode.next;
            }
            return curNode.e;
        }

        public E getFirst() {
            return get(0);
        }

        public E getLast() {
            return get(size - 1);
        }

        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Invalid index. Require index >= 0 and index < size.");
            }
            Node curNode = dummyHead.next;
            for (int i = 0; i < index; ++i) {
                curNode = curNode.next;
            }
            curNode.e = e;
        }

        public boolean contains(E e) {
//        Node curNode = dummyHead.next;
//        for (int i = 0; i < size; ++i) {
//            if (Objects.equals(e, curNode.e)) {
//                return true;
//            }
//            curNode = curNode.next;
//        }
//        return false;

            for (Node curNode = dummyHead.next; curNode != null; curNode = curNode.next) {
                if (Objects.equals(e, curNode.e)) {
                    return true;
                }
            }
            return false;
        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Invalid index. Require index >= 0 and index < size.");
            }
            // 找到index前一个节点
            Node prev = dummyHead;
            for (int i = 0; i < index; ++i) {
                prev = prev.next;
            }
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            --size;
            return delNode.e;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        // 删除链表中第一个等于e的元素
        public boolean removeElement(E e) {
            for (Node prev = dummyHead; prev != null && prev.next != null; prev = prev.next) {
                if (Objects.equals(e, prev.next.e)) {
                    Node delNode = prev.next;
                    prev.next = delNode.next;
                    delNode.next = null;
                    --size;
                    return true;
                }
            }
            return false;
        }

        // 删除所有等于e的元素
        public boolean removeAll(E e) {
//        boolean flag = false;
//        for (Node prev = dummyHead; prev != null && prev.next != null; ) {
//            if (Objects.equals(e, prev.next.e)) {
//                Node delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
//                --size;
//                flag = true;
//            } else {
//                prev = prev.next;
//            }
//        }
//        return flag;

            boolean flag = false;
            Node prev = dummyHead;
            while (prev != null && prev.next != null) {
                if (Objects.equals(e, prev.next.e)) {
                    Node delNode = prev.next;
                    prev.next = delNode.next;
                    delNode.next = null;
                    --size;
                    flag = true;
                    // 删除一个元素后，prev不需要向后移动，因为下一个节点还未与e比较
                } else {
                    prev = prev.next;
                }
            }
            return flag;
        }

        @Override
        public String toString() {
//        StringBuilder res = new StringBuilder();
//        res.append("LinkedList, size:")
//                .append(size)
//                .append('\n');
//        Node curNode = dummyHead.next;
//        for (int i = 0; i < size; ++i) {
//            res.append(curNode.e).append("->");
//            curNode = curNode.next;
//        }
//        res.append("null");
//        return res.toString();

            StringBuilder res = new StringBuilder();
            res.append("LinkedList, size:")
                    .append(size)
                    .append('\n');
            Node curNode = dummyHead.next;
            while (curNode != null) {
                res.append(curNode.e).append("->");
                curNode = curNode.next;
            }
            res.append("null");
            return res.toString();
        }
    }

    private class LinkedListStack<E> implements Stack<E> {

        private LinkedList<E> data;

        public LinkedListStack() {
            data = new LinkedList<>();
        }

        @Override
        public int getSize() {
            return data.getSize();
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public void push(E e) {
            data.addFirst(e);
        }

        @Override
        public E pop() {
            return data.removeFirst();
        }

        @Override
        public E peek() {
            return data.getFirst();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("LinkedListStack, size:")
                    .append(getSize())
                    .append('\n')
                    .append("top [");
            for (int i = 0, size = getSize(); i < size; ++i) {
                res.append(data.get(i));
                if (i != size - 1) {
                    res.append(", ");
                }
            }
            res.append(']');
            return res.toString();
        }
    }

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) return false;
        Stack<Character> stack = new LinkedListStack<>();
        for (int i = 0, length = s.length(); i < length; ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (c != ')' && c != ']' && c != '}') return false;
                if (stack.isEmpty()) return false;
                Character top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        return stack.isEmpty();
    }

//    public static void main(String[] args) {
//        Solution20 s = new Solution20();
////        System.out.println(s.isValid("(((())"));
//        System.out.println(s.isValid("guoliang"));
//        System.out.println(s.isValid("[](){}"));
//    }
}
