import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// 349. Intersection of Two Arrays
// https://leetcode.com/problems/intersection-of-two-arrays/description/
public class Solution349 {
    // version1：使用java自带的HashSet解决
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            set.add(nums1[i]);
        }
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (set.contains(nums2[i])) {
                resultSet.add(nums2[i]);
            }
        }
        int[] res = new int[resultSet.size()];
        Iterator<Integer> it = resultSet.iterator();
        for (int i = 0; i < res.length; ++i) {
            res[i] = it.next();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {2, 2, 3, 5};
        Solution349 s = new Solution349();
        int[] result = s.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
