public class SegmentTree2<E> {
    private class Node {
        private int start, end;
        private E val;
        private Node left, right;

        public Node(E val, int start, int end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }

    private Merger<E> merger;
    private E[] data;
    private Node root;

    public SegmentTree2(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            data[i] = arr[i];
        }

        root = buildSegmentTree(0, data.length - 1);
    }

    // 使用data[start...end]构建一棵线段树
    private Node buildSegmentTree(int start, int end) {
        if (start == end) {
            return new Node(data[start], start, end);
        }
        int mid = start + (end - start) / 2;
        Node leftNode = buildSegmentTree(start, mid);
        Node rightNode = buildSegmentTree(mid + 1, end);
        Node node = new Node(merger.merge(leftNode.val, rightNode.val), start, end);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    public void set(int index, E newValue) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Invalid Index");
        }
        data[index] = newValue;
        root = set(root, index, newValue);
    }

    private Node set(Node node, int index, E newValue) {
        if (node.start == node.end) {
            node.val = newValue;
            return node;
        }
        int mid = node.start + (node.end - node.start) / 2;
        if (index <= mid) {
            node.left = set(node.left, index, newValue);
        } else {
            node.right = set(node.right, index, newValue);
        }
        node.val = merger.merge(node.left.val, node.right.val);
        return node;
    }


    public E query(int queryL, int queryR) {
        if (queryL > queryR || queryL < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("Invalid Index");
        }
        return query(root, queryL, queryR);
    }

    private E query(Node node, int queryL, int queryR) {
        if (node.start == queryL && node.end == queryR) {
            return node.val;
        }
        int mid = node.start + (node.end - node.start) / 2;
        if (queryL > mid) {
            return query(node.right, queryL, queryR);
        } else if (queryR <= mid) {
            return query(node.left, queryL, queryR);
        } else {
            E leftValue = query(node.left, queryL, mid);
            E rightValue = query(node.right, mid + 1, queryR);
            return merger.merge(leftValue, rightValue);
        }
    }


    public static void main(String[] args) {
        SegmentTree2<Integer> segmentTree = new SegmentTree2<Integer>(new Integer[]{1, 2, 3, 4, 5}, (a, b) -> {
            return Math.max(a, b);
        });

        segmentTree.set(0, 4);
        System.out.println(segmentTree.query(0, 4));
    }
}
