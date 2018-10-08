// Quick Find
public class UnionFind1 implements UF {

    private int id[];

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < size; ++i) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // Time Complexity : O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    // Time Complexity : O(n)
    @Override
    public void unionElements(int p, int q) {
        if (isConnected(p, q)) return;
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == id[p]) {
                id[i] = id[q];
            }
        }
    }
}
