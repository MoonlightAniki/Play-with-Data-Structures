// 307. Range Sum Query - Mutable
// https://leetcode.com/problems/range-sum-query-mutable/description/
class NumArray3 {

    private int[] data;
    private int[] sums;

    public NumArray3(int[] nums) {
        if (nums != null && nums.length > 0) {
            data = new int[nums.length];
            for (int i = 0; i < data.length; ++i) {
                data[i] = nums[i];
            }
            sums = new int[nums.length + 1];//sums[i]表示nums[0...i-1]区间元素的和
            sums[0] = 0;
            for (int i = 1; i < sums.length; ++i) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
        }
    }

    public void update(int index, int val) {
        if (data == null || sums == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        data[index] = val;
        for (int i = index + 1; i < sums.length; ++i) {
            sums[i] = sums[i - 1] + data[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (sums == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        return sums[j + 1] - sums[i];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray3 numArray3 = new NumArray3(nums);
        System.out.println(numArray3.sumRange(0, 2));
        numArray3.update(1, 2);
        System.out.println(numArray3.sumRange(0, 2));
    }
}