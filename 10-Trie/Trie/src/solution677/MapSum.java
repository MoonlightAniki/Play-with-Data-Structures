package solution677;

import java.util.HashMap;
import java.util.Map;

// 677. Map Sum Pairs
// https://leetcode.com/problems/map-sum-pairs/description/
class MapSum {

    private class Node {
        private int value;
        private Map<Character, Node> next;

        public Node(int value) {
            this.value = value;
            this.next = new HashMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) {
//                break;
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        int res = 0;
        res += node.value;
        for (char nextChar : node.next.keySet()) {
            res += sum(node.next.get(nextChar));
        }
        return res;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.insert("app", 2);
        mapSum.insert("panda", 100);
        mapSum.insert("pan", 1);

        System.out.println(mapSum.sum("ap"));
        System.out.println(mapSum.sum("pan"));
        System.out.println(mapSum.sum("dog"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
