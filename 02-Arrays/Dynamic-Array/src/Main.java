public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 31; ++i) {
            arr.addLast(i);
            System.out.println(arr);
        }

        for (int i = 0; i < 31; ++i) {
            System.out.println(arr.removeFirst());
            System.out.println(arr);
        }

        for (int i = 0; i < 31; ++i) {
            arr.addFirst(i);
            System.out.println(arr);
        }

    }
}
