public class Main {
    public static void main(String[] args) {
//        demo1();
//        demo2();

//        demo3();
        int count = 1000000;
        Queue<Integer> loopQueue = new LoopQueue<>();
        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        testQueue(loopQueue, count);
        testQueue(linkedListQueue, count);
    }

    // 循环队列与链表队列的性能对比
    private static void testQueue(Queue<Integer> queue, int count) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("%s, count:%s, time:%s s.",
                queue.getClass().getSimpleName(),
                String.valueOf(count),
                String.valueOf((endTime - startTime) / 1000000000.0)));
    }

    private static void demo3() {
        int count = 10000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();

        testStack(arrayStack, count);
        testStack(linkedListStack, count);
    }

    // 动态数组栈与链表栈的性能对比
    private static void testStack(Stack<Integer> stack, int count) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            stack.push(i);
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("%s, count:%s, time:%s s.",
                stack.getClass().getSimpleName(),
                String.valueOf(count),
                String.valueOf((endTime - startTime) / 1000000000.0)));
    }

    private static void demo2() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 4; ++i) {
            linkedList.addLast(i);
            linkedList.addLast(i);
            linkedList.addLast(i);
            System.out.println(linkedList);
        }

        for (int i = 0; i < 4; ++i) {
            System.out.println(linkedList.removeAll(i));
            System.out.println(linkedList);
        }
    }

    private static void demo1() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 20; ++i) {
            if (i % 2 == 0) {
                linkedList.addLast(i);
            } else {
                linkedList.addFirst(i);
            }
            System.out.println(linkedList);
        }

        for (int i = 0; i < 20; ++i) {
            System.out.println(linkedList.get(i));
        }

        for (int i = 0; i < 20; ++i) {
            linkedList.set(i, i);
            System.out.println(linkedList);
        }

//        for (int i = 10; i < 30; ++i) {
//            System.out.println(linkedList.contains(i));
//        }

//        for (int i = 0; i < 20; ++i) {
//            linkedList.removeLast();
//            System.out.println(linkedList);
//        }
        System.out.println("=========================================================");
        for (int i = 20; i >= 0; --i) {
            System.out.println(i);
            System.out.println(linkedList.removeAll(i));
            System.out.println(linkedList);
        }
    }
}
