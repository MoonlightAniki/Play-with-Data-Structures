import java.util.Objects;

public class Array<E> {
    private int count;
    private E[] data;

    public Array(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        data = (E[]) new Object[capacity];
        count = 0;
    }

    public Array() {
        data = (E[]) new Object[10];
        count = 0;
    }

    public Array(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Arr cannot be empty.");
        }
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            data[i] = arr[i];
        }
        count = arr.length;
    }

    public int getSize() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public void add(int index, E e) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index <= size.");
        }
        //添加元素之前检查数组的容量
        if (count == data.length) {
            // 如果数组已满，扩容至原容量的2倍
            resize(data.length * 2);
        }
        for (int i = count - 1; i >= index; --i) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++count;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(count, e);
    }

    public E get(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(count - 1);
    }

    public E remove(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        E ret = data[index];
        for (int i = index; i + 1 < count; ++i) {
            data[i] = data[i + 1];
        }
        --count;
        // 删除完元素之后检查数组容量
        if (count == data.length / 4 && data.length / 2 > 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(count - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < count; ++i) {
            if (Objects.equals(e, data[i])) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < count; ++i) {
            if (Objects.equals(e, data[i])) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < count; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        if (j < 0 || j >= count) {
            throw new IllegalArgumentException("Illegal index. Require index >= 0 and index < size.");
        }
        if (i == j) return;
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Array, size:")
                .append(count)
                .append(", capacity:")
                .append(data.length)
                .append('\n')
                .append('[');
        for (int i = 0; i < count; ++i) {
            res.append(data[i]);
            if (i != count - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 17; i++) {
            arr.addFirst(i);
            System.out.println(arr);
        }
        arr.set(arr.getSize() - 1, 100);
        System.out.println(arr);
    }
}
