class NumArray2 {
    private int[] sums;

    public NumArray2(int[] nums) {
        if (nums.length > 0) {
            sums = new int[nums.length + 1];//sum[i]表示nums[0...i-1]区间元素的和
            sums[0] = 0;
            for (int i = 1; i < sums.length; ++i) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (sums == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        return sums[j + 1] - sums[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 arr = new NumArray2(nums);
        System.out.println(arr.sumRange(0, 2));
        System.out.println(arr.sumRange(2, 5));
        System.out.println(arr.sumRange(0, 5));
    }
}
