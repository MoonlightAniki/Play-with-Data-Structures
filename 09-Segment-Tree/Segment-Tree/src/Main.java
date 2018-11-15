public class Main {
    public static void main(String[] args) {
        Integer[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(data, (a, b) -> a + b);
        System.out.println(segTree);
        System.out.println(segTree.query(0, 9));
        System.out.println(segTree.query(1, 3));
        System.out.println(segTree.query(5, 9));


        SegmentTree2<Integer> segTree2 = new SegmentTree2<Integer>(data, (a, b) -> a + b);
        System.out.println(segTree2);
        System.out.println(segTree2.query(0, 9));
        System.out.println(segTree2.query(1, 3));
        System.out.println(segTree2.query(5, 9));
    }
}
