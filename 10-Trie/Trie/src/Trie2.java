// 基于数组实现的Trie
class Trie2 {
    private class Node {
        private boolean isWord;
        private Node[] next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new Node[26];
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie2() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node();
            }
            cur = cur.next[c - 'a'];
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie2 trie = new Trie2();
        trie.add("panda");
        trie.add("cat");
        trie.add("dog");
        System.out.println(trie.isPrefix("pan"));
        System.out.println(trie.isPrefix("dear"));
    }
}
