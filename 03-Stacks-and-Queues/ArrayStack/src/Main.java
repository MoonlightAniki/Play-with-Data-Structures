public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 31; ++i) {
            stack.push(i);
            System.out.println(stack);
        }

        for (int i = 0; i < 31; ++i) {
            System.out.println(stack.peek());
            System.out.println(stack);
            System.out.println(stack.pop());
            System.out.println(stack);
        }
    }
}
