import java.util.HashMap;
import java.util.Map;

// 基于HashMap实现的Trie
public class Trie {
    private class Node {
        boolean isWord;
        Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // 向Trie中添加单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 防止添加重复的单词时size++
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 返回Trie中是否包含某个单词
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                return false;
            }
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("panda");
        trie.add("cat");
        trie.add("dog");
        System.out.println(trie.isPrefix("pan"));
        System.out.println(trie.isPrefix("dear"));
    }
}
