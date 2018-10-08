import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 1000000;
        int opCount = 1000000;
//        UF uf1 = new UnionFind1(size);
//        System.out.println("UnionFind1 : " + testUnionFind(uf1, opCount) + "s");

//        UF uf2 = new UnionFind2(size);
//        System.out.println("UnionFind2 : " + testUnionFind(uf2, opCount) + "s");

        UF uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUnionFind(uf3, opCount) + "s");

        UF uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUnionFind(uf4, opCount) + "s");

        UF uf5 = new UnionFind5(size);
        System.out.println("UnionFind5 : " + testUnionFind(uf5, opCount) + "s");

        UF uf6 = new UnionFind6(size);
        System.out.println("UnionFind6 : " + testUnionFind(uf6, opCount) + "s");
    }

    private static double testUnionFind(UF uf, int opCount) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < opCount; ++i) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.unionElements(p, q);
        }
        for (int i = 0; i < opCount; ++i) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p, q);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
