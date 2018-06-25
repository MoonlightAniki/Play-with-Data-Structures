import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/description/
public class Solution347 {
//    public List<Integer> topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//        List<Integer>[] bucket = new List[nums.length + 1];
//        for (int key : map.keySet()) {
//            int freq = map.get(key);
//            if (bucket[freq] == null) {
//                bucket[freq] = new LinkedList<>();
//            }
//            bucket[freq].add(key);
//        }
//        List<Integer> res = new LinkedList<>();
//        for (int i = bucket.length - 1; i >= 0; i--) {
//            if (bucket[i] != null && res.size() < k) {
//                res.addAll(bucket[i]);
//            }
//        }
//        return res;
//    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }

        // java.util.PriorityQueue的内部是用最小堆实现的
//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer a, Integer b) {
//                return freqMap.get(a) - freqMap.get(b);
//            }
//        });

        // 使用lambda表达式
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> freqMap.get(a) - freqMap.get(b)
        );

        for (Integer num : freqMap.keySet()) {
            if (pq.size() < k) {
                pq.add(num);
            } else if (freqMap.get(num) > freqMap.get(pq.peek())) {
                pq.remove();
                pq.add(num);
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.addFirst(pq.remove());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int k = 2;
        Solution347 s = new Solution347();
        System.out.println(s.topKFrequent(nums, k));
    }
}
