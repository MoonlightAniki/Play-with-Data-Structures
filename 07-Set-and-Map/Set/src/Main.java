import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Set<String> set1 = new LinkedListSet<>();
        testSet(set1, "a-tale-of-two-cities.txt");

        System.out.println();
        Set<String> set2 = new BSTSet<>();
        testSet(set2, "a-tale-of-two-cities.txt");

        System.out.println();
        Set<String> set3 = new LinkedListSet<>();
        testSet(set3, "pride-and-prejudice.txt");

        System.out.println();
        Set<String> set4 = new BSTSet<>();
        testSet(set4, "pride-and-prejudice.txt");
    }

    private static void testSet(Set<String> set, String filename) {
        List<String> words = new ArrayList<>();
        FileOperation.readFile(filename, words);
        long startTime = System.nanoTime();
        for (String word : words) {
            set.add(word);
        }
        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime) / 1000000000.0;
        System.out.println(set.getClass().getSimpleName() + ":");
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + set.getSize());
        System.out.println("Total time: " + totalTime + "s");
    }
}
