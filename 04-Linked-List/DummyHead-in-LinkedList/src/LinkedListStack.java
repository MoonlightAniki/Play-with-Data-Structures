public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> data;

    public LinkedListStack() {
        data = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedListStack, size:")
                .append(getSize())
                .append('\n')
                .append("top [");
        for (int i = 0, size = getSize(); i < size; ++i) {
            res.append(data.get(i));
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 10; ++i) {
            stack.push(i);
            System.out.println(stack);
            if (i % 3 == 2) {
                System.out.println(stack.pop());
                System.out.println(stack);
            }
        }
    }
}
