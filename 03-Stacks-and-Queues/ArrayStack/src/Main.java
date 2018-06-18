public class Main {
    public static void main(String[] args) {
//        demo1();

//        demo2();

        int count = 100000;
        Queue<Integer> q1 = new ArrayQueue<>();
        double time1 = testQueue(q1, count);
        System.out.println("ArrayQueue, count = " + count + ", time:" + time1 + " s.");


        Queue<Integer> q2 = new LoopQueue<>();
        double time2 = testQueue(q2, count);
        System.out.println("LoopQueue, count = " + count + ", time:" + time2 + " s.");

    }

    private static double testQueue(Queue<Integer> queue, int count) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; ++i) {
            queue.enqueue(i);
        }
        for (int i = 0; i < count; ++i) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static void demo2() {
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
