public class Array<E> {
    private int size;
    private E[] data;

    // 构造函数
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 构造函数
    public Array() {
        this(10);
    }

    // 返回数组中元素的个数
    public int getSize() {
        return size;
    }

    // 返回数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向数组中索引为index的位置添加一个元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        // 添加元素之前检查数组是否已满，满了的话扩容至原来容量的2倍
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; --i) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++size;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 返回索引为index的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Require index >= 0 and index < size.");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 更新索引为index的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Require index >= 0 and index < size.");
        }
        data[index] = e;
    }

    // 返回数组中是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; ++i) {
            if (e.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    // 返回元素e在数组中的索引，如果数组中不包含元素e返回-1
    public int find(E e) {
        for (int i = 0; i < size; ++i) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    // 删除数组中索引为index的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Require index >= 0 and index < size.");
        }
        E ret = data[index];
        for (int i = index; i + 1 < size; ++i) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;//内存回收
        --size;

        // 删除元素之后，检查元素的个数是否为容量的1/4,是的话将容量减小为原来容量的1/2
        if (size == data.length / 4 && data.length / 2 > 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Array, size:")
                .append(size)
                .append(", capacity:")
                .append(data.length)
                .append('\n')
                .append('[');
        for (int i = 0; i < size; ++i) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
