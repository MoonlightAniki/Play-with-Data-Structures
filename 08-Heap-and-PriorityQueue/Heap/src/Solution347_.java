import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/description/
public class Solution347_ {

    private class Array<E> {
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
    }

    /**
     * 最大堆
     */
    private class MaxHeap<E extends Comparable<E>> {
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
    }

    private interface Queue<E> {
        int getSize();

        boolean isEmpty();

        void enqueue(E e);

        E dequeue();

        E getFront();
    }

    private class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

        private MaxHeap<E> maxHeap;

        public PriorityQueue() {
            maxHeap = new MaxHeap<>();
        }

        @Override
        public int getSize() {
            return maxHeap.size();
        }

        @Override
        public boolean isEmpty() {
            return maxHeap.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            maxHeap.add(e);
        }

        @Override
        public E dequeue() {
            return maxHeap.extractMax();
        }

        @Override
        public E getFront() {
            return maxHeap.findMax();
        }
    }

    private class Freq implements Comparable<Freq> {
        public int num;
        public int freq;

        public Freq(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            return o.freq - this.freq;
        }
    }

    // 时间复杂度 O(nlogk)
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int num : nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (Integer num : freqMap.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Freq(num, freqMap.get(num)));
            } else if (freqMap.get(num) > pq.getFront().freq) {
                pq.dequeue();
                pq.enqueue(new Freq(num, freqMap.get(num)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            res.addFirst(pq.dequeue().num);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        Solution347_ s = new Solution347_();
        System.out.println(s.topKFrequent(nums, k));
    }
}
