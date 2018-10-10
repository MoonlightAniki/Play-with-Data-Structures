import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            HashTable<String, Integer> ht = new HashTable<>();

            long startTime = System.nanoTime();
            for (String word : words) {
                if (ht.contains(word)) {
                    ht.set(word, ht.get(word) + 1);
                } else {
                    ht.add(word, 1);
                }
            }
            for (String word : words) {
                ht.contains(word);
            }
            long endTime = System.nanoTime();
            double time1 = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable, time1: " + time1 + " s.");
            System.out.println(ht.getSize());
            System.out.println(ht.get("pride"));
            System.out.println(ht.get("prejudice"));

            TreeMap<String, Integer> tm = new TreeMap<>();
            startTime = System.nanoTime();
            for (String word : words) {
                if (tm.containsKey(word)) {
                    tm.put(word, tm.get(word) + 1);
                } else {
                    tm.put(word, 1);
                }
            }
            for (String word : words) {
                tm.containsKey(word);
            }
            endTime = System.nanoTime();
            double time2 = (endTime - startTime) / 1000000000.0;
            System.out.println("TreeMap, time2: " + time2 + " s.");
            System.out.println(tm.size());
            System.out.println(tm.get("pride"));
            System.out.println(tm.get("prejudice"));
        } else {
            throw new RuntimeException("Read file failed.");
        }
    }
}
