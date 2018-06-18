public class Main {
    public static void main(String[] args) {
//        demo1();

        Queue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 17; ++i) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

//        for (int i = 0; i < 17; ++i) {
//            System.out.println(queue.getFront());
//            System.out.println(queue.dequeue());
//            System.out.println(queue);
//        }
    }

    private static void demo1() {
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
