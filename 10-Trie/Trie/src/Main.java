import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Main {
    // 比较TreeSet/Trie:基于HashMap实现的Trie/Trie2:基于数组实现的Trie/HashSet的时间性能
    // 结论：HashSet>Trie2>Trie>TreeSet
    /*
    Total different words: 6530
    TreeSet time: 0.079614486s

    Total different words: 6530
    Trie time: 0.054582187s

    Total different words: 6530
    Trie2 time: 0.026133712s

    Total different words: 6530
    HashSet time: 0.025162518s
     */
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            // TreeSet--------------------------------------------------
            long startTime = System.nanoTime();
            Set<String> treeSet = new TreeSet<>();
            for (String word : words) {
                treeSet.add(word);
            }
            for (String word : words) {
                treeSet.contains(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + treeSet.size());
            System.out.println("TreeSet time: " + time + "s");

            // 基于HashMap实现的Trie---------------------------------------
            System.out.println();
            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie time: " + time + "s");

            // 基于数组实现的Trie-------------------------------------------
            System.out.println();
            startTime = System.nanoTime();
            Trie2 trie2 = new Trie2();
            for (String word : words) {
                trie2.add(word);
            }
            for (String word : words) {
                trie2.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + trie2.getSize());
            System.out.println("Trie2 time: " + time + "s");

            // HashSet ------------------------------------------------------
            System.out.println();
            startTime = System.nanoTime();
            Set<String> hashSet = new HashSet<>();
            for (String word : words) {
                hashSet.add(word);
            }
            for (String word : words) {
                hashSet.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + hashSet.size());
            System.out.println("HashSet time: " + time + "s");
        }
    }
}
