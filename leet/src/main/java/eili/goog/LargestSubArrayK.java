package eili.goog;

/**
 *
 * Learnings:
 * At first, I thought this was a sliding window technique but then I realized the numbers were not digits
 * so computing a single base 10 value over 'k' elements would be astronomically huge.  I adapted this
 * approach, then to look at all the starting positions and evaluate each successive value against
 * the largest.  I found a solution, but was I too slow?
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

    //  s s s s s s     k=3
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
