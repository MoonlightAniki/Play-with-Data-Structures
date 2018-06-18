public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;//指向第一个元素
    private int tail;//指向最后一个元素的下一个位置
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 添加新元素之前先检查数组是否已满，如果满了的话将数组扩容至原来容量的2倍
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        ++size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        --size;

        // 删除完元素检查数组的容量，如果元素个数等于容量的1/4,将数组的容量减小为原来的1/2
        if (size == getCapacity() / 4 && getCapacity() / 2 > 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue, size:")
                .append(size)
                .append(", capacity:")
                .append(getCapacity())
                .append('\n')
                .append("front = ")
                .append(front)
                .append(" [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail = ").append(tail);
        return res.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 20; ++i) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                System.out.println(queue.dequeue());
                System.out.println(queue);
            }
        }

        System.out.println("======================================================");
        for (int i = 0; i < 20; ++i) {
            queue.dequeue();
            System.out.println(queue);
        }
    }

}
