// 303. Range Sum Query - Immutable
// https://leetcode.com/problems/range-sum-query-immutable/description/
class NumArray {
    private class SegmentTree<E> {
        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
//            if (arr == null || arr.length == 0) {
//                throw new IllegalArgumentException("Illegal Argument!");
//            }
            this.merger = merger;

            data = (E[]) new Object[arr.length];
            for (int i = 0; i < data.length; ++i) {
                data[i] = arr[i];
            }
            tree = (E[]) new Object[arr.length * 4];
            buildSegmentTree(0, 0, data.length - 1);
        }

        // 在treeIndex的位置创建表示区间[l...r]的线段树
        private void buildSegmentTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);
            int mid = l + (r - l) / 2;
            buildSegmentTree(leftChild, l, mid);
            buildSegmentTree(rightChild, mid + 1, r);
            tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
        }

        public int getSize() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Illegal index.");
            }
            return data[index];
        }

        private int leftChild(int treeIndex) {
            return 2 * treeIndex + 1;
        }

        private int rightChild(int treeIndex) {
            return 2 * treeIndex + 2;
        }

        public E query(int queryL, int queryR) {
            if (queryL < 0 || queryL >= data.length
                    || queryR < 0 || queryR >= data.length || queryL > queryR) {
                throw new IllegalArgumentException("Illegal index.");
            }
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        // 在以treeIndex为根的线段树中[l...r]范围内，搜索区间[queryL...queryR]的值
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }

            int mid = l + (r - l) / 2;
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);
            if (queryR <= mid) {
                return query(leftChild, l, mid, queryL, queryR);
            } else if (queryL >= mid + 1) {
                return query(rightChild, mid + 1, r, queryL, queryR);
            } else {// mid > queryL && mid < queryR
                E left = query(leftChild, l, mid, queryL, mid);
                E right = query(rightChild, mid + 1, r, mid + 1, queryR);
                return merger.merge(left, right);
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Segment Tree, size:")
                    .append(data.length)
                    .append('\n')
                    .append('[');
            for (int i = 0; i < tree.length; ++i) {
                if (tree[i] == null) {
                    res.append("null");
                } else {
                    res.append(tree[i]);
                }
                if (i != tree.length - 1) {
                    res.append(", ");
                }
            }
            res.append(']');
            return res.toString();
        }
    }

    private interface Merger<E> {
        E merge(E a, E b);
    }

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < data.length; ++i) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("SegmentTree is null.");
        }
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray arr = new NumArray(nums);
        System.out.println(arr.sumRange(0, 2));
        System.out.println(arr.sumRange(2, 5));
        System.out.println(arr.sumRange(0, 5));
    }
}
