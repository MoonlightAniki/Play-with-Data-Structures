import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            BST<String, Integer> bst = new BST<>();

            long startTime = System.nanoTime();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                } else {
                    bst.add(word, 1);
                }
            }
            long endTime = System.nanoTime();
            double time1 = (endTime - startTime) / 1000000000.0;
            System.out.println("BST, time1: " + time1 + " s");

            RBTree<String, Integer> rbt = new RBTree<>();
            startTime = System.nanoTime();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                } else {
                    rbt.add(word, 1);
                }
            }
            endTime = System.nanoTime();
            double time2 = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree, time2: " + time2 + " s");
        } else {
            System.out.println("readFile failed.");
        }
    }
}
