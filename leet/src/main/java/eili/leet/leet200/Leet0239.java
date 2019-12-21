package eili.leet.leet200;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #:  0239
 * Name:       Sliding Window Maximum
 * Tags:       Monotonic, Queue, Monoqueue
 * BigO:       O(N) Time (amortized), O(1) Space
 * Difficulty: Hard
 * Techniques:
 * Learnings:
 * 1. Monotonic Queue/Stack!!
 *    When faced with a "sliding window minimum/maximum" consider using a monotonic queue.
 *    This datastructure keeps a list or stack of values that are increasing or decreasing (e.g. 1,2,3 or 6,5,4).
 *    These allow O(1) access to a max or min value and amoritized O(K) time to add a new value where K=window size.
 * 
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array
 * to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class Leet0239 {

    public static void main(String[] args) {
        for (int max : slidingWindowMax(new int[] {1,3,-1,-3,5,3,6,7}, 3)) {
            System.out.print(max + ",");
        }
    }

    // Add numbers to the end of the queue such that the front of the queue will always have the largest value
    // nums=[3,2,1] then, Q=[3,2,1]
    // nums=[2,1,3] then, Q[2], Q=[2,1], Q=[3] (notice 2,1 are removed since 3 is bigger than both)
    public static int[] slidingWindowMax(int[] nums, int k) {

        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[0];
        }

        int[] maxVals = new int[nums.length-k+1];

        ArrayDeque<Integer> monoQ = new ArrayDeque<>();
        for (int i=0; i < nums.length; i++) {

            // Remove all values from monoQ that are smaller than nums[i] since they can never be the max
            while (monoQ.size() > 0 && nums[monoQ.getLast()] <= nums[i]) {
                monoQ.removeLast();
            }

            // Now add nums[i] since it may be a max value in the future
            // Note we are adding the index instead of the value so we can detect when it moves out of window
            monoQ.addLast(i);

            // The front value is always the max O(1)
            boolean isSlidingWindowFull = i+1 >= k;
            if (isSlidingWindowFull) {
                maxVals[i-k+1] = nums[monoQ.getFirst()];
            }

            // Finally, remove the max value if it no longer fits in our window
            boolean isMaxMonoQValSlidingOutOfWindow = i-monoQ.getFirst()+1 >= k;
            if (isMaxMonoQValSlidingOutOfWindow) monoQ.removeFirst();
        }

        return maxVals;
    }


//    public static int[] referenceAnswer(int[] nums, int k) {
//        if (k == 0) return nums;
//
//        int[] ans = new int[nums.length - k + 1];
//        Deque<Integer> q = new ArrayDeque<>();
//
//        for (int i = 0; i < nums.length; i++) {
//
//            System.out.print("i="+i + " q="+q + "\tans=[");
//            for (int x=0; x < ans.length-1; x++) System.out.print(ans[x] + ", ");
//            System.out.println(ans[ans.length-1]+"]");
//
//            while (q.size() > 0 && nums[i] >= nums[q.getLast()]){
//                q.removeLast();
//            }
//            q.addLast(i);
//            if (i - k + 1 >= 0) ans[i - k + 1] = nums[q.getFirst()];
//
//            //finally remove max which is out of range
//            if (i - k + 1 == q.getFirst()) q.removeFirst();
//        }
//
//        return ans;
//    }
}
