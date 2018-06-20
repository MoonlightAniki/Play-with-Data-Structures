public class Sum {
    // 返回整型数组中所有整数的和
    public int sum(int[] arr) {
        int l = 0;
        return sum(l, arr);
    }

    // 返回整型数组arr[l...arr.length)范围内整数的和
    private int sum(int l, int[] arr) {
        //递归终止条件
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(l + 1, arr);
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        int[] arr = new int[]{1, 3, 2, 4, 6, 5, 8, 9, 7};
        System.out.println(sum.sum(arr));
    }
}
