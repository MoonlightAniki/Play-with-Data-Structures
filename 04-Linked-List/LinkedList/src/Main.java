public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 12; ++i) {
            if (i % 2 == 0) {
                linkedList.addLast(i);
            } else {
                linkedList.addFirst(i);
            }
            System.out.println(linkedList);
        }
    }
}
