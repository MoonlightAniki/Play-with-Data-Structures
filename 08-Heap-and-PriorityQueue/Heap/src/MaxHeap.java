import java.util.Random;

/**
 * 最大堆
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> array;

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public MaxHeap() {
        array = new Array<>();
    }

    // Heapify的时间复杂度：O(n)
    // 将元素逐个向堆中添加的时间复杂度:O(nlogn)
    public MaxHeap(E[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Illegal Data. Data can not be an empty array.");
        }
        array = new Array<>(data);
        // Heapify:从最后一个非叶子节点开始进行siftDown操作，直到根节点
        for (int i = parent(data.length - 1); i >= 0; --i) {
            siftDown(i);
        }
    }

    public int size() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Illegal index. Index-0 does not have a parent.");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 时间复杂度 O(logn)
    public void add(E e) {
        //向数组末尾添加一个元素，并对末尾元素进行siftUp操作
        array.addLast(e);
        siftUp(array.getSize() - 1);
    }

    public E findMax() {
        if (array.getSize() <= 0) {
            throw new IllegalArgumentException("FindMax failed, heap is empty.");
        }
        return array.get(0);
    }

    // 时间复杂度 O(logn)
    public E extractMax() {
        E ret = findMax();
        array.swap(0, array.getSize() - 1);// 交换最大元素与数组末尾的元素
        array.removeLast();//删除末尾元素
        siftDown(0);
        return ret;
    }

    public E replace(E e) {
        E ret = findMax();
        array.set(0, e);
        siftDown(0);
        return ret;
    }

    private void siftUp(int k) {
        // 循环进行条件：存在父亲节点并且父亲节点的值小于当前节点的值
        while (k > 0 && array.get(parent(k)).compareTo(array.get(k)) < 0) {
            array.swap(parent(k), k);
            k = parent(k);
        }
    }

    private void siftDown(int k) {
        while (leftChild(k) < array.getSize() /*存在左孩子*/) {
            int j = leftChild(k);
            if (rightChild(k) < array.getSize() && array.get(rightChild(k)).compareTo(array.get(leftChild(k))) > 0) {//存在右孩子且右孩子比左孩子大
                j = rightChild(k);
            }
            if (array.get(k).compareTo(array.get(j)) >= 0) {
                // 如果当前节点比左右孩子中的最大值还要大则退出循环
                break;
            }
            array.swap(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int count = 1000000;
        Integer[] arr = new Integer[count];
        Random random = new Random();
        for (int i = 0; i < count; ++i) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        testHeap(arr, false);
        testHeap(arr, true);
    }

    private static void testHeap(Integer[] arr, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(arr);
        } else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < arr.length; ++i) {
                maxHeap.add(arr[i]);
            }
        }
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            newArr[i] = maxHeap.extractMax();
        }
        for (int i = 0; i + 1 < arr.length; ++i) {
            if (newArr[i] < newArr[i + 1]) {
                throw new IllegalArgumentException();
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Test MaxHeap, count:" + arr.length + ", time:" + (endTime - startTime) / 1000000000.0 + " s.");
    }
}
