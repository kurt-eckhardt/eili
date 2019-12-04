package eili.goog;

/**
 * Array X is greater than array Y if the first non-matching element in both arrays has a greater value in X than in Y.
 * For example:
 * X = [1,2,4,3,5]
 * Y = [1,2,3,4,5]
 *
 * X is greater than Y because the first element that does not match is larger in X (i.e. for X[2] > Y[2])
 *
 * A contiguous subarray is defined by an interval of consecitive indicies (e.g. 4,5,6,7 or 1,2,3)
 *
 * Given an array 'A' consisting of 'N' integers and an integer 'K', write a function that returns the largest
 * contiguous subarray of length K from all the contiguous subarrays of length K.
 *
 * For example, given A=[1,4,3,2,5] and K=4, the function should return [4,3,2,5]
 *
 * Learnings:
 * 1. At first, I thought this was a sliding window technique but then I realized the numbers were not digits
 * so computing a single base 10 value over 'k' elements would be astronomically huge.  I adapted this approach
 * to look at all the starting positions and evaluate each successive value against the largest.
 *
 * 2. I found a solution, but was I too slow?
 *
 * 3. A better solution is to work forward because we can skip ahead when we find a value that is bigger than the value
 *    we are building.  For example: A=[3,4,5,1,2,3,4,9,0], k=3  as we build the largest value we see 3,4,5; however
 *    we can stop building 3,4,5 when we see "4" because we know that a number starting with "4" will be larger.
 *    Similarly, when we start building "4,5,1" we can stop at "5" because we know "5,1,2" will be larger still.
 *    Once we build 5,2,1 (k=3), we can then consider "3..." as the next largest.
 */
public class LargestSubArrayK {

    /**
     * O(N*K) when all elements are the same
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[] {1,40,3,2,3,40,3,1};
        int[] largest = largestSubArray(nums, 3);
        for (int i : largest) { System.out.print(i+","); }
    }

    //  s s s s s s      k=3
    // {1,4,3,2,3,4,5,1} length=8
    //
    public static int[] largestSubArray(int[] nums, int k) {

        int[] largest = new int[k];
        for (int sIdx=nums.length-k; sIdx >= 0; sIdx--) {
            updateLargest(largest, nums, sIdx, k);
        }

        return largest;
    }

    private static void updateLargest(int[] largest, int[] nums, int sIdx, int k) {
        for (int i=0; i < k; i++) {
            if (nums[sIdx+i] < largest[i]) {
                return;
            } else if (nums[sIdx+i] > largest[i]) {
                System.arraycopy(nums, sIdx, largest, 0, k);
            } else {
                // they are equal so continue looking at next number
            }
        }
    }

}
