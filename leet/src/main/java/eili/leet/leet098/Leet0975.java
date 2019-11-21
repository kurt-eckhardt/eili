package eili.leet.leet098;

import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #:  0975
 * Name:       Odd Even Jumps
 * Tags:       TreeMap, Google Question
 * BigO:       O(N Log N)
 * Difficulty: Hard
 * Techniques: Dynamic Programming
 * Learnings:
 * 1) TreeMap lowerEntry, floorEntry, higherEntry, ceilingEntry can be used to return subtree values
 * 2) Why do we need to count just odd instead of odd+even to get the totalWays to reach the end?
 *
 *
 * You are given an integer array A.  From some starting index, you can make a series of jumps.
 * The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and
 * the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.
 *
 * You may from index i jump forward to index j (with i < j) in the following way:
 *
 * During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that
 * A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j,
 * you can only jump to the smallest such index j.
 *
 * During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that
 * A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j,
 * you can only jump to the smallest such index j.
 *
 * It may be the case that for some index i, there are no legal jumps.
 *
 * A starting index is good if, starting from that index, you can reach the end of the
 * array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)
 *
 * Return the number of good starting indexes.
 *
 *
 * For example:
 * Input: [2,3,1,1,4]
 * Output: 3
 * Explanation:
 * From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
 *
 * During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value
 * in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].
 *
 * During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value
 * in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a
 * smaller index, so we can only jump to i = 2 and not i = 3.
 *
 * During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value
 * in (A[3], A[4]) that is greater than or equal to A[2].
 *
 * We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
 *
 * In a similar manner, we can deduce that:
 * From starting index i = 1, we jump to i = 4, so we reach the end.
 * From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
 * From starting index i = 3, we jump to i = 4, so we reach the end.
 * From starting index i = 4, we are already at the end.
 *
 * In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end
 * with some number of jumps.
 */
public class Leet0975 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{10,13,12,14,15}));
        System.out.println(solution(new int[]{2,3,1,1,4}));
        System.out.println(solution(new int[]{1,2,3,2,1,4,4,5}));
    }

    public static int solution(int nums[]) {

        if (nums.length <= 1) return nums.length;

        // Maps a value to the index in the "nums" array that it was found.
        // We'll use this index to lookup if an odd or even jump can be made
        // that will eventually reach the end of the array
        TreeMap<Integer, Integer> valMap = new TreeMap<>();
        boolean[] canReachEndJumpOdd = new boolean[nums.length];
        boolean[] canReachEndJumpEvn = new boolean[nums.length];

        // This is our base case - we can always reach the end from the end :)
        canReachEndJumpOdd[nums.length-1] = true;
        canReachEndJumpEvn[nums.length-1] = true;
        valMap.put(nums[nums.length-1], nums.length-1);

        for (int i=nums.length-2; i >= 0; i--) {

            // Determine if we can reach the end of the array by making an odd jump.
            // This will be true if the next even index can reach the end of the array.
            Map.Entry<Integer, Integer> indexOfNextHigherVal = valMap.ceilingEntry(nums[i]);
            canReachEndJumpOdd[i] = indexOfNextHigherVal != null && canReachEndJumpEvn[indexOfNextHigherVal.getValue()];

            // Similarly, determine if we can reach the end by making an even jump.
            Map.Entry<Integer, Integer> indexOfNextLowerVal = valMap.floorEntry(nums[i]);
            canReachEndJumpEvn[i] = indexOfNextLowerVal != null && canReachEndJumpOdd[indexOfNextLowerVal.getValue()];

            // Add this number to the array since this will be the next smallest
            // index "j" when nums[i] already exists.
            valMap.put(nums[i], i);
        }

        // Now just count the ways in our results array
        // Note we only count the "odd" ways to reach not both odd+even.  Why?
        // Because if we can reach from a index using odd then we know it's next
        // jump was from even.  If we also count that even jump then we're double
        // counting.
        int numWays = 0;
        for (boolean canReachEnd : canReachEndJumpOdd) {
            if (canReachEnd) numWays++;
        }

        return numWays;
    }

}










